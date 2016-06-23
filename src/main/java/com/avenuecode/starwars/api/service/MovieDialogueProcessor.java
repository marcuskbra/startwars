package com.avenuecode.starwars.api.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.stereotype.Service;

import com.avenuecode.starwars.api.model.MovieScript;

@Service
public class MovieDialogueProcessor {

    public static final String SCENE_REGEX = "\\b(INT.\\/EXT.\\s)|(INT.\\s)|(EXT.\\s)\\b.*?\\b";
    public static final String SCENE_REGEX_LOOKAHEAD = "(?=" + SCENE_REGEX + ")";

    private static final String[] SCRIPT_TITLES_PREFIXES = new String[] { "INT. ", "EXT. ", "INT./EXT. " };
    private static final String CHARACTER_NAME_PREFIX = "                      ";

    public boolean process(MovieScript script) {
	String content = script.getContent();
	String[] splited = content.split(SCENE_REGEX_LOOKAHEAD);

	// String[] split = content.split(SCENE_REGEX);
	for (String scene : splited) {

	    String[] lines = scene.split("\\r\\n|\\n|\\r");
	    String currentSettingTitle = null;

	    for (String line : lines) {
		final boolean isSettingTitleLine = isSettingTitle(line);
		if (isSettingTitleLine) {
		    currentSettingTitle = getSettingTitle(scene);
		    System.out.println(currentSettingTitle);
		    //save setting?
		    
		} else if (currentSettingTitle != null) {
		    
		    if (isCharacterName(line)){
			final String characterName = line.trim();
			
			System.out.println(characterName);
		    }
		}
	    }

	    try {
		String sceneText = getSettingText(scene);
		System.out.println("=================================================================");
		System.out.println(sceneText);
		// doProcess(content);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	return splited.length > 0;
    }

    private boolean isCharacterName(String line) {
	return line.startsWith(CHARACTER_NAME_PREFIX) && StringUtils.isNotBlank(line);
    }

    private boolean isSettingTitle(String string) {
	return StringUtils.indexOfAny(string, SCRIPT_TITLES_PREFIXES) == 0;
    }

    private void doProcess(String script) {

	SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]")
		.set("spark.executor.memory", "1g");
	JavaSparkContext sc = new JavaSparkContext(conf);

	Pattern pattern = Pattern.compile(MovieDialogueProcessor.SCENE_REGEX_LOOKAHEAD, Pattern.MULTILINE);

	List<String> list = Arrays.asList(pattern.split(script));

	JavaRDD<String> scripts = sc.parallelize(list);

    }

    private static String getSettingTitle(String str) {
	Pattern pattern = Pattern.compile(SCENE_REGEX);
	Matcher matcher = pattern.matcher(str);
	if (matcher.find()) {
	    int indexOfAny = StringUtils.indexOfAny(str, "\r\n", "\n", "\r", " - ");
	    if (indexOfAny > -1) {
		String title = str.substring(matcher.end(), indexOfAny).trim();
		return title;
	    }
	}
	return "";
    }

    private static String getSettingText(String str) {
	Pattern pattern = Pattern.compile(SCENE_REGEX_LOOKAHEAD);
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
