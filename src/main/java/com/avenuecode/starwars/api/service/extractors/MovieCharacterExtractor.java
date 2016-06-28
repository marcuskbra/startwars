package com.avenuecode.starwars.api.service.extractors;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class MovieCharacterExtractor {

    private static final String CHARACTER_NAME_REGEX = "(^\\s{22})\\S[A-Z].*$";

    public Set<String> extractCharactersNames(final String[] settingLines) {
	final Set<String> charactersNames = new HashSet<>();

	if (settingLines != null) {
	    for (final String line : settingLines) {
		if (isCharacterName(line)) {
		    charactersNames.add(line.trim());
		}
	    }
	}
	return charactersNames;
    }

    private boolean isCharacterName(final String line) {
	return Pattern.matches(CHARACTER_NAME_REGEX, line);
    }
}
