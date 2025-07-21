package com.example.demo.application.product.mapper;

import com.example.demo.application.product.entity.ProductEntity;
import com.example.demo.application.product.entity.VariantEntity;
import com.example.demo.application.product.entity.VarietyEntity;
import com.example.demo.application.product.model.ProductModel;
import com.example.demo.application.product.model.VariantModel;
import com.example.demo.application.product.model.VarietyModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductModel mapToProductModel(ProductEntity product);

  ProductEntity mapToProductEntity(ProductModel product);

  VariantModel mapToVariantModel(VariantEntity variant);

  VariantEntity mapToVariantEntity(VariantModel variant);

  VarietyModel mapToVarietyModel(VarietyModel variety);

  VarietyEntity mapToVarietyEntity(VarietyModel variety);
}
