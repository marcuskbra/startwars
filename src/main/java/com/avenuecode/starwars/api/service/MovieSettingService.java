package com.avenuecode.starwars.api.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.api.exception.ItemNotFoundException;
import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.MovieSetting;
import com.avenuecode.starwars.data.repository.MovieSettingRepository;

@Service
@Transactional(readOnly = true)
public class MovieSettingService {

    @Autowired
    private MovieSettingRepository settingRepository;

    @Autowired
    private MovieCharacterService characterService;

    public MovieSetting getOne(final Integer id) {
	final MovieSetting setting = this.settingRepository.findOne(id);
	if (setting == null) {
	    final String msg = "Movie setting with id %s not found";
	    throw new ItemNotFoundException(String.format(msg, id));
	}
	final Collection<MovieCharacter> characters = findCharacters(setting);
	setting.setCharacters(characters);
	return setting;
    }

    public Iterable<MovieSetting> findAll() {

	final Iterable<MovieSetting> settings = this.settingRepository.findAll();
	for (final MovieSetting setting : settings) {
	    final Collection<MovieCharacter> characters = findCharacters(setting);
	    setting.setCharacters(characters);
	}

	return settings;
    }

    private Collection<MovieCharacter> findCharacters(final MovieSetting setting) {
	return this.characterService.findMovieSettingId(setting.getId());
    }
}
