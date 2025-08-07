package com.example.demo.platform.shared.dto;

import com.example.demo.platform.shared.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDto<T> {
  private ErrorCode code;
  private HttpStatus status;
  private String message;
  private T data;
}
