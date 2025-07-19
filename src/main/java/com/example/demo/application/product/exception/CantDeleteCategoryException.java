package com.example.demo.application.product.exception;

public class CantDeleteCategoryException extends RuntimeException {
  public CantDeleteCategoryException() {
    super("cant update category while it has fields");
  }
}
