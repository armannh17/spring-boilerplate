package com.example.demo.application.user.exception;

public class InvalidTokenException extends RuntimeException {
	public InvalidTokenException() {
		super("token is missing or invalid");
	}
}
