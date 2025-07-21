package com.example.demo.application.product.exception;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException() {
    super("product does not exist");
  }
}
