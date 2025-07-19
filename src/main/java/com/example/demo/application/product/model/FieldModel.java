package com.example.demo.application.product.model;

import com.example.demo.platform.shared.model.BaseModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FieldModel extends BaseModel {
  private String name;
}
