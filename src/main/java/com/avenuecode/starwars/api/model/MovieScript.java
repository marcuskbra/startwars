package com.avenuecode.starwars.api.model;

public class MovieScript {
    private final String content;

    public MovieScript(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
