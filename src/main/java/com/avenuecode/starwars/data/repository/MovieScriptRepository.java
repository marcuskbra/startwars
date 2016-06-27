package com.avenuecode.starwars.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.avenuecode.starwars.data.model.MovieScript;

public interface MovieScriptRepository extends CrudRepository<MovieScript, String> {

}