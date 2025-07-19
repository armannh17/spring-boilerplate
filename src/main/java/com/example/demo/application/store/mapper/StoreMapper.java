package com.example.demo.application.store.mapper;

import com.example.demo.application.store.entity.StoreEntity;
import com.example.demo.application.store.model.StoreModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
  StoreModel mapToStoreModel(StoreEntity store);

  StoreEntity mapToStoreEntity(StoreModel store);
}
