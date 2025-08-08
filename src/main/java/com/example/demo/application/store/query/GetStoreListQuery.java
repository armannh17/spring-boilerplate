package com.example.demo.application.store.query;

import com.example.demo.platform.shared.query.PaginationQuery;
import java.util.UUID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GetStoreListQuery extends PaginationQuery {
  private UUID userId;
}
