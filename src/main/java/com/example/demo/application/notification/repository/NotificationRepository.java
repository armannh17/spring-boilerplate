package com.example.demo.application.notification.repository;

import com.example.demo.application.notification.constant.NotificationType;
import com.example.demo.application.notification.dao.NotificationDao;
import com.example.demo.application.notification.entity.NotificationEntity;
import com.example.demo.application.notification.mapper.NotificationMapper;
import com.example.demo.application.notification.model.NotificationModel;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {
  private final NotificationDao notificationDao;
  private final NotificationMapper notificationMapper;

  public NotificationRepository(
      NotificationDao notificationDao, NotificationMapper notificationMapper) {
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
