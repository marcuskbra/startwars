package com.avenuecode.starwars.api.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.starwars.api.model.MovieScript;
import com.avenuecode.starwars.api.service.MovieDialogueProcessor;

@RestController
public class ScriptController {

    private static final String MSG = "May the Force be with you!";
    
    @Autowired
    private MovieDialogueProcessor processor;

    @RequestMapping("/")
    public String index() {
	return MSG;
    }

    @RequestMapping(path = "/script", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Map<String, String> postScript(@RequestBody(required = true) String script) {

	this.processor.process(new MovieScript(script));
	
	final Map<String, String> response = new HashMap<>();
	response.put("message", "Movie script successfully received");
	return response;
    }
}
