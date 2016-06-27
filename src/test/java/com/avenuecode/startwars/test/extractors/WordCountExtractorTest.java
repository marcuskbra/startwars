package com.avenuecode.startwars.test.extractors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.avenuecode.starwars.Application;
import com.avenuecode.starwars.api.service.extractors.WordCountExtractor;
import com.avenuecode.starwars.data.model.WordCount;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class WordCountExtractorTest {

    private WordCountExtractor extractor = new WordCountExtractor();

    @Test
    public void testSimpleWords() {

	String string1 = "I see, sir. I see, sir Luke.";

	Collection<WordCount> extracted = this.extractor.extract(null,string1);

	WordCount expected1 = new WordCount("i", 2);
	WordCount expected2 = new WordCount("see", 2);
	WordCount expected3 = new WordCount("sir", 2);
	WordCount expected4 = new WordCount("luke", 1);
	assertThat(extracted, hasSize(4));
	assertThat(extracted, hasItems(expected1, expected2, expected3, expected4));
    }

    @Test
    public void testPunctuationWords() {

	String string1 = "Artoo-Detoo! It's you! It's you!";

	Collection<WordCount> extracted = this.extractor.extract(null,string1);

	WordCount expected1 = new WordCount("it's", 2);
	WordCount expected2 = new WordCount("you", 2);
	WordCount expected3 = new WordCount("artoo", 1);
	WordCount expected4 = new WordCount("detoo", 1);
	assertThat(extracted, hasSize(4));
	assertThat(extracted, hasItems(expected1, expected2, expected3, expected4));
    }

    @Test
    public void testWordWithSpecialCharacters() {
	String pathname = "screenplay.txt";
	URL systemResource = ClassLoader.getSystemResource(pathname);
	String movieScript = null;
	try {
	    movieScript = IOUtils.toString(systemResource, Charset.forName("UTF-8"));
	} catch (IOException e) {
	    fail(e.getMessage());
	}
	
	Map<String, Integer> words = this.extractor.mapAndCount(movieScript);
	assertThat(words.size(), greaterThan(0));
	words.forEach((k,v) -> {
	    assertThat(k, not(isEmptyOrNullString()));
	    assertThat(k,
		    not(stringContainsInOrder(Arrays.asList("?", "-", ",", ";", ":", "_", "\"", ".", "!", "~", "*", "(", ")"))));
	    assertThat(v, not(nullValue()));
	    assertThat(v, greaterThan(0));
	});
    }
    
    @Test
    public void testWordNullAndEmpty() {
	Map<String, Integer> words = this.extractor.mapAndCount(null);
	assertThat(words.size(), equalTo(0));
	
	words = this.extractor.mapAndCount("");
	assertThat(words.size(), equalTo(0));

	words = this.extractor.mapAndCount(" ");
	assertThat(words.size(), equalTo(0));

    }

}
