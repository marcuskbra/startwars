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

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.model.WordCount;
import com.avenuecode.starwars.api.repository.CharacterWordsRepository;
import com.avenuecode.starwars.api.repository.MovieCharacterRepository;

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
	
	Pageable pageable = createPageable();
	final Collection<MovieCharacter> collection = makeCollection(this.characterRepository.findBySettingsId(id));
	for (MovieCharacter character : collection) {
	    List<WordCount> words = this.wordsRepository.findByCharacter(character, pageable);
	    character.setWordCounts(words);
	}
	return collection;
	
    }

    public Collection<MovieCharacter> listAll() {

	Pageable pageable = createPageable();
	final Collection<MovieCharacter> collection = makeCollection(this.characterRepository.findAll());
	for (MovieCharacter character : collection) {
	    List<WordCount> words = this.wordsRepository.findByCharacter(character, pageable);
	    character.setWordCounts(words);
	}
	return collection;

    }

    public Pageable createPageable() {
	return new PageRequest(1, 10, Sort.Direction.DESC, "count", "word");
    }

    public boolean save(MovieCharacter mc) {
	this.characterRepository.save(mc);
	return true;
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
