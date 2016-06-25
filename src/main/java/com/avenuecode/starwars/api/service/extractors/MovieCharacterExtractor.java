package com.avenuecode.starwars.api.service.extractors;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.model.WordCount;

public class MovieCharacterExtractor implements Extractor<String[], Collection<MovieCharacter>> {

    private final Extractor<String, Collection<WordCount>> delegate;

    private static final String CHARACTER_NAME_PREFIX = "                      ";
    private static final String CHARACTER_SPEAK_PREFIX = "          ";

    public MovieCharacterExtractor(final Extractor<String, Collection<WordCount>> delegate) {
	this.delegate = delegate;
    }

    @Override
    public Collection<MovieCharacter> extract(final String[] settingLines) {
	final Map<String, StringBuilder> phrases = extractPhrases(settingLines);

	final Set<MovieCharacter> characters = new HashSet<>();
	phrases.forEach((k, v) -> {
	    final MovieCharacter movieCharacter = new MovieCharacter();
	    movieCharacter.setName(k);

	    if (this.delegate != null) {
		final Collection<WordCount> wordCounts = this.delegate.extract(v.toString());
		movieCharacter.setWordCounts(wordCounts);
	    }
	    characters.add(movieCharacter);
	});

	return characters;
    }

    private Map<String, StringBuilder> extractPhrases(final String[] settingLines) {
	final Map<String, StringBuilder> phrases = new HashMap<>();

	String characterName = null;
	for (final String line : settingLines) {
	    if (isCharacterName(line)) {
		characterName = line.trim();
		if (!phrases.containsKey(characterName)) {
		    phrases.put(characterName, new StringBuilder());
		}
	    } else if (isCharacterSpeaking(line)) {
		if (phrases.containsKey(characterName)) {
		    phrases.get(characterName).append(" ").append(line.trim());
		}
	    }
	}
	return phrases;
    }

    private boolean isCharacterSpeaking(final String line) {
	return StringUtils.isNotBlank(line) && line.startsWith(CHARACTER_SPEAK_PREFIX);
    }

    private boolean isCharacterName(final String line) {
	return StringUtils.isNotBlank(line) && line.startsWith(CHARACTER_NAME_PREFIX);
    }
}
