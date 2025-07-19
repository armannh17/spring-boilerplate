package com.example.demo.application.user.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "user")
public class UserConfig {
  private String tokenSecret;
  private Long tokenExpiry;

  private Integer otpLength;
  private Long otpExpiry;
}
