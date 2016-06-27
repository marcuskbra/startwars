package com.avenuecode.starwars.api.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.model.MovieScript;
import com.avenuecode.starwars.api.model.WordCount;
import com.avenuecode.starwars.api.repository.CharacterWordsRepository;
import com.avenuecode.starwars.api.repository.MovieCharacterRepository;
import com.avenuecode.starwars.api.service.extractors.CharacterPhrasesExtractor;
import com.avenuecode.starwars.api.service.extractors.MovieSettingExtractor;
import com.avenuecode.starwars.api.service.extractors.WordCountExtractor;

@Service
public class MovieDialogueProcessor {

    private static final String NEW_LINE_REGEX = "\\r\\n|\\n|\\r";
    private static final String SETTING_REGEX = "\\b(INT\\.{1}\\/{1}EXT.\\s)\\b|\\b(INT.\\s)\\b|(EXT.\\s)\\b";
    private static final String SETTING_REGEX_LOOKAHEAD = "(?=" + SETTING_REGEX + ")";

    @Autowired
    private CharacterWordsRepository wordsRepository;

    @Autowired
    private MovieCharacterRepository characterRepository;

    @Autowired
    private MovieSettingExtractor settingExtractor;

    @Autowired
    private CharacterPhrasesExtractor phrasesExtractor;

    @Autowired
    private WordCountExtractor wordCountExtractor;
    
    public void process(final MovieScript script) {

	String content = script.getContent();
	final String[] splitedSettings = content.split(SETTING_REGEX_LOOKAHEAD);

	final Map<String, MovieCharacter> characters = new HashMap<>();

	for (final String settingString : splitedSettings) {
	    final String[] settingLines = settingString.split(NEW_LINE_REGEX);
	    this.settingExtractor.extractCharactersAndSettings(characters, settingLines);
	}


	final Map<String, StringBuilder> phrases = new HashMap<>();

	for (final String settingString : splitedSettings) {
	    final String[] settingLines = settingString.split(NEW_LINE_REGEX);
	    this.phrasesExtractor.extract(phrases, settingLines);
	}
	
	phrases.forEach((k, v) -> {
	    MovieCharacter movieCharacter = characters.get(k);
	    final Collection<WordCount> words = this.wordCountExtractor.extract(movieCharacter, v.toString());
	    movieCharacter.getWordCounts().addAll(words);
	});

	this.characterRepository.save(characters.values());
    }
}
