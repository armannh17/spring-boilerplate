package com.example.demo.application.product.serializer;

import com.example.demo.application.product.command.AddFieldCommand;
import com.example.demo.application.product.command.DeleteCategoryCommand;
import com.example.demo.application.product.command.DeleteFieldCommand;
import com.example.demo.application.product.command.MakeCategoryCommand;
import com.example.demo.application.product.command.UpdateCategoryCommand;
import com.example.demo.application.product.command.UpdateFieldCommand;
import com.example.demo.application.product.dto.AddFieldReqDto;
import com.example.demo.application.product.dto.AddFieldResDto;
import com.example.demo.application.product.dto.GetCategoryResDto;
import com.example.demo.application.product.dto.GetFieldResDto;
import com.example.demo.application.product.dto.MakeCategoryReqDto;
import com.example.demo.application.product.dto.MakeCategoryResDto;
import com.example.demo.application.product.dto.UpdateCategoryReqDto;
import com.example.demo.application.product.dto.UpdateFieldReqDto;
import com.example.demo.application.product.model.CategoryModel;
import com.example.demo.application.product.query.GetCategoryQuery;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import java.util.List;
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
        .code(ErrorCode.NO_ERROR)
        .status(HttpStatus.CREATED)
        .message("successful")
        .data(MakeCategoryResDto.builder().id(id).build())
        .build();
  }

  public UpdateCategoryCommand serializeUpdateCategoryCommand(
      UserDetails user, String categoryId, UpdateCategoryReqDto dto) {
    return UpdateCategoryCommand.builder()
        .id(UUID.fromString(categoryId))
        .name(dto.getName())
        .image(dto.getImage())
        .description(dto.getDescription())
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<Void> serializeUpdateCategoryResponse() {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.NO_ERROR)
        .status(HttpStatus.OK)
        .message("successful")
        .build();
  }

  public DeleteCategoryCommand serializeDeleteCategoryCommand(UserDetails user, String categoryId) {
    return DeleteCategoryCommand.builder()
        .id(UUID.fromString(categoryId))
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<Void> serializeDeleteCategoryResponse() {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.NO_ERROR)
        .status(HttpStatus.OK)
        .message("successful")
        .build();
  }

  public GetCategoryQuery serializeGetCategoryQuery(String storeId) {
    return GetCategoryQuery.builder().storeId(UUID.fromString(storeId)).build();
  }

  public ResponseDto<List<GetCategoryResDto>> serializeGetCategoryResponse(
      List<CategoryModel> categories) {
    return ResponseDto.<List<GetCategoryResDto>>builder()
        .code(ErrorCode.NO_ERROR)
        .status(HttpStatus.CREATED)
        .message("successful")
        .data(
            categories.stream()
                .map(
                    c ->
                        GetCategoryResDto.builder()
                            .id(c.getId().toString())
                            .name(c.getName())
                            .image(c.getImage())
                            .description(c.getDescription())
                            .fields(
                                c.getFields().stream()
                                    .map(
                                        f ->
                                            GetFieldResDto.builder()
                                                .id(f.getId().toString())
                                                .name(f.getName())
                                                .build())
                                    .toList())
                            .build())
                .toList())
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
        .code(ErrorCode.NO_ERROR)
        .status(HttpStatus.CREATED)
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
        .code(ErrorCode.NO_ERROR)
        .status(HttpStatus.OK)
        .message("successful")
        .build();
  }

  public DeleteFieldCommand serializeDeleteFieldCommand(
      UserDetails user, String categoryId, String fieldId) {
    return DeleteFieldCommand.builder()
        .id(UUID.fromString(fieldId))
        .categoryId(UUID.fromString(categoryId))
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<Void> serializeDeleteFieldResponse() {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.NO_ERROR)
        .status(HttpStatus.OK)
        .message("successful")
        .build();
  }
}
