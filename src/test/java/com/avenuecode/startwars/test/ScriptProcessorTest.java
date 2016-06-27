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
import com.avenuecode.starwars.api.service.MovieDialogueProcessor;
import com.avenuecode.starwars.data.model.MovieScript;
import com.avenuecode.starwars.data.model.MovieSetting;

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
	postScript(movieScript);
    }

    private Collection<MovieSetting> postScript(String movieScript) throws Exception {
	MovieScript ms = new MovieScript("123", movieScript);
	this.proc.process(ms);
	return null;
    }
}
