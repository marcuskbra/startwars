package com.avenuecode.starwars.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.avenuecode.starwars.api.model.MovieCharacter;

public interface MovieCharacterRepository extends CrudRepository<MovieCharacter, Integer> {
}