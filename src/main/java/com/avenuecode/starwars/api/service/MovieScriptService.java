package com.avenuecode.starwars.api.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.starwars.api.exception.DuplicatedScriptException;
import com.avenuecode.starwars.api.exception.InvalidMovieScriptException;
import com.avenuecode.starwars.api.service.extractors.CharacterPhrasesExtractor;
import com.avenuecode.starwars.api.service.extractors.MovieSettingExtractor;
import com.avenuecode.starwars.api.service.extractors.WordCountExtractor;
import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.MovieScript;
import com.avenuecode.starwars.data.model.WordCount;
import com.avenuecode.starwars.data.repository.MovieCharacterRepository;
import com.avenuecode.starwars.data.repository.MovieScriptRepository;

@Service
public class MovieScriptService {

    private static final String NEW_LINE_REGEX = "\\r\\n|\\n|\\r";
    private static final String SETTING_REGEX = "\\b(INT\\.{1}\\/{1}EXT.\\s)\\b|\\b(INT.\\s)\\b|(EXT.\\s)\\b";
    private static final String SETTING_REGEX_LOOKAHEAD = "(?=" + SETTING_REGEX + ")";

    @Autowired
    private MovieCharacterRepository characterRepository;
    
    @Autowired
    private MovieScriptRepository scriptRepository;

    @Autowired
    private MovieSettingExtractor settingExtractor;

    @Autowired
    private CharacterPhrasesExtractor phrasesExtractor;

    @Autowired
    private WordCountExtractor wordCountExtractor;
    
    public void processMovieScript(final MovieScript script) {

	validateScript(script);
	
	processScript(script);
    }

    private void processScript(final MovieScript script) {
	String content = script.getContent();
	final String[] splitedSettings = content.split(SETTING_REGEX_LOOKAHEAD);

	final Map<String, MovieCharacter> characters = extractCharactersAndSettings(splitedSettings);

	if (characters.keySet().isEmpty()) {
	    throw new InvalidMovieScriptException();
	}
	
	final Map<String, StringBuilder> phrases = extractPhrases(splitedSettings);
	
	countWords(characters, phrases);

	this.characterRepository.save(characters.values());
    }

    private void countWords(final Map<String, MovieCharacter> characters, final Map<String, StringBuilder> phrases) {
	phrases.forEach((k, v) -> {
	    MovieCharacter movieCharacter = characters.get(k);
	    final Collection<WordCount> words = this.wordCountExtractor.extract(movieCharacter, v.toString());
	    movieCharacter.getWordCounts().addAll(words);
	});
    }

    private Map<String, StringBuilder> extractPhrases(final String[] splitedSettings) {
	final Map<String, StringBuilder> phrases = new HashMap<>();

	for (final String settingString : splitedSettings) {
	    final String[] settingLines = settingString.split(NEW_LINE_REGEX);
	    this.phrasesExtractor.extract(phrases, settingLines);
	}
	return phrases;
    }

    private Map<String, MovieCharacter> extractCharactersAndSettings(final String[] splitedSettings) {
	final Map<String, MovieCharacter> characters = new HashMap<>();

	for (final String settingString : splitedSettings) {
	    final String[] settingLines = settingString.split(NEW_LINE_REGEX);
	    this.settingExtractor.extractCharactersAndSettings(characters, settingLines);
	}
	return characters;
    }

    public void validateScript(final MovieScript script) {
	final boolean exists = this.scriptRepository.exists(script.getId());
	if (!exists) {
	    this.scriptRepository.save(script);
	} else {
	    throw new DuplicatedScriptException("Movie script already received");
	}
    }
}
