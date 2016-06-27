package com.avenuecode.starwars.api.service.extractors;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CharacterPhrasesExtractor {

    private static final String CHARACTER_NAME_REGEX = "(^\\s{22})\\S[A-Z].*$";
    private static final String CHARACTER_PHRASE_REGEX = "(^\\s{10})\\S.*$";

    public void extract(Map<String, StringBuilder> phrases, String[] settingLines) {

	String characterName = null;
	for (final String line : settingLines) {
	    if (isCharacterName(line)) {
		characterName = line.trim();
		if (!phrases.containsKey(characterName)) {
		    phrases.put(characterName, new StringBuilder());
		}
	    } else if (characterName != null && isCharacterSpeaking(line)) {
		if (phrases.containsKey(characterName)) {
		    phrases.get(characterName).append(line.trim()).append(" ");
		}
	    }
	}
    }

    private boolean isCharacterSpeaking(final String line) {
	return Pattern.matches(CHARACTER_PHRASE_REGEX, line);
    }

    private boolean isCharacterName(final String line) {
	return Pattern.matches(CHARACTER_NAME_REGEX, line);
    }

}
