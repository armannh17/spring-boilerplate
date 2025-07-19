package com.example.demo.application.notification.exception;

public class FailedToSendNotificationException extends RuntimeException {
  public FailedToSendNotificationException(String message) {
    super(message);
  }
}
