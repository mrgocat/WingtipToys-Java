package com.wingtip.webapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException {
	public AuthException(String msg) {
		super(msg);
	}
}
