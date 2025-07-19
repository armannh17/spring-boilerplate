package com.example.demo.application.user.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticateUserCommand {
  private final String phone;
  private final String otp;
}
