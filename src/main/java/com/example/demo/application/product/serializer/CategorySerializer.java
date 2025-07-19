package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.AddFieldCommand;
import com.example.demo.application.product.command.MakeCategoryCommand;
import com.example.demo.application.product.command.UpdateFieldCommand;
import com.example.demo.application.product.dto.AddFieldReqDto;
import com.example.demo.application.product.dto.AddFieldResDto;
import com.example.demo.application.product.dto.MakeCategoryReqDto;
import com.example.demo.application.product.dto.MakeCategoryResDto;
import com.example.demo.application.product.dto.UpdateFieldReqDto;
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

  public AddFieldCommand serializeAddFieldCommand(
      UserDetails user, String categoryId, AddFieldReqDto dto) {
    return AddFieldCommand.builder()
        .name(dto.getName())
        .categoryId(UUID.fromString(categoryId))
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<AddFieldResDto> serializeAddFieldResponse(String id) {
    return ResponseDto.<AddFieldResDto>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.CREATED.value())
        .message("successful")
        .data(AddFieldResDto.builder().id(id).build())
        .build();
  }

  public UpdateFieldCommand serializeUpdateFieldCommand(
      UserDetails user, String categoryId, String fieldId, UpdateFieldReqDto dto) {
    return UpdateFieldCommand.builder()
        .name(dto.getName())
        .id(UUID.fromString(fieldId))
        .categoryId(UUID.fromString(categoryId))
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<Void> serializeUpdateFieldResponse() {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.OK.value())
        .message("successful")
        .build();
  }
}
