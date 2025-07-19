package com.example.demo.application.notification.handler;

import com.example.demo.application.notification.service.NotificationService;
import com.example.demo.application.user.event.LoginAttemptedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SendOtpNotificationHandler {
  private final NotificationService notificationService;

  public SendOtpNotificationHandler(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @EventListener
  public void on(LoginAttemptedEvent event) {
    this.notificationService.sendOtpNotification(event.getPhone(), event.getOtp());
  }
}
