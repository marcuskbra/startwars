package com.avenuecode.startwars.test.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.avenuecode.starwars.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllersTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }
    
    @Test
    public void doPostScriptFull() throws Exception {
	String pathname = "screenplay.txt";
	URL systemResource = ClassLoader.getSystemResource(pathname);
	String movieScript = IOUtils.toString(systemResource, Charset.defaultCharset());
	this.mvc.perform(post("/script")
		.content(movieScript)
		.contentType(MediaType.TEXT_PLAIN)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		//.andDo(print())
		.andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(content().json("{\"message\":\"Movie script successfully received\"}"));

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
    
    
    @Test
    public void getNotFoundCharacter() throws Exception {
	this.mvc.perform(get("/characters/1999")
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isNotFound());
    }
    
    @Test
    public void getInvalidCharacter() throws Exception {
	this.mvc.perform(get("/characters/199asd")
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isInternalServerError());
    }
    
    @Test
    public void listSettings() throws Exception {
	this.mvc.perform(get("/settings/")
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
    }
    
    @Test
    public void getSetting() throws Exception {
	this.mvc.perform(get("/settings/1")
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
    }
    
    
    @Test
    public void getNotFoundSetting() throws Exception {
	this.mvc.perform(get("/settings/999")
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isNotFound());
    }
    
    @Test
    public void getInvalidSetting() throws Exception {
	this.mvc.perform(get("/settings/999asd")
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isInternalServerError());
    }


    @Test
    public void postScriptFullAgain() throws Exception {
	String pathname = "screenplay.txt";
	URL systemResource = ClassLoader.getSystemResource(pathname);
	String movieScript = IOUtils.toString(systemResource, Charset.defaultCharset());
	this.mvc.perform(post("/script")
		.content(movieScript)
		.contentType(MediaType.TEXT_PLAIN)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		//.andDo(print())
		.andExpect(status().isForbidden())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(content().json("{\"message\":\"Movie script already received\"}"));
    }
    
    @Test
    public void postInvalidScript() throws Exception {

	this.mvc.perform(post("/script")
		.contentType(MediaType.TEXT_PLAIN)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(status().isInternalServerError())
		.andExpect(content().string("{\"message\":\"Unexpected error\"}"));
    }
}
