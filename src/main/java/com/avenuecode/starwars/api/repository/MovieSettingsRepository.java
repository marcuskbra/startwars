package com.avenuecode.starwars.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.avenuecode.starwars.api.model.MovieSetting;

public interface MovieSettingsRepository extends CrudRepository<MovieSetting, Integer> {
}