package com.example.demo.application.user.dto;

import com.example.demo.platform.shared.validation.OtpValidation;
import com.example.demo.platform.shared.validation.PhoneValidation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticateUserReqDto {
	@Schema(description = "phone number", example = "0912...", required = true)
	@NotEmpty
	@PhoneValidation
	private String phone;

	@Schema(description = "otp code", example = "123...", required = true)
	@NotEmpty
	@OtpValidation
	private String otp;
}
