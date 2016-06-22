package com.avenuecode.starwars.api.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.repository.MovieCharacterRepository;

@Service
public class MovieCharacterService {

    @Autowired
    private MovieCharacterRepository rep;

    public MovieCharacter getOne(Integer id) {
	return this.rep.findOne(id);
    }

    public Collection<MovieCharacter> listAll() {

	final Collection<MovieCharacter> collection = makeCollection(this.rep.findAll());
	return collection;

    }

    public boolean save(MovieCharacter mc) {
	this.rep.save(mc);
	return true;
    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
	Collection<E> list = new ArrayList<E>();
	if (iter != null) {
	    for (E item : iter) {
		list.add(item);
	    }
	}
	return list;
    }
}
