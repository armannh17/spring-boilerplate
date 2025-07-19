package com.example.demo.application.user.event;

import com.example.demo.platform.shared.event.BaseEvent;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LoginAttemptedEvent extends BaseEvent<String> {
  private final String phone;
  private final String otp;
}
