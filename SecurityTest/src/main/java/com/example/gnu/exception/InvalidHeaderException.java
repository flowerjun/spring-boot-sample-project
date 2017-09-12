package com.example.gnu.exception;

public class InvalidHeaderException extends RuntimeException{
	private static final long serialVersionUID = 4886603260792207755L;

	public InvalidHeaderException(String message) {
		super(message);
	}
	
}
