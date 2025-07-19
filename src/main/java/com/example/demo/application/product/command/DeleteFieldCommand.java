package com.example.demo.application.product.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteFieldCommand {
  private UUID id;
  private UUID categoryId;
  private UUID userId;
}
