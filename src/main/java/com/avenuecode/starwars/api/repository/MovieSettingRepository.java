package com.avenuecode.starwars.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.api.model.MovieSetting;

@Transactional(readOnly=true)
public interface MovieSettingRepository extends CrudRepository<MovieSetting, Integer> {
    MovieSetting findByName(String name);
}