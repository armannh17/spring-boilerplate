package com.example.demo.application.notification.mapper;

import com.example.demo.application.notification.entity.NotificationEntity;
import com.example.demo.application.notification.model.NotificationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
  public NotificationModel mapToModel(NotificationEntity notification);
}
