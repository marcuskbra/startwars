package com.avenuecode.starwars.api.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
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

    @RequestMapping("/")
    public String index() {
	return MSG;
    }

    @RequestMapping(path = "/script", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Map<String, String> postScript(@RequestBody(required = true) String script) {

	
	final Map<String, String> response = new HashMap<>();
	response.put("message", "Movie script successfully received");
	return response;
    }
}
