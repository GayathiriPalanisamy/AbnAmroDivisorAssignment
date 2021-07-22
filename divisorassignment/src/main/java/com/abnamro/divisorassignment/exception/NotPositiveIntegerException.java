package com.abnamro.divisorassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotPositiveIntegerException extends RuntimeException {
	/**
	 * 
	 */

	public NotPositiveIntegerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotPositiveIntegerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotPositiveIntegerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotPositiveIntegerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotPositiveIntegerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
