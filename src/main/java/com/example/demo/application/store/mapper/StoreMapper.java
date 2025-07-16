package com.example.demo.application.store.mapper;

import org.mapstruct.Mapper;

import com.example.demo.application.store.entity.StoreEntity;
import com.example.demo.application.store.model.StoreModel;

@Mapper(componentModel = "spring")
public interface StoreMapper {
	StoreModel mapToStoreModel(StoreEntity store);

	StoreEntity mapToStoreEntity(StoreModel store);
}
