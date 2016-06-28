package com.avenuecode.starwars.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.starwars.api.service.MovieSettingService;
import com.avenuecode.starwars.data.model.MovieSetting;

@RestController
@RequestMapping(path = "/settings")
public class MovieSettingController {

    private static final Logger LOG = LoggerFactory.getLogger(MovieSettingController.class);
    
    @Autowired
    private MovieSettingService settingService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Iterable<MovieSetting> listAll() {
	LOG.info("Listing all settings");
	return this.settingService.findAll();
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody MovieSetting get(@PathVariable Integer id) {
	LOG.info("Searching setting by " + id);
	return this.settingService.getOne(id);
    }
}
