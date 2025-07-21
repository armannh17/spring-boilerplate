package com.example.demo.application.store.exception;

public class AccessToStoreDeniedException extends RuntimeException {
  public AccessToStoreDeniedException() {
    super("access to this store is denied");
  }
}
