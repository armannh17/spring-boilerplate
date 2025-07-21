package com.example.demo.application.product.command;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddVariantCommand {
  private String name;
  private String overview;
  private List<AddVarietyCommand> varieties;
  private UUID productId;
  private UUID userId;
}
