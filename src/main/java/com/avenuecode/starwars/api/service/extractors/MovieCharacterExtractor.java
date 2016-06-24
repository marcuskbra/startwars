package com.avenuecode.starwars.api.service.extractors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

public class MovieCharacterExtractor implements Extractor {

    private Extractor delegate;

    private static final String CHARACTER_NAME_PREFIX = "                      ";
    private static final String CHARACTER_SPEAK_PREFIX = "          ";

    public MovieCharacterExtractor(Extractor delegate) {
	this.delegate = delegate;
    }

    @Override
    // TODO: passar como parametro os objetos que devem ser extraidos
    public String extract(String[] settingLines) {
	Map<String, List<String>> phrases = new HashMap<String, List<String>>();

	for (int i = 0; i < settingLines.length; i++) {
	    String line = settingLines[i];
	    String characterName = null;
	    if (isCharacterName(line)) {
		characterName = line.trim();
		phrases.putIfAbsent(characterName, new ArrayList<String>());
	    } else if (isCharacterSpeak(line)) {
		if (phrases.containsKey(characterName)) {
		    phrases.get(characterName).add(line.trim());
		}
	    }
	}

	// return phrases;
	return null;
    }

    private boolean isCharacterSpeak(String line) {
	return StringUtils.isNotBlank(line) && line.startsWith(CHARACTER_SPEAK_PREFIX);
    }

    private boolean isCharacterName(String line) {
	return StringUtils.isNotBlank(line) && line.startsWith(CHARACTER_NAME_PREFIX);
    }

}
