package com.avenuecode.starwars.api.service.extractors;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.model.MovieSetting;

public class MovieSettingExtractor implements Extractor<String[], MovieSetting> {

    private static final String[] SCRIPT_TITLES_PREFIXES = new String[] { "INT. ", "EXT. ", "INT./EXT. " };
    private static final String SETTING_REGEX = "\\b(INT\\.{1}\\/{1}EXT.\\s)\\b|\\b(INT.\\s)\\b|[^/]\\b(EXT.\\s)\\b";

    private Extractor<String[], Collection<MovieCharacter>> delegate;

    public MovieSettingExtractor(Extractor<String[], Collection<MovieCharacter>> delegate) {
	this.delegate = delegate;
    }

    @Override
    public MovieSetting extract(String[] settingLines) {

	if (settingLines.length > 0 && isSettingTitle(settingLines[0])) {
	    String settingTitle = getSettingTitle(settingLines[0]);

	    if (StringUtils.isNotBlank(settingTitle)) {

		MovieSetting movieSetting = new MovieSetting();
		movieSetting.setName(settingTitle);

		String[] range = Arrays.copyOfRange(settingLines, 1, settingLines.length);

		if (this.delegate != null) {
		    Collection<MovieCharacter> characters = this.delegate.extract(range);
		    movieSetting.setCharacters(characters);
		}

		return movieSetting;
	    }
	}

	return null;
    }

    private boolean isSettingTitle(String string) {
	return StringUtils.indexOfAny(string, SCRIPT_TITLES_PREFIXES) == 0;
    }

    private String getSettingTitle(String str) {
	Pattern pattern = Pattern.compile(SETTING_REGEX);
	Matcher matcher = pattern.matcher(str);
	if (matcher.find()) {
	    int indexOfAny = StringUtils.indexOfAny(str, "\r\n", "\n", "\r", " - ");
	    if (indexOfAny > -1) {
		String title = str.substring(matcher.end(), indexOfAny).trim();
		return title;
	    }
	}
	return null;
    }

}
