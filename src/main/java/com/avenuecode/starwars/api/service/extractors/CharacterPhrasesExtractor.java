package com.avenuecode.starwars.api.service.extractors;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CharacterPhrasesExtractor implements Extractor<String[], Map<String, StringBuilder>> {

    private static final String CHARACTER_NAME_PREFIX = "                      ";
    private static final String CHARACTER_SPEAK_PREFIX = "          ";

    @Override
    public Map<String, StringBuilder> extract(final String[] settingLines) {
	final Map<String, StringBuilder> phrases = extractPhrases(settingLines);


	return phrases;
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
