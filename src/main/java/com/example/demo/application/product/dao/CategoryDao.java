package com.example.demo.application.product.dao;

import com.example.demo.application.product.entity.CategoryEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, UUID> {
  List<CategoryEntity> findAllByStoreId(UUID storeId);

  Optional<CategoryEntity> findByFields_Id(UUID fieldId);
}
