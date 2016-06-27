package com.avenuecode.startwars.test.extractors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.avenuecode.starwars.Application;
import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.service.extractors.MovieCharacterExtractor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MovieCharacterExtractorTest {

    private static final String NEW_LINE_REGEX = "\\r\\n|\\n|\\r";

    private MovieCharacterExtractor extractor = new MovieCharacterExtractor();

    @Test
    public void testPartialScript() throws Exception {
	// String pathname = "screenplay.txt";
	String pathname = "movie-setting-1.txt";
	URL systemResource = ClassLoader.getSystemResource(pathname);
	String movieScript = IOUtils.toString(systemResource, Charset.forName("UTF-8"));
	Collection<String> extracted = this.extractor.extractCharactersNames(movieScript.split(NEW_LINE_REGEX));
	MovieCharacter expected = new MovieCharacter();
	expected.setId(0);
	expected.setName("THREEPIO");
	Collection<MovieCharacter> expectedList = new HashSet<MovieCharacter>();
	expectedList.add(expected);
	assertThat(expectedList, is(extracted));
    }

}
