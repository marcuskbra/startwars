package com.avenuecode.startwars.test;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.avenuecode.starwars.Application;
import com.avenuecode.starwars.api.model.MovieScript;
import com.avenuecode.starwars.api.model.MovieSetting;
import com.avenuecode.starwars.api.service.MovieDialogueProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ScriptProcessorTest {
    
    @Autowired
    private MovieDialogueProcessor proc;

    @Before
    public void setUp() throws Exception {
	MockMvcBuilders.standaloneSetup(this.proc).build();
    }

    @Test
    public void postScript() throws Exception {
	
	String movieScript = "INT. REBEL BLOCKADE RUNNER - MAIN PASSAGEWAY";
	postScript(movieScript);
    }
    
    @Test
    public void postScriptFull() throws Exception {
	String pathname = "screenplay.txt";
	URL systemResource = ClassLoader.getSystemResource(pathname);
	String movieScript = IOUtils.toString(systemResource, Charset.forName("UTF-8"));
	Collection<MovieSetting> postScript = postScript(movieScript);
    }

    private Collection<MovieSetting> postScript(String movieScript) throws Exception {
	MovieScript ms = new MovieScript(movieScript);
	Collection<MovieSetting> processed = this.proc.process(ms);
	return processed;
    }
}
