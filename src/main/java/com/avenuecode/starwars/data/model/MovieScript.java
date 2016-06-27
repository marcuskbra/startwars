package com.avenuecode.starwars.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public final class MovieScript {

    @Id
    private String md5;

    @Lob
    private String content;

    MovieScript() {
    }
    
    public MovieScript(String md5, String content) {
	this.md5 = md5;
	this.content = content;
    }

    public String getMd5() {
	return this.md5;
    }

    public String getContent() {
	return this.content;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.md5 == null) ? 0 : this.md5.hashCode());
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
	if (this.md5 == null) {
	    if (other.md5 != null)
		return false;
	} else if (!this.md5.equals(other.md5))
	    return false;
	if (this.content == null) {
	    if (other.content != null)
		return false;
	} else if (!this.content.equals(other.content))
	    return false;
	return true;
    }

}
