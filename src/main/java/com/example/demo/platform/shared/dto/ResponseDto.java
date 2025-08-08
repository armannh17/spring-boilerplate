package com.example.demo.platform.shared.dto;

import com.example.demo.platform.shared.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDto<T> {
  private int code;
  private int status;
  private String message;
  private T data;

  public static <T> ResponseDto<T> success(HttpStatus status) {
    return ResponseDto.<T>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(status.value())
        .message("successful")
        .data(null)
        .build();
  }

  public static <T> ResponseDto<T> success(HttpStatus status, T data) {
    return ResponseDto.<T>builder()
        .code(ErrorCode.NO_ERROR.getCode())
        .status(status.value())
        .message("successful")
        .data(data)
        .build();
  }

  public static ResponseDto<Void> error(ErrorCode code, HttpStatus status, String message) {
    return ResponseDto.<Void>builder()
        .code(code.getCode())
        .status(status.value())
        .message(message)
        .data(null)
        .build();
  }
}
