package com.avenuecode.startwars.test.extractors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.avenuecode.starwars.Application;
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
	Collection<String> extracted = callExtractor(pathname);
	Collection<String> expectedList = new HashSet<>();
	expectedList.add("THREEPIO");
	assertThat(extracted, equalTo(expectedList));
    }
    
    @Test
    public void testFullScript() throws Exception {
	String pathname = "screenplay.txt";
	Collection<String> extracted = callExtractor(pathname);
	assertThat(extracted, containsInAnyOrder("LUKE", "WILLARD", "DEATH STAR INTERCOM VOICE", "MOTTI",
		"RED TEN", "BIGGS", "CHIEF PILOT", "WOMAN", "SECOND OFFICER", "GOLD TWO", "HUMAN", "INTERCOM VOICE",
		"CREATURE", "TARKIN", "TECHNICIAN", "FIXER", "RED NINE", "MASSASSI INTERCOM VOICE", "FIRST OFFICER",
		"TROOPER", "AUNT BERU", "DODONNA", "RED LEADER", "OWEN", "OFFICER CASS", "GANTRY OFFICER", "MAN",
		"VADER", "PORKINS", "CAPTAIN", "REBEL OFFICER", "CONTROL OFFICER", "THREEPIO", "CAMIE",
		"SECOND TROOPER", "READ LEADER", "GOLD FIVE", "RED ELEVEN", "BARTENDER", "FIRST TROOPER",
		"GOLD LEADER", "BERU", "BASE VOICE", "GREEDO", "COMMANDER", "JABBA", "IMPERIAL OFFICER",
		"TROOPER VOICE", "TAGGE", "BEN", "OFFICER", "RED SEVEN", "LEIA", "VOICE", "WINGMAN", "DEAK", "HAN",
		"CHIEF", "ASTRO-OFFICER", "WEDGE" ));
	assertThat(extracted, hasSize(60));
    }

    private Collection<String> callExtractor(String pathname) {
	URL systemResource = ClassLoader.getSystemResource(pathname);
	String movieScript = null;
	try {
	    movieScript = IOUtils.toString(systemResource, Charset.forName("UTF-8"));
	    Collection<String> extracted = this.extractor.extractCharactersNames(movieScript.split(NEW_LINE_REGEX));
	    return extracted;
	} catch (IOException e) {
	    fail(e.getMessage());
	}
	return null;
    }

    
    @Test
    public void testInvalidStrings() {
	
	String pathname = "movie-parts.txt";
	Collection<String> words = callExtractor(pathname);
	
	assertThat(words.size(), equalTo(0));
	
    }
    
    @Test
    public void testNullAndEmpty() {
	Set<String> words = this.extractor.extractCharactersNames(null);
	assertThat(words.size(), equalTo(0));
	
	words = this.extractor.extractCharactersNames(new String[]{""});
	assertThat(words.size(), equalTo(0));
	
	words = this.extractor.extractCharactersNames(new String[]{});
	assertThat(words.size(), equalTo(0));

	words = this.extractor.extractCharactersNames(new String[]{" "});
	assertThat(words.size(), equalTo(0));

    }
}
