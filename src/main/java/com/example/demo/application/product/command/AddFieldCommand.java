package com.example.demo.application.product.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddFieldCommand {
  private String name;
  private UUID categoryId;
  private UUID userId;
}
