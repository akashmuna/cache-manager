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
public class RatesAPIExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String UNAUTHORIZED = "401";
	private static final String INTERNAL_SERVER_ERROR = "500";
	
	@ExceptionHandler(value= {RatesAPIException.class})
	public ResponseEntity<Object> handleRatesAPIException(RatesAPIException e, WebRequest request){
		
		ErrorMessage errorMessage = new ErrorMessage(e.getStatus_code(), e.getUrl(), e.getMessage());
		
		if(e.getStatus_code().equals(UNAUTHORIZED))
			return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.UNAUTHORIZED);
		else if(e.getStatus_code().equals(INTERNAL_SERVER_ERROR))
			return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
       
        ErrorMessage errorMessage = new ErrorMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
