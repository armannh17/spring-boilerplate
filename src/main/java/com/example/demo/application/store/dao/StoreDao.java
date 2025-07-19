package com.example.demo.application.store.dao;

import com.example.demo.application.store.entity.StoreEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDao extends JpaRepository<StoreEntity, UUID> {
  Optional<StoreEntity> findBySlug(String slug);
}
