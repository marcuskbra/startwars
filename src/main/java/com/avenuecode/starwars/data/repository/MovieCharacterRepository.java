package com.avenuecode.starwars.data.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.avenuecode.starwars.data.model.MovieCharacter;

public interface MovieCharacterRepository extends PagingAndSortingRepository<MovieCharacter, Integer> {
    List<MovieCharacter> findBySettingsId(int id);
}