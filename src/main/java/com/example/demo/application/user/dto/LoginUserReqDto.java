package com.example.demo.application.user.dto;

import com.example.demo.platform.shared.validator.PhoneValidator;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserReqDto {
	@Schema(description = "phone number", example = "0912...", required = true)
	@NotEmpty
	@PhoneValidator
	private String phone;
}
