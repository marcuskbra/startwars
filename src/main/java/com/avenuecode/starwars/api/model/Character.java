package com.avenuecode.starwars.api.model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Character {

    @Id
    private int id;
    private String name;
    private Map<String, Integer> wordCounts;

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

    public Map<String, Integer> getWordCounts() {
	return this.wordCounts;
    }

    public void setWordCounts(Map<String, Integer> wordCounts) {
	this.wordCounts = wordCounts;
    }

}
