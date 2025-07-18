package com.example.demo.application.notification.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.application.notification.constant.NotificationType;
import com.example.demo.application.notification.entity.NotificationEntity;

@Repository
public interface NotificationDao extends JpaRepository<NotificationEntity, UUID> {
	Optional<NotificationEntity> findByType(NotificationType type);
}
