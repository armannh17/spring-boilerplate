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
  private Boolean published;
  private Boolean archived;
  private UUID fieldId;
  private List<VariantModel> variants;

  public void update(String name, String image, String description) {
    this.name = name;
    this.image = image;
    this.description = description;
  }

  public void publish() {
    published = true;
  }

  public void archive() {
    archived = true;
  }
}
