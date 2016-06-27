package com.avenuecode.starwars.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.data.model.MovieCharacter;

public interface MovieCharacterRepository extends CrudRepository<MovieCharacter, Integer> {

    @Transactional(readOnly = true)
    List<MovieCharacter> findBySettingsId(int id);

    @Override
    @Transactional(readOnly = true)
    @Query("select new MovieCharacter(m.id, m.name) from MovieCharacter m where m.id = :id")
    MovieCharacter findOne(@Param("id") Integer id);

}