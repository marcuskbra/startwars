package com.avenuecode.starwars.data.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.WordCount;

public interface CharacterWordsRepository extends PagingAndSortingRepository<WordCount, Integer> {

    @Transactional(readOnly = true)
    List<WordCount> findTop10ByCharacterOrderByCountDesc(MovieCharacter character);

}