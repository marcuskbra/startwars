package com.avenuecode.starwars.api.service.extractors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.avenuecode.starwars.api.model.WordCount;

public class WordCountExtractor implements Extractor<String, Collection<WordCount>> {

    private static final String REGEX = "[\\s\\?\\-,_\".!~*()]";
    private final Extractor<?, ?> delegate;

    public WordCountExtractor(final Extractor<?, ?> delegate) {
	this.delegate = delegate;
    }

    @Override
    public Collection<WordCount> extract(final String words) {

	Map<String, Integer> counts = mapAndCount(words);
	List<WordCount> result = new ArrayList<WordCount>();
	counts.forEach((k, v) -> {
	    result.add(new WordCount(k, v));
	});

	if (this.delegate != null) {
	    this.delegate.extract(null);
	}

	return result;
    }

    public Map<String, Integer> mapAndCount(final String words) {
	if (StringUtils.isNotBlank(words)) {
	    String[] splited = words.toLowerCase().split(REGEX);
	    Map<String, Integer> counts = Arrays.asList(splited).stream()
		    .filter(s -> StringUtils.isNotBlank(s))
		    .collect(Collectors
			    .toMap(w -> w, 
				    w -> 1, Integer::sum));
	    return counts;
	} 

	return Collections.emptyMap();
    }

}
