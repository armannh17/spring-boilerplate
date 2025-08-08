package com.example.demo.platform.shared.query;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PaginationQuery {
  private Integer page;
  private Integer limit;
}
