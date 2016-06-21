package com.avenuecode.starwars.api.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.starwars.api.model.Movie;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public String index() {
        return "May the Force be with you!";
    }

    @RequestMapping(path = "/sayHello", method = RequestMethod.GET)
    public @ResponseBody Movie sayHello(
            @RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
        return new Movie(counter.incrementAndGet(), String.format(template, name));
    }
}
