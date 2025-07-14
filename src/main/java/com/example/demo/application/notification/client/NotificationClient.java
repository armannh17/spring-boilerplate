package com.example.demo.application.notification.client;

public interface NotificationClient {
	public void send(String receiver, String template, String... tokens);
}
