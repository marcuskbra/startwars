package com.avenuecode.starwars.api.service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.stereotype.Service;

import com.avenuecode.starwars.api.model.MovieScript;
import com.avenuecode.starwars.api.service.extractors.MovieSettingExtractor;

@Service
public class MovieDialogueProcessor {

    public static final String NEW_LINE_REGEX = "\\r\\n|\\n|\\r";
    private static final String SETTING_REGEX = "\\b(INT\\.{1}\\/{1}EXT.\\s)\\b|\\b(INT.\\s)\\b|[^/]\\b(EXT.\\s)\\b";
    private static final String SETTING_REGEX_LOOKAHEAD = "(?=" + SETTING_REGEX + ")";

    
    public boolean process(MovieScript script) {
	String content = script.getContent();
	String[] splited = content.split(SETTING_REGEX_LOOKAHEAD);

//	String[] split = content.split(SETTING_REGEX);
	for (String settingString : splited) {

	    MovieSettingExtractor settingExtractor = new MovieSettingExtractor(null);
	    String[] settingLines = settingString.split(NEW_LINE_REGEX);
	    settingExtractor.extract(settingLines);
	}

	return splited.length > 0;
    }

    private void doProcess(String script) {

	SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]")
		.set("spark.executor.memory", "1g");
	JavaSparkContext sc = new JavaSparkContext(conf);

	Pattern pattern = Pattern.compile(MovieDialogueProcessor.SETTING_REGEX_LOOKAHEAD, Pattern.MULTILINE);

	List<String> list = Arrays.asList(pattern.split(script));

	JavaRDD<String> scripts = sc.parallelize(list);

	
	    
	    try {
		String sceneText = getSettingText(script);
		System.out.println("=================================================================");
		System.out.println(sceneText);
		// doProcess(content);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

    }

    private static String getSettingText(String str) {
	Pattern pattern = Pattern.compile(SETTING_REGEX_LOOKAHEAD);
	Matcher matcher = pattern.matcher(str);
	if (matcher.find()) {
	    System.out.println("Found the text \"" + matcher.group() + "\" starting at " + matcher.start()
		    + " index and ending at index " + matcher.end());
	    String substring1 = str.substring(matcher.end(), str.length()).trim();
	    System.out.println(substring1);
	    return substring1;
	}
	return "";
    }
    
}
