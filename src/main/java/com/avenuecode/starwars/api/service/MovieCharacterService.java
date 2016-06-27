package com.avenuecode.starwars.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.WordCount;
import com.avenuecode.starwars.data.repository.CharacterWordsRepository;
import com.avenuecode.starwars.data.repository.MovieCharacterRepository;

@Service
public class MovieCharacterService {

    @Autowired
    private MovieCharacterRepository characterRepository;

    @Autowired
    private CharacterWordsRepository wordsRepository;

    @PersistenceContext
    private EntityManager em;

    public MovieCharacter getOne(Integer id) {
	return this.characterRepository.findOne(id);
    }
    
    public Collection<MovieCharacter> findMovieSettingId(Integer id) {
	
	final Collection<MovieCharacter> collection = makeCollection(this.characterRepository.findBySettingsId(id));
	for (MovieCharacter character : collection) {
	    List<WordCount> words = this.wordsRepository.findByCharacter(character, createPageRequest());
	    character.setWordCounts(words);
	}
	return collection;
	
    }

    private Pageable createPageRequest() {
	return new PageRequest(1, 10, new Sort(Sort.Direction.DESC, "count").and(new Sort(Sort.Direction.ASC, "word")));
    }
    
    public Collection<MovieCharacter> listAll() {

	final Collection<MovieCharacter> collection = makeCollection(this.characterRepository.findAll());
	for (MovieCharacter character : collection) {
	    List<WordCount> words = this.wordsRepository.findTop10ByCharacterOrderByCountDesc(character);
	    character.setWordCounts(words);
	}
	return collection;

    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
	Collection<E> list = new ArrayList<E>();
	if (iter != null) {
	    for (E item : iter) {
		list.add(item);
	    }
	}
	return list;
    }
}
