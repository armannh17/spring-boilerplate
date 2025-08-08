package com.example.demo.platform.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDto {
  @Schema(description = "page", example = "1", required = true)
  @Min(1)
  private Integer page = 1;

  @Schema(description = "limit", example = "10", required = false)
  @Min(1)
  private Integer limit = 10;
}
