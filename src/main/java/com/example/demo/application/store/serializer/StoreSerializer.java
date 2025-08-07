package com.example.demo.application.store.serializer;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.command.UpdateStoreCommand;
import com.example.demo.application.store.dto.GetStoreResDto;
import com.example.demo.application.store.dto.MakeStoreReqDto;
import com.example.demo.application.store.dto.MakeStoreResDto;
import com.example.demo.application.store.dto.UpdateStoreReqDto;
import com.example.demo.application.store.model.StoreModel;
import com.example.demo.application.store.query.GetStoreQuery;
import com.example.demo.platform.shared.serializer.BaseSerializer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface StoreSerializer extends BaseSerializer {

  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  MakeStoreCommand serializeToMakeStoreCommand(UserDetails user, MakeStoreReqDto dto);

  MakeStoreResDto serializeToMakeStoreDto(String id);

  GetStoreQuery serializeToGetStoreQuery(String slug);

  GetStoreResDto serializeToGetStoreDto(StoreModel store);

  @Mapping(target = "id", source = "id", qualifiedByName = "mapId")
  @Mapping(target = "userId", source = "user", qualifiedByName = "mapId")
  UpdateStoreCommand serializeToUpdateStoreCommand(
      UserDetails user, String id, UpdateStoreReqDto dto);
}
