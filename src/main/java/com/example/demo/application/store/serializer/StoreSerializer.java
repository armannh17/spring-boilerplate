package com.example.demo.application.store.serializer;

import com.example.demo.application.store.command.MakeStoreCommand;
import com.example.demo.application.store.command.UpdateStoreCommand;
import com.example.demo.application.store.dto.GetStoreListReqDto;
import com.example.demo.application.store.dto.GetStoreListResDto;
import com.example.demo.application.store.dto.GetStoreResDto;
import com.example.demo.application.store.dto.MakeStoreReqDto;
import com.example.demo.application.store.dto.MakeStoreResDto;
import com.example.demo.application.store.dto.UpdateStoreReqDto;
import com.example.demo.application.store.model.StoreModel;
import com.example.demo.application.store.query.GetStoreListQuery;
import com.example.demo.application.store.query.GetStoreQuery;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreSerializer {

  MakeStoreCommand serializeToMakeStoreCommand(String userId, MakeStoreReqDto dto);

  MakeStoreResDto serializeToMakeStoreDto(String id);

  GetStoreQuery serializeToGetStoreQuery(String slug);

  GetStoreResDto serializeToGetStoreDto(StoreModel store);

  UpdateStoreCommand serializeToUpdateStoreCommand(String userId, String id, UpdateStoreReqDto dto);

  GetStoreListQuery serializeToGetStoreListQuery(String userId, GetStoreListReqDto dto);

  List<GetStoreListResDto> serializeToGetStoreListDto(List<StoreModel> stores);
}
