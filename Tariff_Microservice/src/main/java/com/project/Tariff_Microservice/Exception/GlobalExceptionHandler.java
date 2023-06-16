package com.project.Tariff_Microservice.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
    
	@ExceptionHandler(TariffNotFoundException.class)
	public ResponseEntity<ErrorObject> handleException(TariffNotFoundException ex,WebRequest request)
	{
		ErrorObject errorObject =new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DiscountNotFoundException.class)
	public ResponseEntity<ErrorObject> handleDiscountException(DiscountNotFoundException ex,WebRequest request)
	{
		ErrorObject errorObject =new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EstimationNotSavedException.class)
	public ResponseEntity<ErrorObject> handleEstimationException(EstimationNotSavedException ex,WebRequest request)
	{
		ErrorObject errorObject =new ErrorObject();
		errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EstimationNotFoundException.class)
	public ResponseEntity<ErrorObject> handleEstimationNotFoundException(EstimationNotFoundException ex,WebRequest request)
	{
		ErrorObject errorObject =new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
	}
}
