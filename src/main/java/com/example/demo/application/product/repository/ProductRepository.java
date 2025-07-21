package com.example.demo.application.product.repository;

import com.example.demo.application.product.dao.ProductDao;
import com.example.demo.application.product.entity.ProductEntity;
import com.example.demo.application.product.mapper.ProductMapper;
import com.example.demo.application.product.model.ProductModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
  private final ProductDao productDao;
  private final ProductMapper productMapper;

  public ProductRepository(ProductDao productDao, ProductMapper productMapper) {
    this.productDao = productDao;
    this.productMapper = productMapper;
  }

  public Optional<ProductModel> findById(UUID id) {
    Optional<ProductEntity> product = productDao.findById(id);

    if (product.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(productMapper.mapToProductModel(product.get()));
  }

  public void save(ProductModel product) {
    ProductEntity entity = productMapper.mapToProductEntity(product);

    productDao.save(entity);
  }
}
