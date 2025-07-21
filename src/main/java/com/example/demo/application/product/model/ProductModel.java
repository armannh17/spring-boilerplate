package com.example.demo.application.product.model;

import com.example.demo.application.product.exception.CantUpdateProductException;
import com.example.demo.application.product.exception.VariantNotFoundException;
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
  private UUID storeId;
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

  public void addVariant(VariantModel variant) {
    canUpdate();

    variants.add(variant);
  }

  public VariantModel removeVariant() {
    canUpdate();

    VariantModel variant =
        variants.stream()
            .filter(v -> v.getId().equals(id))
            .findFirst()
            .orElseThrow(VariantNotFoundException::new);

    variants.remove(variant);

    return variant;
  }

  private void canUpdate() {
    if (published || archived) {
      throw new CantUpdateProductException();
    }
  }
}
