package com.example.demo.application.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticateUserResDto {
	private String token;
}
