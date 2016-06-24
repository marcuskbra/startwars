package com.avenuecode.starwars.api.service.extractors;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class MovieSettingExtractor implements Extractor {

    private static final String[] SCRIPT_TITLES_PREFIXES = new String[] { "INT. ", "EXT. ", "INT./EXT. " };
    private static final String SETTING_REGEX = "\\b(INT\\.{1}\\/{1}EXT.\\s)\\b|\\b(INT.\\s)\\b|[^/]\\b(EXT.\\s)\\b";

    private Extractor delegate;

    public MovieSettingExtractor(Extractor delegate) {
	this.delegate = delegate;
    }

    @Override
    public String extract(String[] settingLines) {
	

	if (settingLines.length > 0 && isSettingTitle(settingLines[0])) {
	    String settingTitle = getSettingTitle(settingLines[0]);

	    String[] range = Arrays.copyOfRange(settingLines, 1, settingLines.length);

	    this.delegate.extract(range);
	}

	return null;
    }

    private boolean isSettingTitle(String string) {
	return StringUtils.indexOfAny(string, SCRIPT_TITLES_PREFIXES) == 0;
    }

    private static String getSettingTitle(String str) {
	Pattern pattern = Pattern.compile(SETTING_REGEX);
	Matcher matcher = pattern.matcher(str);
	if (matcher.find()) {
	    int indexOfAny = StringUtils.indexOfAny(str, "\r\n", "\n", "\r", " - ");
	    if (indexOfAny > -1) {
		String title = str.substring(matcher.end(), indexOfAny).trim();
		return title;
	    }
	}
	return "";
    }

}
