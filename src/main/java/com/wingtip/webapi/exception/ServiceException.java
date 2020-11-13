package com.wingtip.webapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5516596042177053726L;

	public ServiceException(String msg) {
		super(msg);
	}
}
