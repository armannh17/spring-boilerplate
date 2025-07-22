package com.example.demo.application.notification.client;

public interface NotificationClient {
  public void send(String sender, String receiver, String template, String... tokens);
}
