package com.example.demo.application.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

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
