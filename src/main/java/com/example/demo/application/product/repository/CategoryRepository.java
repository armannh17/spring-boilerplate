package com.example.demo.application.product.repository;

import com.example.demo.application.product.dao.CategoryDao;
import com.example.demo.application.product.entity.CategoryEntity;
import com.example.demo.application.product.mapper.CategoryMapper;
import com.example.demo.application.product.model.CategoryModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
  private final CategoryMapper categoryMapper;
  private final CategoryDao categoryDao;

  public CategoryRepository(CategoryMapper categoryMapper, CategoryDao categoryDao) {
    this.categoryMapper = categoryMapper;
    this.categoryDao = categoryDao;
  }

  public Optional<CategoryModel> findByIdAndStore(UUID id, UUID storeId) {
    Optional<CategoryEntity> category = categoryDao.findByIdAndStoreId(id, storeId);

    if (category.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(categoryMapper.mapToCategoryModel(category.get()));
  }

  public void save(CategoryModel category) {
    CategoryEntity entity = categoryMapper.mapToCategoryEntity(category);

    categoryDao.save(entity);
  }
}
