package com.example.demo.application.notification.service;

import com.example.demo.application.notification.client.NotificationClient;
import com.example.demo.application.notification.client.VisualPanelClient;
import com.example.demo.application.notification.constant.NotificationType;
import com.example.demo.application.notification.exception.NotificationTemplateMissingException;
import com.example.demo.application.notification.model.NotificationModel;
import com.example.demo.application.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  private final NotificationClient notificationClient;
  private final NotificationRepository notificationRepository;

  public NotificationService(
      VisualPanelClient visualPanelClient, NotificationRepository notificationRepository) {
    this.notificationClient = visualPanelClient;
    this.notificationRepository = notificationRepository;
  }

  public void sendOtpNotification(String receiver, String otp) {
    NotificationModel notification =
        notificationRepository
            .findByType(NotificationType.OTP_CODE)
            .orElseThrow(NotificationTemplateMissingException::new);

    this.notificationClient.send(
        notification.getSender(), receiver, notification.getTemplate(), otp);
  }
}
