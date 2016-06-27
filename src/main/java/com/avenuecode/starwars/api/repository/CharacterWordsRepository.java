package com.avenuecode.starwars.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.model.WordCount;

public interface CharacterWordsRepository extends PagingAndSortingRepository<WordCount, Integer> {
    List<WordCount> findByCharacter(MovieCharacter character, Pageable pageable);
}