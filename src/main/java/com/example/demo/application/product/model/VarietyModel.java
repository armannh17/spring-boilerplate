package com.example.demo.application.product.model;

import com.example.demo.platform.shared.model.BaseModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class VarietyModel extends BaseModel {
  private String name;
  private String color;
}
