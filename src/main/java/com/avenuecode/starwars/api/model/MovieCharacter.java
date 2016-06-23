package com.avenuecode.starwars.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MovieCharacter {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @JsonIgnore
    @OneToMany
    private Set<MovieSetting> settings = new HashSet<>();

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

    public Set<MovieSetting> getSettings() {
	return this.settings;
    }

    public void addSettings(MovieSetting setting) {
	this.settings.add(setting);
    }

}
