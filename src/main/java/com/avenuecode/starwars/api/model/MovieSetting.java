package com.avenuecode.starwars.api.model;

import java.util.Collection;

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
    private Collection<MovieCharacter> characters;

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

    public Collection<MovieCharacter> getCharacters() {
	return this.characters;
    }

    public void setCharacters(Collection<MovieCharacter> characters) {
	this.characters = characters;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.characters == null) ? 0 : this.characters.hashCode());
	result = prime * result + this.id;
	result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	MovieSetting other = (MovieSetting) obj;
	if (this.characters == null) {
	    if (other.characters != null) {
		return false;
	    }
	} else if (!this.characters.equals(other.characters)) {
	    return false;
	}
	if (this.id != other.id) {
	    return false;
	}
	if (this.name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!this.name.equals(other.name)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return String.format("MovieSetting [id=%s, name=%s, characters=%s]", this.id, this.name, this.characters);
    }
    
}
