package com.example.demo.application.store.serializer;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.command.UpdateStoreCommand;
import com.example.demo.application.store.dto.GetStoreResDto;
import com.example.demo.application.store.dto.MakeStoreReqDto;
import com.example.demo.application.store.dto.MakeStoreResDto;
import com.example.demo.application.store.dto.UpdateStoreReqDto;
import com.example.demo.application.store.model.StoreModel;
import com.example.demo.application.store.query.GetStoreQuery;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class StoreSerializer {
  public MakeStoreCommand serializeMakeStoreCommand(UserDetails user, MakeStoreReqDto dto) {
    return MakeStoreCommand.builder()
        .name(dto.getName())
        .slug(dto.getSlug())
        .image(dto.getImage())
        .brief(dto.getBrief())
        .description(dto.getDescription())
        .color(dto.getColor())
        .raduis(dto.getRadius())
        .detail(dto.getDetail())
        .alignment(dto.getAlignment())
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<MakeStoreResDto> serializeMakeStoreResponse(String id) {
    return ResponseDto.<MakeStoreResDto>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.CREATED.value())
        .message("successful")
        .data(MakeStoreResDto.builder().id(id).build())
        .build();
  }

  public GetStoreQuery serializeGetStoreQuery(String slug) {
    return GetStoreQuery.builder().slug(slug).build();
  }

  public ResponseDto<GetStoreResDto> serializeGetStoreResponse(StoreModel store) {
    return ResponseDto.<GetStoreResDto>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.OK.value())
        .message("successful")
        .data(
            GetStoreResDto.builder()
                .id(store.getId().toString())
                .name(store.getName())
                .slug(store.getSlug())
                .brief(store.getBrief())
                .description(store.getDescription())
                .image(store.getImage())
                .colorPrimary(store.getColorPrimary())
                .colorAccent(store.getColorAccent())
                .colorNeutral(store.getColorNeutral())
                .colorDark(store.getColorDark())
                .verified(store.getVerified())
                .radius(store.getRaduis())
                .detail(store.getDetail())
                .alignment(store.getAlignment())
                .build())
        .build();
  }

  public UpdateStoreCommand serializeUpdateStoreCommand(
      UserDetails user, UUID id, UpdateStoreReqDto dto) {
    return UpdateStoreCommand.builder()
        .id(id)
        .name(dto.getName())
        .image(dto.getImage())
        .brief(dto.getBrief())
        .description(dto.getDescription())
        .color(dto.getColor())
        .raduis(dto.getRadius())
        .detail(dto.getDetail())
        .alignment(dto.getAlignment())
        .userId(UUID.fromString(user.getUsername()))
        .build();
  }

  public ResponseDto<Void> serializeUpdateStoreResponse() {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(HttpStatus.OK.value())
        .message("successful")
        .build();
  }
}
