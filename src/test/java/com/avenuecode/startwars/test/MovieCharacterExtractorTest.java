package com.avenuecode.startwars.test;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.avenuecode.starwars.Application;
import com.avenuecode.starwars.api.service.MovieDialogueProcessor;
import com.avenuecode.starwars.api.service.extractors.MovieCharacterExtractor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MovieCharacterExtractorTest {

    private MovieCharacterExtractor extractor = new MovieCharacterExtractor(null);

    @Test
    public void textPartialScript() throws Exception {
	String pathname = "screenplay.txt";
	URL systemResource = ClassLoader.getSystemResource(pathname);
	String movieScript = IOUtils.toString(systemResource, Charset.forName("UTF-8"));
	String extracted = this.extractor.extract(movieScript.split(MovieDialogueProcessor.NEW_LINE_REGEX));
	System.out.println(extracted);
    }

}
