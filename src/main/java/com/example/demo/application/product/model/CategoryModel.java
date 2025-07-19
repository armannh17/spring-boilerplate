package com.example.demo.application.product.model;

import com.example.demo.platform.shared.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CategoryModel extends BaseModel {
  private String name;
  private String image;
  private String description;
  private UUID storeId;

  @Builder.Default private List<FieldModel> fields = new ArrayList<FieldModel>();
}
