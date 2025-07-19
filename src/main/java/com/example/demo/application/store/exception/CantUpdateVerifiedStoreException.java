package com.example.demo.application.store.exception;

public class CantUpdateVerifiedStoreException extends RuntimeException {
  public CantUpdateVerifiedStoreException() {
    super("cant update a verified store");
  }
}
