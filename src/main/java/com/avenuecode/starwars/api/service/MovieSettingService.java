package com.avenuecode.starwars.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.MovieSetting;
import com.avenuecode.starwars.data.model.WordCount;
import com.avenuecode.starwars.data.repository.CharacterWordsRepository;
import com.avenuecode.starwars.data.repository.MovieSettingRepository;

@Service
@Transactional(readOnly = true)
public class MovieSettingService {

    @Autowired
    private MovieSettingRepository rep;

    @Autowired
    private CharacterWordsRepository wordsRepository;

    @Autowired
    private MovieCharacterService svc;

    @PersistenceContext
    private EntityManager em;

    public MovieSetting getOne(Integer id) {
	return findOne(id);
    }

    public Collection<MovieSetting> listAll() {

	final Collection<MovieSetting> collection = makeCollection(this.rep.findAll());
	return collection;

    }

    public MovieSetting findOne(int id) {

	TypedQuery<MovieSetting> query = this.em.createQuery(
		"select NEW MovieSetting(ms.id, ms.name) from MovieSetting ms where ms.id = ?1", MovieSetting.class);
	query.setParameter(1, id);

	final MovieSetting setting = query.getSingleResult();

	final Collection<MovieCharacter> characters = findCharacters(setting);
	setting.setCharacters(characters);

	return setting;
    }

    public List<MovieSetting> findAll() {

	TypedQuery<MovieSetting> query = this.em
		.createQuery("select NEW MovieSetting(ms.id, ms.name) from MovieSetting ms", MovieSetting.class);

	final List<MovieSetting> settings = query.getResultList();

	for (MovieSetting setting : settings) {
	    final Collection<MovieCharacter> characters = findCharacters(setting);
	    setting.setCharacters(characters);
	}

	return settings;
    }

    public Collection<MovieCharacter> findCharacters(MovieSetting setting) {
	final Collection<MovieCharacter> chars = this.svc.findMovieSettingId(setting.getId());

	for (MovieCharacter character : chars) {
	    List<WordCount> words = this.wordsRepository.findTop10ByCharacterOrderByCountDesc(character);
	    character.setWordCounts(words);
	}
	return chars;
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
