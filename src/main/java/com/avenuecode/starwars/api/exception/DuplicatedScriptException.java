package com.avenuecode.starwars.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class DuplicatedScriptException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicatedScriptException(String message) {
	super(message);
    }

}
