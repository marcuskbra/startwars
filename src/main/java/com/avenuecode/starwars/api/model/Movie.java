package com.avenuecode.starwars.api.model;

public class Movie {
    private final long id;
    private final String content;

    public Movie(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
