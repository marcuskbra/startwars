package com.avenuecode.starwars.api.service.extractors;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.MovieSetting;
import com.avenuecode.starwars.data.repository.MovieSettingRepository;

@Component
public class MovieSettingExtractor {

    private static final String[] SCRIPT_TITLES_PREFIXES = new String[] { "INT. ", "EXT. ", "INT./EXT. " };
    private static final String SETTING_REGEX = "\\b(INT\\.{1}\\/{1}EXT.\\s)\\b|\\b(INT.\\s)\\b|(EXT.\\s)\\b";

    @Autowired
    private MovieSettingRepository rep;

    public void extractCharactersAndSettings(final Map<String, MovieCharacter> characters, final String[] settingLines) {

	if (settingLines.length > 0 && isSettingTitle(settingLines[0])) {
	    final String settingTitle = getSettingTitle(settingLines[0]);

	    if (StringUtils.isNotBlank(settingTitle)) {

		final MovieSetting movieSetting = getMovieSetting(settingTitle);

		final String[] range = Arrays.copyOfRange(settingLines, 1, settingLines.length);

		final MovieCharacterExtractor characterExtractor = new MovieCharacterExtractor();
		final Collection<String> phrases = characterExtractor.extractCharactersNames(range);
		phrases.forEach(k -> {
		    if (characters.containsKey(k)) {
			characters.get(k).getSettings().add(movieSetting);
		    } else {
			final MovieCharacter movieCharacter = new MovieCharacter(k);
			movieCharacter.getSettings().add(movieSetting);
			characters.put(k, movieCharacter);
		    }
		});
	    }
	}

    }

    private MovieSetting getMovieSetting(final String settingTitle) {
	MovieSetting movieSetting = this.rep.findByName(settingTitle);
	if (movieSetting == null) {
	    movieSetting = new MovieSetting(settingTitle);
	    this.rep.save(movieSetting);
	}
	return movieSetting;
    }

    private boolean isSettingTitle(final String string) {
	return StringUtils.indexOfAny(string, SCRIPT_TITLES_PREFIXES) == 0;
    }

    private String getSettingTitle(final String str) {
	final Pattern pattern = Pattern.compile(SETTING_REGEX);
	final Matcher matcher = pattern.matcher(str);
	if (matcher.find()) {
	    final int indexOfAny = StringUtils.indexOfAny(str, "\r\n", "\n", "\r", " - ");
	    if (indexOfAny > -1) {
		final String title = str.substring(matcher.end(), indexOfAny).trim();
		return title;
	    } else {
		final String title = str.substring(matcher.end(), str.length()).trim();
		return title;
	    }
	}
	return null;
    }

}
