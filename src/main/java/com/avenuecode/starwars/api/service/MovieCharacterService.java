package com.avenuecode.starwars.api.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.avenuecode.starwars.api.model.MovieCharacter;

@Service
@Configurable
public class MovieCharacterService {

    public MovieCharacter getOne(Integer id) {
	return null;
    }

    public Collection<MovieCharacter> listAll() {

	Collection<MovieCharacter> r = new ArrayList<>();
	return r;

    }

}
