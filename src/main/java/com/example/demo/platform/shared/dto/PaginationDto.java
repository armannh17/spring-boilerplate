package com.example.demo.platform.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PaginationDto {
  @Default
  @Schema(description = "page", example = "1", required = false)
  @Min(1)
  public Integer page = 1;

  @Default
  @Schema(description = "limit", example = "10", required = false)
  @Min(1)
  public Integer limit = 10;
}
