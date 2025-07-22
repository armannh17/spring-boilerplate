package com.example.demo.application.notification.serializer;

import com.example.demo.application.notification.dto.SendPatternMessageReqDto;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;

@Component
public class VisualPanelSerializer {
  public SendPatternMessageReqDto serializeSendPatternMessageRequest(
      String code, String sender, String recipient, String... tokens) {
    return SendPatternMessageReqDto.builder()
        .code(code)
        .sender(sender)
        .recipient(recipient)
        .variable(
            IntStream.range(0, tokens.length)
                .boxed()
                .collect(Collectors.toMap(i -> "token" + (i + 1), i -> tokens[i])))
        .build();
  }
}
