package com.wingtip.webapi.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
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

import com.wingtip.webapi.models.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleUserServiceException(Exception ex, WebRequest request) {
		LoggerFactory.getLogger(AppExceptionHandler.class).error("Error:" + ex.toString() + ":" + ex.getCause());
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), ex.getMessage());
		HttpStatus status = null;
		ResponseStatus annotation = ex.getClass().getAnnotation(ResponseStatus.class);
		if(annotation != null) status = annotation.value();
		else {
			ex.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			errorMessage.setMessage("Internal Server Error!");
			errorMessage.setDetails("Please refer to system admin.");
		}
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, 
			  HttpStatus status, WebRequest request){
		//final List<String> details = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int i = 0;
        for (final ObjectError error : ex.getBindingResult().getAllErrors()) {
            //details.add(error.getDefaultMessage());
        	if(i > 0) sb.append(",");
        	sb.append("[").append(error.getDefaultMessage()).append("]");
        	i++;
        }
        
	    ErrorMessage errorMsg = 
			new ErrorMessage(new Date(), "Validation Error.", sb.toString()); // , ex.getBindingResult().toString());
		return new ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST);
	}
}
