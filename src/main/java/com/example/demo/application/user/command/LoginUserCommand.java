package com.example.demo.application.user.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserCommand {
  private final String phone;
}
