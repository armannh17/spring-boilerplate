package com.example.demo.application.product.dto;

import com.example.demo.platform.shared.validator.TextValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class AddFieldReqDto {
  @Schema(description = "field name...", example = "field one", required = true)
  @NotEmpty
  @Length(min = 1, max = 100)
  @TextValidator
  private String name;
}
