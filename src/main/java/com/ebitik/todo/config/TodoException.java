package com.ebitik.todo.config;

/**
 * An i18n friendly exception. message will be i18n key.
 * 
 * @author erdal.bitik
 * */
public class TodoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TodoException(String message) {
		super(message);
	}

}
