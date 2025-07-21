package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.AddVariantCommand;
import com.example.demo.application.product.command.AddVarietyCommand;
import com.example.demo.application.product.command.DeleteVariantCommand;
import com.example.demo.application.product.command.MakeProductCommand;
import com.example.demo.application.product.dto.AddVariantReqDto;
import com.example.demo.application.product.dto.AddVariantResDto;
import com.example.demo.application.product.dto.MakeProductReqDto;
import com.example.demo.application.product.dto.MakeProductResDto;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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

  public AddVariantCommand serializeAddVariantCommand(
      UserDetails user, String productId, AddVariantReqDto dto) {
    List<AddVarietyCommand> varieties =
        dto.getVarieties().stream()
            .map(v -> AddVarietyCommand.builder().name(v.getName()).color(v.getColor()).build())
            .collect(Collectors.toList());

    return AddVariantCommand.builder()
        .name(dto.getName())
        .overview(dto.getOverview())
        .varieties(varieties)
        .productId(UUID.fromString(productId))
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<AddVariantResDto> serializeAddVariantResponse(String id) {
    return ResponseDto.<AddVariantResDto>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.CREATED.value())
        .message("successful")
        .data(AddVariantResDto.builder().id(id).build())
        .build();
  }

  public DeleteVariantCommand serializeDeleteVariantCommand(
      UserDetails user, String productId, String variantId) {

    return DeleteVariantCommand.builder()
        .id(UUID.fromString(variantId))
        .productId(UUID.fromString(productId))
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<Void> serializeDeleteVariantResponse() {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.OK.value())
        .message("successful")
        .build();
  }
}
