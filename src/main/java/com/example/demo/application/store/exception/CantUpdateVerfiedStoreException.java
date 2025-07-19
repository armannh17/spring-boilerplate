package com.example.demo.application.store.exception;

public class CantUpdateVerfiedStoreException extends RuntimeException {
  public CantUpdateVerfiedStoreException() {
    super("cant update a verified store");
  }
}
