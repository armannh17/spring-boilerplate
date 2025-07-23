package com.example.demo.platform.core.controller;

import com.example.demo.application.user.exception.InvalidTokenException;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Hidden
@RestController
public class AppErrorController implements ErrorController {
  private static final Logger logger = LoggerFactory.getLogger(AppErrorController.class);

  private final ErrorAttributes errorAttributes;

  public AppErrorController(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @RequestMapping("/error")
  public ResponseEntity<ResponseDto<Void>> handleUnknownError(
      HttpServletRequest request, ServletWebRequest webRequest) {
    // get the error
    Throwable exception = errorAttributes.getError(webRequest);

    // check for invalid input
    if (exception instanceof HttpMessageNotReadableException) {
      return handleInvalidInputError();
    }

    // check for auth error
    if (exception instanceof InvalidTokenException error) {
      return handleUnauthorizedError(error);
    }

    // check for not found error
    if (exception instanceof NoResourceFoundException) {
      return handleNotFoundError();
    }

    // check for method not allowed error
    if (exception instanceof HttpRequestMethodNotSupportedException) {
      return handleMethodNotAllowedError();
    }

    // check for content not supported error
    if (exception instanceof HttpMediaTypeNotSupportedException) {
      return handleContentNotSupportedError();
    }

    // unknown error, log the error
    logger.error(exception.getMessage());

    return handleUnknownError();
  }

  private ResponseEntity<ResponseDto<Void>> handleInvalidInputError() {
    int code = ErrorCode.INVALID_INPUT.getCode();
    int status = HttpStatus.BAD_REQUEST.value();
    String message = "some fields are missing or invalid";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  private ResponseEntity<ResponseDto<Void>> handleUnauthorizedError(Exception exception) {
    int code = ErrorCode.UNAUTHORIZED.getCode();
    int status = HttpStatus.UNAUTHORIZED.value();
    String message = exception.getMessage();

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  private ResponseEntity<ResponseDto<Void>> handleNotFoundError() {
    int code = ErrorCode.NOT_FOUND.getCode();
    int status = HttpStatus.NOT_FOUND.value();
    String message = "uri or asset not found";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  private ResponseEntity<ResponseDto<Void>> handleMethodNotAllowedError() {
    int code = ErrorCode.METHOD_NOT_ALLOWED.getCode();
    int status = HttpStatus.METHOD_NOT_ALLOWED.value();
    String message = "method not allowed";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  private ResponseEntity<ResponseDto<Void>> handleContentNotSupportedError() {
    int code = ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode();
    int status = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
    String message = "content not supported";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  private ResponseEntity<ResponseDto<Void>> handleUnknownError() {
    int code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String message = "internal server error";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }
}
