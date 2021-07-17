package com.rest.webservices.restfullwebservice.user;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler 
				extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionresponse =  new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)) ;
		//System.out.println(exceptionresponse.details);
		
		return new ResponseEntity<Object>(exceptionresponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionresponse =  new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)) ;
		//System.out.println(exceptionresponse.details);
		
		return new ResponseEntity<Object>(exceptionresponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionresponse =  
											new ExceptionResponse(new Date(), 
																 "Not Valid Input", 
																 ex.getBindingResult().getFieldError().getDefaultMessage().toString()) ;
		return new ResponseEntity<Object>(exceptionresponse, HttpStatus.BAD_REQUEST);
	}

}
