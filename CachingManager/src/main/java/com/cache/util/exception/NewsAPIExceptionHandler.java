package com.cache.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cache.model.exception.ErrorMessage;

@ControllerAdvice
public class NewsAPIExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= {NewsAPIException.class})
	public ResponseEntity<Object> handleNewsAPIException(NewsAPIException e, WebRequest request){
		
		ErrorMessage errorMessage = new ErrorMessage(e.getStatus_code(), e.getUrl(), e.getMessage());
		return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
}
