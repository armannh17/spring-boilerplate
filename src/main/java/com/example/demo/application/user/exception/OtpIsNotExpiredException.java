package com.example.demo.application.user.exception;

public class OtpIsNotExpiredException extends RuntimeException {
  public OtpIsNotExpiredException() {
    super("you should wait for the current otp to expire");
  }
}
