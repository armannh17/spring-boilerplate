package com.example.demo.application.product.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakeProductCommand {
  private String name;
  private String image;
  private String description;
  private UUID storeId;
  private UUID fieldId;
  private UUID userdId;
}
