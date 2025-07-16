package com.example.demo.application.store.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.application.store.dao.StoreDao;
import com.example.demo.application.store.entity.StoreEntity;
import com.example.demo.application.store.mapper.StoreMapper;
import com.example.demo.application.store.model.StoreModel;

@Repository
public class StoreRepository {
	private final StoreDao storeDao;
	private final StoreMapper storeMapper;

	public StoreRepository(StoreDao storeDao, StoreMapper storeMapper) {
		this.storeDao = storeDao;
		this.storeMapper = storeMapper;
	}

	public Optional<StoreModel> findBySlug(String slug) {
		Optional<StoreEntity> store = storeDao.findBySlug(slug);

		if (store.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(storeMapper.mapToStoreModel(store.get()));
	}

	public void save(StoreModel store) {
		StoreEntity entity = storeMapper.mapToStoreEntity(store);

		storeDao.save(entity);
	}
}
