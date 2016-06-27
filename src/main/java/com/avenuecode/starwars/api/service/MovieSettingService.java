package com.avenuecode.starwars.api.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.starwars.api.model.MovieSetting;
import com.avenuecode.starwars.api.repository.MovieSettingRepository;

@Service
@Transactional(readOnly=true)
public class MovieSettingService {

    @Autowired
    private MovieSettingRepository rep;

    public MovieSetting getOne(Integer id) {
	return this.rep.findOne(id);
    }

    public Collection<MovieSetting> listAll() {

	final Collection<MovieSetting> collection = makeCollection(this.rep.findAll());
	return collection;

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
