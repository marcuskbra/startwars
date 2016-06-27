package com.avenuecode.starwars.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.avenuecode.starwars.data.model.MovieSetting;

public interface MovieScriptRepository extends CrudRepository<MovieSetting, Integer> {
}