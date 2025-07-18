package com.example.demo.application.store.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import com.example.demo.application.store.constant.Alignment;
import com.example.demo.application.store.constant.Detail;
import com.example.demo.application.store.constant.Radius;
import com.example.demo.platform.shared.validator.ColorValidator;
import com.example.demo.platform.shared.validator.SlugValidator;
import com.example.demo.platform.shared.validator.TextValidator;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakeStoreReqDto {
	@Schema(description = "your brand name", example = "shop...", required = true)
	@NotEmpty
	@Length(min = 1, max = 100)
	@TextValidator
	private String name;

	@Schema(description = "your domain slug", example = "my-shop", required = true)
	@NotEmpty
	@Length(min = 1, max = 100)
	@SlugValidator
	private String slug;

	@Schema(description = "brief inro to your services", example = "biggest online shop...", required = true)
	@NotEmpty
	@Length(min = 1, max = 200)
	@TextValidator
	private String brief;

	@Schema(description = "a short overview of your store", example = "top seller on industry...", required = true)
	@NotEmpty
	@Length(min = 1, max = 200)
	@TextValidator
	private String description;

	@Schema(description = "your uploaded cover image", example = "48d548aa-1c7f-4869-8d29-06be89982989", required = true)
	@NotEmpty
	@UUID
	private String image;

	@Schema(description = "your brands main color", example = "#0000FF", required = true)
	@NotEmpty
	@Length(min = 1, max = 100)
	@ColorValidator
	private String color;

	@Schema(description = "amount of raduis applied to elements", example = "SMALL", required = true)
	@NotNull
	private Radius radius;

	@Schema(description = "level of detail on components", example = "LOW", required = true)
	@NotNull
	private Detail detail;

	@Schema(description = "the alignment of the elements", example = "LEFT", required = true)
	@NotNull
	private Alignment alignment;
}
