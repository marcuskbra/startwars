package com.avenuecode.starwars.api.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.starwars.api.model.MovieSetting;
import com.avenuecode.starwars.api.service.MovieSettingService;

@RestController
@RequestMapping(path = "/settings")
public class MovieSettingController {

    @Autowired
    private MovieSettingService settingService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Collection<MovieSetting> listAll() {
	return this.settingService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody MovieSetting get(@PathVariable Integer id) {
	return this.settingService.getOne(id);
    }
}
