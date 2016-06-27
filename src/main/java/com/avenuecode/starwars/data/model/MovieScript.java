package com.avenuecode.starwars.data.model;

//TODO: confirmar se precisa mesmo dessa classe
public class MovieScript {
    private final String content;

    public MovieScript(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
