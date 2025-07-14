package com.example.demo.application.notification.service;

import org.springframework.stereotype.Service;

import com.example.demo.application.notification.client.KaveNegarClient;
import com.example.demo.application.notification.client.NotificationClient;
import com.example.demo.application.notification.exception.NotificationTemplateMissingException;
import com.example.demo.application.notification.model.NotificationModel;
import com.example.demo.application.notification.repository.NotificationRepository;
import com.example.demo.application.notification.type.NotificationType;

@Service
public class NotificationService {
	private final NotificationClient notificationClient;
	private final NotificationRepository notificationRepository;

	public NotificationService(KaveNegarClient kaveNegarClient, NotificationRepository notificationRepository) {
		this.notificationClient = kaveNegarClient;
		this.notificationRepository = notificationRepository;
	}

	public void sendOtpNotification(String receiver, String otp) {
		NotificationModel notification = notificationRepository.findByType(NotificationType.OTP_CODE)
				.orElseThrow(NotificationTemplateMissingException::new);

		this.notificationClient.send(receiver, notification.getTemplate(), otp);
	}
}
