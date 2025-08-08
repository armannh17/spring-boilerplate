package com.example.demo.application.store.repository;

import com.example.demo.application.store.dao.StoreDao;
import com.example.demo.application.store.entity.StoreEntity;
import com.example.demo.application.store.mapper.StoreMapper;
import com.example.demo.application.store.model.StoreModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class StoreRepository {
  private final StoreDao storeDao;
  private final StoreMapper storeMapper;

  public StoreRepository(StoreDao storeDao, StoreMapper storeMapper) {
    this.storeDao = storeDao;
    this.storeMapper = storeMapper;
  }

  public Optional<StoreModel> findByIdAndUser(UUID id, UUID userId) {
    Optional<StoreEntity> store = storeDao.findByIdAndUserId(id, userId);

    if (store.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(storeMapper.mapToStoreModel(store.get()));
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

  public List<StoreModel> findListByUser(Integer page, Integer limit, UUID userId) {
    Pageable pageable = PageRequest.of(page - 1, limit);

    Page<StoreEntity> stores = storeDao.findAllByUserId(pageable, userId);

    if (stores.isEmpty()) {
      return List.of();
    }

    return stores.getContent().stream().map(storeMapper::mapToStoreModel).toList();
  }
}
