package com.example.demo;

import com.example.demo.application.file.config.FileConfig;
import com.example.demo.application.notification.config.NotificationConfig;
import com.example.demo.application.user.config.UserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.example.demo")
@EnableConfigurationProperties({UserConfig.class, NotificationConfig.class, FileConfig.class})
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
