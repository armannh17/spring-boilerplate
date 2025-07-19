package com.example.demo.application.product.exception;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException() {
    super("category does not exist");
  }
}
