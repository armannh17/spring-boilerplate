package com.example.demo.application.store.repository;

import com.example.demo.application.store.dao.StoreDao;
import com.example.demo.application.store.entity.StoreEntity;
import com.example.demo.application.store.mapper.StoreMapper;
import com.example.demo.application.store.model.StoreModel;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class StoreRepository {
  private final StoreDao storeDao;
  private final StoreMapper storeMapper;
  private final EntityManager em;

  public StoreRepository(StoreDao storeDao, StoreMapper storeMapper, EntityManager em) {
    this.storeDao = storeDao;
    this.storeMapper = storeMapper;
    this.em = em;
  }

  public Optional<StoreModel> findById(UUID id) {
    Optional<StoreEntity> store = storeDao.findById(id);

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
}
