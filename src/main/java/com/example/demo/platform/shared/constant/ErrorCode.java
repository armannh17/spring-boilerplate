package com.example.demo.platform.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  // generic
  NO_ERROR(0),
  INVALID_INPUT(1001),
  UNAUTHORIZED(1002),
  FORBIDDEN(1003),
  NOT_FOUND(1004),
  METHOD_NOT_ALLOWED(1005),
  UNSUPPORTED_MEDIA_TYPE(1006),
  INTERNAL_SERVER_ERROR(1007),

  // store
  SLUG_TAKEN(2001),
  GREY_SCALE_COLORS_NOT_ALLOWED(2002),
  STORE_NOT_FOUND(2003);

  private final int code;
}
