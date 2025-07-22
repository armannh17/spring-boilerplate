package com.example.demo.application.notification.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendPatternMessageReqDto {
  private String code;
  private String sender;
  private String recipient;
  private Map<String, String> variable;
}
