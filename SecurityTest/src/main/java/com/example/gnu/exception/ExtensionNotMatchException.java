package com.example.gnu.exception;

public class ExtensionNotMatchException extends RuntimeException{
	private static final long serialVersionUID = -4713213343824349361L;

	public ExtensionNotMatchException(String message) {
		super(message);
	}
}
