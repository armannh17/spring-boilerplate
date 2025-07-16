package com.example.demo.application.store.exception;

public class StoreAlreadyExistsException extends RuntimeException {
	public StoreAlreadyExistsException() {
		super("store with the given slug already exists");
	}
}
