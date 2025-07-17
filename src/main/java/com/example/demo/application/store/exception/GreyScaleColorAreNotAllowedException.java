package com.example.demo.application.store.exception;

public class GreyScaleColorAreNotAllowedException extends RuntimeException {
	public GreyScaleColorAreNotAllowedException() {
		super("grey scale colors are not allowed");
	}
}
