package com.avenuecode.starwars.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public final class MovieScript {

    @Id
    private String id;

    @Lob
    private String content;

    MovieScript() {
    }
    
    public MovieScript(String id, String content) {
	this.id = id;
	this.content = content;
    }

    public String getId() {
	return this.id;
    }

    public String getContent() {
	return this.content;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
	result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	MovieScript other = (MovieScript) obj;
	if (this.id == null) {
	    if (other.id != null)
		return false;
	} else if (!this.id.equals(other.id))
	    return false;
	if (this.content == null) {
	    if (other.content != null)
		return false;
	} else if (!this.content.equals(other.content))
	    return false;
	return true;
    }

}
