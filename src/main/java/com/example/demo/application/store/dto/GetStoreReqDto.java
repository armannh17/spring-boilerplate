package com.example.demo.application.store.dto;

import org.hibernate.validator.constraints.Length;

import com.example.demo.platform.shared.validator.SlugValidator;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStoreReqDto {
	@Schema(description = "your domain slug", example = "my-shop", required = true)
	@NotEmpty
	@Length(min = 1, max = 100)
	@SlugValidator
	private String slug;
}
