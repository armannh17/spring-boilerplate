package com.example.demo.application.notification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "notification")
public class NotificationConfig {
  private String kavenegarApiKey;
  private String visualPanelApiKey;
}
