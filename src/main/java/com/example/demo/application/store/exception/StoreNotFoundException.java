package com.example.demo.application.store.exception;

public class StoreNotFoundException extends RuntimeException {
	public StoreNotFoundException() {
		super("store does not exist");
	}
}
