package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.MakeCategoryCommand;
import com.example.demo.application.product.dto.MakeCategoryReqDto;
import com.example.demo.application.product.dto.MakeCategoryResDto;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CategorySerializer {
  public MakeCategoryCommand serializeMakeCategoryCommand(
      UserDetails user, MakeCategoryReqDto dto) {
    return MakeCategoryCommand.builder()
        .name(dto.getName())
        .image(dto.getImage())
        .description(dto.getDescription())
        .storeId(UUID.fromString(dto.getStoreId()))
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<MakeCategoryResDto> serializeMakeCategoryResponse(String id) {
    return ResponseDto.<MakeCategoryResDto>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.CREATED.value())
        .message("successful")
        .data(MakeCategoryResDto.builder().id(id).build())
        .build();
  }
}
