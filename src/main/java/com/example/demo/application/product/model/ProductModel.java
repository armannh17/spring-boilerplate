package com.example.demo.application.product.model;

import com.example.demo.platform.shared.model.BaseModel;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProductModel extends BaseModel {
  private String name;
  private String image;
  private String description;
  private UUID fieldId;
  private List<VariantModel> variants;
}
