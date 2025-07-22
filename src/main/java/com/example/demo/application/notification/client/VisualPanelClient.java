package com.example.demo.application.notification.client;

import com.example.demo.application.notification.config.NotificationConfig;
import com.example.demo.application.notification.constant.VisualPanelURL;
import com.example.demo.application.notification.dto.SendPatternMessageReqDto;
import com.example.demo.application.notification.exception.FailedToSendNotificationException;
import com.example.demo.application.notification.serializer.VisualPanelSerializer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VisualPanelClient implements NotificationClient {
  private final VisualPanelSerializer visualPanelSerializer;
  private final RestTemplate restTemplate;

  public VisualPanelClient(
      VisualPanelSerializer visualPanelSerializer,
      RestTemplateBuilder restTemplateBuilder,
      NotificationConfig notificationConfig) {
    this.visualPanelSerializer = visualPanelSerializer;
    this.restTemplate =
        restTemplateBuilder
            .rootUri(VisualPanelURL.BASE_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("apikey", notificationConfig.getVisualPanelApiKey())
            .build();
  }

  @Override
  public void send(String sender, String receiver, String template, String... tokens) {
    try {
      // parse the body of the request
      SendPatternMessageReqDto body =
          visualPanelSerializer.serializeSendPatternMessageRequest(
              template, sender, receiver, tokens);

      // send the request
      restTemplate.postForEntity(VisualPanelURL.SEND_PATTERN, body, String.class);
    } catch (Exception e) {
      throw new FailedToSendNotificationException(e.getMessage());
    }
  }
}
