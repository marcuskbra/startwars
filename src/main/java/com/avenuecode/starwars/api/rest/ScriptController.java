package com.avenuecode.starwars.api.rest;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.starwars.api.service.MovieScriptService;
import com.avenuecode.starwars.data.model.MovieScript;

@RestController
public class ScriptController {

    @Autowired
    private MovieScriptService service;

    @RequestMapping(path = "/script", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Map<String, String> postScript(@RequestBody(required = true) String script) {

	final String md5 = DigestUtils.md5Hex(script);
	final MovieScript movieScript = new MovieScript(md5, script);
	
	this.service.processMovieScript(movieScript);

	final Map<String, String> response = new HashMap<>();
	response.put("message", "Movie script successfully received");
	return response;
    }
}
