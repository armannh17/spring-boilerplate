package com.example.demo.application.notification.dao;

import com.example.demo.application.notification.constant.NotificationType;
import com.example.demo.application.notification.entity.NotificationEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends JpaRepository<NotificationEntity, UUID> {
  Optional<NotificationEntity> findByType(NotificationType type);
}
