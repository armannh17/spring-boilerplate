package com.example.demo.application.product.mapper;

import com.example.demo.application.product.entity.CategoryEntity;
import com.example.demo.application.product.entity.FieldEntity;
import com.example.demo.application.product.model.CategoryModel;
import com.example.demo.application.product.model.FieldModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  CategoryModel mapToCategoryModel(CategoryEntity category);

  CategoryEntity mapToCategoryEntity(CategoryModel category);

  FieldModel mapToFieldModel(FieldEntity field);

  FieldEntity mapToFieldEntity(FieldModel field);
}
