package com.avenuecode.starwars.api.model;

import java.util.Set;

public class MovieSetting {

    private int id;
    private String name;
    private Set<MovieCharacter> characters;

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<MovieCharacter> getCharacters() {
	return this.characters;
    }

    public void setCharacters(Set<MovieCharacter> characters) {
	this.characters = characters;
    }

}
