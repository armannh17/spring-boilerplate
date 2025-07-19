package com.example.demo.application.notification.client;

import com.example.demo.application.notification.config.NotificationConfig;
import com.example.demo.application.notification.exception.FailedToSendNotificationException;
import com.kavenegar.sdk.KavenegarApi;
import org.springframework.stereotype.Component;

@Component
public class KaveNegarClient implements NotificationClient {
  private final KavenegarApi api;

  public KaveNegarClient(NotificationConfig notificationConfig) {
    this.api = new KavenegarApi(notificationConfig.getKavenegarApiKey());
  }

  @Override
  public void send(String receiver, String template, String... tokens) {
    try {
      String token1 = tokens.length >= 1 ? tokens[0] : null;
      String token2 = tokens.length >= 2 ? tokens[1] : null;
      String token3 = tokens.length >= 3 ? tokens[2] : null;

      api.verifyLookup(receiver, token1, token2, token3, template);
    } catch (Exception e) {
      throw new FailedToSendNotificationException(e.getMessage());
    }
  }
}
