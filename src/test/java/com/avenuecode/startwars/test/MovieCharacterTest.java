package com.avenuecode.startwars.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.avenuecode.starwars.Application;
import com.avenuecode.starwars.api.rest.MovieCharacterController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MovieCharacterTest {

    private MockMvc mvc;

    @Autowired
    private MovieCharacterController svc;
    
    @Before
    public void setUp() throws Exception {
	this.mvc = MockMvcBuilders.standaloneSetup(this.svc).build();
    }

    
    @Test
    public void listCharacters() throws Exception {
	this.mvc.perform(get("/characters/")
		.accept(MediaType.APPLICATION_JSON_UTF8))
	.andExpect(status().isOk());
    }
    
    @Test
    public void getCharacter() throws Exception {
	this.mvc.perform(get("/characters/1")
		.accept(MediaType.APPLICATION_JSON_UTF8))
	.andExpect(status().isOk());
    }

}
