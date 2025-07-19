package com.example.demo.application.notification.mapper;

import com.example.demo.application.notification.entity.NotificationEntity;
import com.example.demo.application.notification.model.NotificationModel;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
  public NotificationModel mapToModel(NotificationEntity notification) {
    return NotificationModel.builder()
        .id(notification.getId())
        .template(notification.getTemplate())
        .type(notification.getType())
        .createdAt(notification.getCreatedAt())
        .updatedAt(notification.getUpdatedAt())
        .build();
  }
}
