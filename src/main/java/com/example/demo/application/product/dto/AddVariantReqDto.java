package com.example.demo.application.product.dto;

import com.example.demo.platform.shared.validator.TextValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class AddVariantReqDto {
  @Schema(description = "variant name...", example = "color", required = true)
  @NotEmpty
  @Length(min = 1, max = 100)
  @TextValidator
  private String name;

  @Schema(description = "a short summary of this variant", example = "top colors", required = true)
  @NotEmpty
  @Length(min = 1, max = 200)
  @TextValidator
  private String overview;

  @Schema(description = "list of varieties with maximum of 10 items", required = true)
  @NotNull
  @Size(max = 10)
  @Valid
  private List<AddVarietyReqDto> varieties;
}
