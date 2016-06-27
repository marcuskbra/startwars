package com.avenuecode.starwars.data.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.avenuecode.starwars.data.model.MovieCharacter;
import com.avenuecode.starwars.data.model.WordCount;


public interface CharacterWordsRepository extends PagingAndSortingRepository<WordCount, Integer> {
    Pageable PAGEABLE = new PageRequest(1, 10, Direction.DESC, "count");
    
    List<WordCount> findByCharacter(MovieCharacter character, Pageable pageable);
    
    List<WordCount> findTop10ByCharacterOrderByCountDesc(MovieCharacter character);

    List<WordCount> findTop10ByOrderByCountDesc();
    
}