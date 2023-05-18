package ru.delivery.fastdelivery.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;


@RestControllerAdvice
public class ErrorHandler {	
	
	 @ExceptionHandler(BadRequestException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public BadRequestResponse badRequestException(BadRequestException e) {		 
		 return new BadRequestResponse();
	 }
	 
	 @ExceptionHandler(NotFoundException.class)
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public NotFoundResponse notFoundException(NotFoundException e) {		 
		 return new NotFoundResponse();
	 }
}
