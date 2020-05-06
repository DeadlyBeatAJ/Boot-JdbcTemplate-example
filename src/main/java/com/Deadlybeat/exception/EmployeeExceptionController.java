package com.Deadlybeat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
this annotation is used to handle the exception globally
*/
@ControllerAdvice
public class EmployeeExceptionController {

	/*
	this annotation is used to handle the specific exception  and sending the custom response to the client
	*/
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<Object> exception(EmployeeNotFoundException exception)
	{
		return new ResponseEntity<Object>("Employee Record not found!",HttpStatus.NOT_FOUND);
	}
	
}
