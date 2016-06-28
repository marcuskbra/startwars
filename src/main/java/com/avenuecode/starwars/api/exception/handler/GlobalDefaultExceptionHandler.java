package com.avenuecode.starwars.api.exception.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.avenuecode.starwars.Application;
import com.avenuecode.starwars.api.exception.DuplicatedScriptException;
import com.avenuecode.starwars.api.exception.ItemNotFoundException;

@ControllerAdvice(basePackageClasses = Application.class)
public class GlobalDefaultExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    private static final String UNEXPECTED_ERROR = "Unexpected error";
    
    
    @ExceptionHandler(value = { ItemNotFoundException.class, DuplicatedScriptException.class })
    @ResponseBody
    ResponseEntity<?> handleAppException(final HttpServletRequest request, final Throwable ex) {
	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	final ResponseStatus annotation = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
	if (annotation != null) {
	    status = annotation.value();
	}
	LOG.error(ex.getMessage(), ex);
	return getErrorResponse(status, ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    ResponseEntity<?> handleException(final HttpServletRequest request, final Throwable ex) {
	final HttpStatus status = getStatus(request);
	LOG.error(UNEXPECTED_ERROR, ex);
	return getErrorResponse(status, UNEXPECTED_ERROR);
    }

    private ResponseEntity<?> getErrorResponse(final HttpStatus status, final String message) {
	final Map<String, String> response = new HashMap<>();
	response.put("message", message);
	final ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<>(response, status);
	return responseEntity;
    }

    private HttpStatus getStatus(final HttpServletRequest request) {
	final Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	if (statusCode == null) {
	    return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	return HttpStatus.valueOf(statusCode);
    }
}