package com.avenuecode.starwars.api.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.model.MovieScript;
import com.avenuecode.starwars.api.model.MovieSetting;
import com.avenuecode.starwars.api.model.WordCount;
import com.avenuecode.starwars.api.service.extractors.Extractor;
import com.avenuecode.starwars.api.service.extractors.MovieCharacterExtractor;
import com.avenuecode.starwars.api.service.extractors.MovieSettingExtractor;
import com.avenuecode.starwars.api.service.extractors.WordCountExtractor;

@Service
public class MovieDialogueProcessor {

    private static final String NEW_LINE_REGEX = "\\r\\n|\\n|\\r";
    private static final String SETTING_REGEX = "\\b(INT\\.{1}\\/{1}EXT.\\s)\\b|\\b(INT.\\s)\\b|[^/]\\b(EXT.\\s)\\b";
    private static final String SETTING_REGEX_LOOKAHEAD = "(?=" + SETTING_REGEX + ")";

    
    public Collection<MovieSetting> process(MovieScript script) {
	String[] splited = script.getContent().split(SETTING_REGEX_LOOKAHEAD);

	Extractor<String[], MovieSetting> settingExtractor = buildExtractors();
	Collection<MovieSetting> mls = new ArrayList<MovieSetting>();
	
	for (String settingString : splited) {
	    String[] settingLines = settingString.split(NEW_LINE_REGEX);
	    MovieSetting extracted = settingExtractor.extract(settingLines);
	    mls.add(extracted);
	}

	return mls;
    }


    private Extractor<String[], MovieSetting> buildExtractors() {
	Extractor<String, Collection<WordCount>> wordCountExtractor = new WordCountExtractor(null);
	Extractor<String[], Collection<MovieCharacter>> charactorExtractor = new MovieCharacterExtractor(wordCountExtractor);
	Extractor<String[], MovieSetting> settingExtractor = new MovieSettingExtractor(charactorExtractor);
	return settingExtractor;
    }
    
}
