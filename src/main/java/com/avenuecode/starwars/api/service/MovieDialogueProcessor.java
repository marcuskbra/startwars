package com.avenuecode.starwars.api.service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrTokenizer;

import com.avenuecode.starwars.api.model.MovieScript;

public class MovieDialogueProcessor {

    public static String scene_regex = "^INT./EXT.|^INT.|^EXT.";

    public void process(MovieScript script) {
	String content = script.getContent();
	StrTokenizer st = new StrTokenizer(content);
	StringUtils.split(content);
	content.split("\\r\\n|\\n|\\r");
	
    }
    
    public static void main2(String[] args) {
        // using pattern with flags
	
	String ex1 = "INT. REBEL BLOCKADE RUNNER - MAIN PASSAGEWAY"
		+ "\n\nAn explosion rocks the ship as two robots, Artoo-Detoo (R2-";
	String ex2 = "EXT. REBEL BLOCKADE RUNNER - MAIN PASSAGEWAY";
	String ex3 = "INT./EXT. REBEL BLOCKADE RUNNER - MAIN PASSAGEWAY";
	
	getSceneTitle(ex1);
	getSceneTitle(ex2);
	getSceneTitle(ex3);
	getSceneText(ex1);
	getSceneText(ex2);
        getSceneText(ex3);

        Collection<String> titles = new LinkedList<>();
        
        String[] split = ex1.split(scene_regex);
        for (String str : split) {
	    if (!StringUtils.isEmpty(str)) {
		String sceneTitle = getSceneTitle(str);
		titles.add(sceneTitle);
	    }
	}
        
    }
    
    private static String getSceneTitle(String str) {
	Pattern pattern = Pattern.compile(scene_regex);
	Matcher matcher = pattern.matcher(str);
	if (matcher.find()) {
	    System.out.println("Found the text \"" + matcher.group()
		    + "\" starting at " + matcher.start()
		    + " index and ending at index " + matcher.end());
	    String substring1 = str.substring(matcher.end(), str.indexOf(" - ", matcher.end())).trim();
	    System.out.println(substring1);
	    return substring1;
	}
	return str;
    }

    private static String getSceneText(String str) {
	Pattern pattern = Pattern.compile(scene_regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("Found the text \"" + matcher.group()
                    + "\" starting at " + matcher.start()
                    + " index and ending at index " + matcher.end());
            String substring1 = str.substring(matcher.end(), str.length()).trim();
            System.out.println(substring1);
            return substring1;
        }
	return str;
    }
}
