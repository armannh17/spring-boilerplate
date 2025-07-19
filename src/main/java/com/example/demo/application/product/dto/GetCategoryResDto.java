package com.example.demo.application.product.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCategoryResDto {
  private String id;
  private String name;
  private String image;
  private String description;
  private List<GetFieldResDto> fields;
}
