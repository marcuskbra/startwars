package com.avenuecode.starwars.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class MovieSetting {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    
    @ManyToMany
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
