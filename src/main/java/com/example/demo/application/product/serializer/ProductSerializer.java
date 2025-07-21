package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.MakeProductCommand;
import com.example.demo.application.product.dto.MakeProductReqDto;
import com.example.demo.application.product.dto.MakeProductResDto;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ProductSerializer {
  public MakeProductCommand serializeMakeProductCommand(UserDetails user, MakeProductReqDto dto) {
    return MakeProductCommand.builder()
        .name(dto.getName())
        .image(dto.getImage())
        .description(dto.getDescription())
        .storeId(UUID.fromString(dto.getStoreId()))
        .fieldId(UUID.fromString(dto.getFieldId()))
        .userdId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<MakeProductResDto> serializeMakeProductResponse(String id) {
    return ResponseDto.<MakeProductResDto>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.CREATED.value())
        .message("successful")
        .data(MakeProductResDto.builder().id(id).build())
        .build();
  }
}
