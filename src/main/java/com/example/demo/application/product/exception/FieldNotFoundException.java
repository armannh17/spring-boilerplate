package com.example.demo.application.product.exception;

public class FieldNotFoundException extends RuntimeException {
  public FieldNotFoundException() {
    super("field does not exist");
  }
}
