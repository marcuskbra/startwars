package com.avenuecode.starwars.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.avenuecode.starwars.api.model.MovieCharacter;

public interface MovieCharacterRepository extends PagingAndSortingRepository<MovieCharacter, Integer> {
}