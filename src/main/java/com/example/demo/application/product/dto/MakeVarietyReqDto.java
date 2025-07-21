package com.example.demo.application.product.dto;

import com.example.demo.platform.shared.validator.ColorValidator;
import com.example.demo.platform.shared.validator.TextValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class MakeVarietyReqDto {
  @Schema(description = "variety name...", example = "blue", required = true)
  @NotEmpty
  @Length(min = 1, max = 100)
  @TextValidator
  private String name;

  @Schema(description = "varity background color", example = "#0000FF", required = true)
  @NotEmpty
  @Length(min = 1, max = 100)
  @ColorValidator
  private String color;
}
