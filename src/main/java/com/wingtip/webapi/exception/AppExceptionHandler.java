package com.wingtip.webapi.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wingtip.webapi.model.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleUserServiceException(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), ex.getMessage());
		HttpStatus status = null;
		ResponseStatus annotation = ex.getClass().getAnnotation(ResponseStatus.class);
		if(annotation != null) status = annotation.value();
		else status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, 
			  HttpStatus status, WebRequest request){

	    ErrorMessage errorMsg = 
			new ErrorMessage(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		return new ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST);
	}
}
