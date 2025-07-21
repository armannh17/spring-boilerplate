package com.example.demo.application.product.exception;

public class CantUpdateProductException extends RuntimeException {
  public CantUpdateProductException() {
    super("cant update published or archived products");
  }
}
