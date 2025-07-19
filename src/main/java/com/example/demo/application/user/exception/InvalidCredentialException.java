package com.example.demo.application.user.exception;

public class InvalidCredentialException extends RuntimeException {
  public InvalidCredentialException() {
    super("invalid credentials provided");
  }
}
