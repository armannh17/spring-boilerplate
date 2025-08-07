package com.example.demo.application.notification.serializer;

import com.example.demo.application.notification.dto.SendPatternMessageReqDto;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface VisualPanelSerializer {

  @Mapping(target = "variable", source = "tokens", qualifiedByName = "mapTokensToVariables")
  SendPatternMessageReqDto serializeSendPatternMessageRequest(
      String code, String sender, String recipient, String... tokens);

  @Named("mapTokensToVariables")
  default Map<String, String> mapTokensToVariables(String... tokens) {
    return IntStream.range(0, tokens.length)
        .boxed()
        .collect(Collectors.toMap(i -> "token" + (i + 1), i -> tokens[i]));
  }
}
