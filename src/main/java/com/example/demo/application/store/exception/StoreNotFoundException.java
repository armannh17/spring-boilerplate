package com.example.demo.application.store.exception;

public class StoreNotFoundException extends RuntimeException {
	public StoreNotFoundException() {
		super("store with provided slug does not exist");
	}
}
