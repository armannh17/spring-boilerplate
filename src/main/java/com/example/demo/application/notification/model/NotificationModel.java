package com.example.demo.application.notification.model;

import com.example.demo.application.notification.constant.NotificationType;
import com.example.demo.platform.shared.model.BaseModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class NotificationModel extends BaseModel {
  private String template;
  private String sender;
  private NotificationType type;
}
