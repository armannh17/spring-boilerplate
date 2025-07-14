package com.example.demo.application.notification.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.application.notification.dao.NotificationDao;
import com.example.demo.application.notification.entity.NotificationEntity;
import com.example.demo.application.notification.mapper.NotificationMapper;
import com.example.demo.application.notification.model.NotificationModel;
import com.example.demo.application.notification.type.NotificationType;

@Repository
public class NotificationRepository {
	private final NotificationDao notificationDao;
	private final NotificationMapper notificationMapper;

	public NotificationRepository(NotificationDao notificationDao, NotificationMapper notificationMapper) {
		this.notificationDao = notificationDao;
		this.notificationMapper = notificationMapper;
	}

	public Optional<NotificationModel> findByType(NotificationType type) {
		Optional<NotificationEntity> notification = notificationDao.findByType(type);

		if (notification.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(notificationMapper.mapToModel(notification.get()));
	}
}
