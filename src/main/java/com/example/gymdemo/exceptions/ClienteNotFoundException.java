package com.example.gymdemo.exceptions;

public class ClienteNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 0L;
	
	public ClienteNotFoundException(String message) {
		super(message);
	}

}
