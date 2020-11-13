package com.wingtip.webapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6195485836909559535L;

	public BadRequestException(String msg) {
		super(msg);
	}
}
