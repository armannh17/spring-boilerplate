package com.example.demo.application.product.repository;

import com.example.demo.application.product.dao.CategoryDao;
import com.example.demo.application.product.entity.CategoryEntity;
import com.example.demo.application.product.mapper.CategoryMapper;
import com.example.demo.application.product.model.CategoryModel;
import java.util.ArrayList;
import java.util.List;
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

  public Optional<CategoryModel> findById(UUID id) {
    Optional<CategoryEntity> category = categoryDao.findById(id);

    if (category.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(categoryMapper.mapToCategoryModel(category.get()));
  }

  public List<CategoryModel> findAllByStore(UUID storeId) {
    List<CategoryEntity> categories = categoryDao.findAllByStoreId(storeId);

    if (categories.isEmpty()) {
      return new ArrayList<>();
    }

    return categories.stream().map(categoryMapper::mapToCategoryModel).toList();
  }

  public void save(CategoryModel category) {
    CategoryEntity entity = categoryMapper.mapToCategoryEntity(category);

    categoryDao.save(entity);
  }

  public void delete(CategoryModel category) {
    CategoryEntity entity = categoryMapper.mapToCategoryEntity(category);

    categoryDao.delete(entity);
  }
}
