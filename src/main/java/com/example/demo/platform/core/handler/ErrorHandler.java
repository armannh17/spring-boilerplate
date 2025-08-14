package com.example.demo.platform.core.handler;

import com.example.demo.application.user.exception.InvalidCredentialException;
import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseDto<Void> handleValidationErrors(MethodArgumentNotValidException ex) {
    return ResponseDto.error(
        ErrorCode.INVALID_INPUT, HttpStatus.BAD_REQUEST, "some fields are missing or invalid");
  }

  @ExceptionHandler(InvalidCredentialException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseDto<Void> handleInvalidCredentialError(Exception e) {
    return ResponseDto.error(ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED, "invalid credential");
  }

  @ExceptionHandler(AuthorizationDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseDto<Void> handleAuthorizationFailedError(Exception e) {
    return ResponseDto.error(ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN, "access denied");
  }
}
