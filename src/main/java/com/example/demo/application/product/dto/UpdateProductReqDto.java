package com.example.demo.application.product.dto;

import com.example.demo.platform.shared.validator.TextValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

@Getter
@Builder
public class UpdateProductReqDto {
  @Schema(description = "product name...", example = "iphone 16", required = true)
  @NotEmpty
  @Length(min = 1, max = 100)
  @TextValidator
  private String name;

  @Schema(
      description = "your uploaded image",
      example = "48d548aa-1c7f-4869-8d29-06be89982989",
      required = true)
  @NotEmpty
  @UUID
  private String image;

  @Schema(description = "a short summary of the product", example = "top seller", required = true)
  @NotEmpty
  @Length(min = 1, max = 200)
  @TextValidator
  private String description;
}
