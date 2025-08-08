package com.example.demo.application.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreListResDto {
  private String name;
  private String slug;
  private String brief;
  private String image;
  private Boolean verified;
}
