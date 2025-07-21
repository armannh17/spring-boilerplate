package com.example.demo.application.product.exception;

public class VariantNotFoundException extends RuntimeException {
  public VariantNotFoundException() {
    super("variant does not exist");
  }
}
