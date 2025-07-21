package com.example.demo.application.product.model;

import com.example.demo.application.product.exception.CantDeleteCategoryException;
import com.example.demo.application.product.exception.FieldNotFoundException;
import com.example.demo.platform.shared.model.BaseModel;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CategoryModel extends BaseModel {
  private String name;
  private String image;
  private String description;
  private UUID storeId;
  private List<FieldModel> fields;

  public void update(String name, String image, String description) {
    this.name = name;
    this.image = image;
    this.description = description;
  }

  public void delete() {
    if (!fields.isEmpty()) {
      throw new CantDeleteCategoryException();
    }
  }

  public void addField(FieldModel field) {
    fields.add(field);
  }

  public FieldModel updateField(UUID id, String name) {
    FieldModel field =
        fields.stream()
            .filter(f -> f.getId().equals(id))
            .findFirst()
            .orElseThrow(FieldNotFoundException::new);

    field.update(name);

    return field;
  }

  public FieldModel deleteField(UUID id) {
    FieldModel field =
        fields.stream()
            .filter(f -> f.getId().equals(id))
            .findFirst()
            .orElseThrow(FieldNotFoundException::new);

    fields.remove(field);

    return field;
  }
}
