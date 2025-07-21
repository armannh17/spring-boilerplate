package com.example.demo.application.product.model;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class VariantModel {
  private String name;
  private String overview;
  private List<VarietyModel> varieties;
}
