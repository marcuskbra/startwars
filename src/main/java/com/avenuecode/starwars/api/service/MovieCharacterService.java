package com.avenuecode.starwars.api.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.WordCount;
import com.avenuecode.starwars.data.repository.CharacterWordsRepository;
import com.avenuecode.starwars.data.repository.MovieCharacterRepository;

@Service
@Transactional(readOnly = true)
public class MovieCharacterService {

    @Autowired
    private MovieCharacterRepository characterRepository;

    @Autowired
    private CharacterWordsRepository wordsRepository;

    public MovieCharacter getOne(Integer id) {
	MovieCharacter character = this.characterRepository.findOne(id);
	if (character == null) {
	    final String msg = "Movie character with id %s not found";
	    throw new IllegalArgumentException(String.format(msg, id));
	}
	setWordCounts(character);
	return character;
    }

    private void setWordCounts(MovieCharacter character) {
	List<WordCount> words = findCharacterWords(character);
	character.setWordCounts(words);
    }

    public Collection<MovieCharacter> findMovieSettingId(Integer id) {
	List<MovieCharacter> settings = this.characterRepository.findBySettingsId(id);
	for (MovieCharacter character : settings) {
	    setWordCounts(character);
	}
	return settings;
    }

    private List<WordCount> findCharacterWords(MovieCharacter character) {
	List<WordCount> words = this.wordsRepository.findTop10ByCharacterOrderByCountDesc(character);
	return words;
    }

    public Iterable<MovieCharacter> listAll() {
	Iterable<MovieCharacter> characters = this.characterRepository.findAll();
	for (MovieCharacter character : characters) {
	    setWordCounts(character);
	}
	return characters;

    }

}
