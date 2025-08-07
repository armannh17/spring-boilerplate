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

  public static class ResponseDtoBuilder<T> {
    public ResponseDtoBuilder<T> code(ErrorCode code) {
      this.code = code.getCode();
      return this;
    }

    public ResponseDtoBuilder<T> status(HttpStatus status) {
      this.status = status.value();
      return this;
    }
  }
}
