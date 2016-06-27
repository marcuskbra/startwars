package com.avenuecode.starwars.api.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "character_setting", 
    		joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"), 
    		inverseJoinColumns = @JoinColumn(name = "setting_id", referencedColumnName = "id"))
    private Collection<MovieSetting> settings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="character")
    private Collection<WordCount> wordCounts = new HashSet<>();

    public MovieCharacter() {
	super();
    }

    public MovieCharacter(String name) {
	super();
	this.name = name;
    }

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

    public Collection<MovieSetting> getSettings() {
	return this.settings;
    }

    public void addSettings(MovieSetting setting) {
	this.settings.add(setting);
    }

    public Collection<WordCount> getWordCounts() {
	return this.wordCounts;
    }

    public void setWordCounts(Collection<WordCount> wordCounts) {
	this.wordCounts = wordCounts;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
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
	MovieCharacter other = (MovieCharacter) obj;
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
	return String.format("MovieCharacter [id=%s, name=%s]", this.id, this.name);
    }

}
