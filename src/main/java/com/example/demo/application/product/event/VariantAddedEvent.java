package com.example.demo.application.product.event;

import com.example.demo.platform.shared.event.BaseEvent;
import java.util.UUID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class VariantAddedEvent extends BaseEvent<Void> {
  private UUID id;
  private UUID productId;
  private UUID storeId;
  private UUID userId;
}
