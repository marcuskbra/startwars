package com.avenuecode.startwars.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.avenuecode.starwars.api.rest.ScriptController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ScriptControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
	this.mvc = MockMvcBuilders.standaloneSetup(new ScriptController()).build();
    }

    @Test
    public void postScript() throws Exception {

	this.mvc.perform(post("/script")
		.content("INT. REBEL BLOCKADE RUNNER - MAIN PASSAGEWAY")
		.contentType(MediaType.TEXT_PLAIN)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().json("{\"message\":\"Movie script successfully received\"}"));
    }
}
