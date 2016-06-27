package com.avenuecode.starwars.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.starwars.api.service.MovieCharacterService;
import com.avenuecode.starwars.data.model.MovieCharacter;

@RestController
@RequestMapping(path = "/characters")
public class MovieCharacterController {

    @Autowired
    private MovieCharacterService characterService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Iterable<MovieCharacter> listAll() {
	Iterable<MovieCharacter> listAll = this.characterService.listAll();
	return listAll;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody MovieCharacter get(@PathVariable Integer id) {
	return this.characterService.getOne(id);
    }
}
