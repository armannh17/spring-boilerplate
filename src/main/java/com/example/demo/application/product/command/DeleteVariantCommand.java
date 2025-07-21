package com.example.demo.application.product.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteVariantCommand {
  private UUID id;
  private UUID productId;
  private UUID userId;
}
