package com.example.demo.application.product.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateProductCommand {
  private UUID id;
  private String name;
  private String image;
  private String description;
  private UUID userId;
}
