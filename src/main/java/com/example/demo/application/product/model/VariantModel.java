package com.example.demo.application.product.model;

import com.example.demo.platform.shared.model.BaseModel;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class VariantModel extends BaseModel {
  private String name;
  private String overview;
  private List<VarietyModel> varieties;
}
