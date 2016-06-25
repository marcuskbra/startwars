package com.avenuecode.starwars.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WordCount {

    public WordCount(String word, Integer count) {
	super();
	this.word = word;
	this.count = count;
    }

    @Id
    private String word;

    private Integer count;

    public String getWord() {
	return this.word;
    }

    public void setWord(String word) {
	this.word = word;
    }

    public Integer getCount() {
	return this.count;
    }

    public void setCount(Integer count) {
	this.count = count;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
	result = prime * result + ((this.word == null) ? 0 : this.word.hashCode());
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
	WordCount other = (WordCount) obj;
	if (this.count == null) {
	    if (other.count != null) {
		return false;
	    }
	} else if (!this.count.equals(other.count)) {
	    return false;
	}
	if (this.word == null) {
	    if (other.word != null) {
		return false;
	    }
	} else if (!this.word.equals(other.word)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return String.format("[word=%s, count=%s]", this.word, this.count);
    }

}
