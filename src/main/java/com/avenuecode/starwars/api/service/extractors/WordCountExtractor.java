package com.avenuecode.starwars.api.service.extractors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.model.WordCount;

@Component
public class WordCountExtractor {

    private static final String REGEX = "[\\s\\?\\-,;_\".!~*()]";

    public Collection<WordCount> extract(MovieCharacter movieCharacter, final String words) {

	final Map<String, Integer> counts = mapAndCount(words);
	final List<WordCount> result = new ArrayList<WordCount>();
	counts.forEach((k, v) -> {
	    WordCount w = new WordCount(k, v);
	    w.setCharacter(movieCharacter);
	    result.add(w);
	});


	return result;
    }

    public Map<String, Integer> mapAndCount(final String words) {
	if (StringUtils.isNotBlank(words)) {
	    final String[] splited = words.toLowerCase().split(REGEX);
	    final Map<String, Integer> counts = Arrays.asList(splited).stream()
		    .filter(w -> StringUtils.isNotBlank(w))
		    .collect(Collectors
			    .toMap(w -> w, w -> 1, Integer::sum));
	    return counts;
	} 

	return Collections.emptyMap();
    }

}
