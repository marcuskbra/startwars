package com.avenuecode.starwars.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.data.model.MovieSetting;

public interface MovieSettingRepository extends CrudRepository<MovieSetting, Integer> {

    @Transactional(readOnly = true)
    MovieSetting findByName(String name);

    @Override
    @Transactional(readOnly = true)
    @Query("select new MovieSetting(ms.id, ms.name) from MovieSetting ms where ms.id = :id")
    MovieSetting findOne(@Param("id") Integer id);

    @Override
    @Transactional(readOnly = true)
    @Query("select new MovieSetting(ms.id, ms.name) from MovieSetting ms")
    public Iterable<MovieSetting> findAll();
}