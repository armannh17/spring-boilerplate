package com.example.demo.application.user.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyUserCommand {
	private final String phone;
	private final String otp;
}
