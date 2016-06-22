package com.avenuecode.starwars.api.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.starwars.api.model.MovieCharacter;
import com.avenuecode.starwars.api.service.MovieCharacterService;

@RestController
@RequestMapping(path = "/characters")
public class MovieCharacterController {

    @Autowired
    private MovieCharacterService characterService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Collection<MovieCharacter> listAll() {
	return characterService.listAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody MovieCharacter get(@RequestParam(required = true) Integer id) {
	return characterService.getOne(id);
    }

}
