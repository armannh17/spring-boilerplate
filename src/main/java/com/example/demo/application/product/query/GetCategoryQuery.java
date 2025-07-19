package com.example.demo.application.product.query;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCategoryQuery {
  private UUID storeId;
}
