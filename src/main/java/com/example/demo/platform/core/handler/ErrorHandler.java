package com.example.demo.platform.core.handler;

import com.example.demo.application.product.exception.CantDeleteCategoryException;
import com.example.demo.application.product.exception.CantUpdateProductException;
import com.example.demo.application.product.exception.CategoryNotFoundException;
import com.example.demo.application.product.exception.FieldNotFoundException;
import com.example.demo.application.product.exception.ProductNotFoundException;
import com.example.demo.application.product.exception.VariantNotFoundException;
import com.example.demo.application.store.exception.AccessToStoreDeniedException;
import com.example.demo.application.store.exception.CantUpdateVerifiedStoreException;
import com.example.demo.application.store.exception.GreyScaleColorAreNotAllowedException;
import com.example.demo.application.store.exception.StoreAlreadyExistsException;
import com.example.demo.application.store.exception.StoreNotFoundException;
import com.example.demo.application.user.exception.InvalidCredentialException;
import com.example.demo.application.user.exception.OtpIsNotExpiredException;
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
    return ResponseDto.<Void>builder()
        .code(ErrorCode.INVALID_INPUT.getCode())
        .status(HttpStatus.BAD_REQUEST.value())
        .message("some fields are missing or invalid")
        .build();
  }

  @ExceptionHandler(exception = InvalidCredentialException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseDto<Void> handleInvalidCredentialError(Exception e) {

    return ResponseDto.<Void>builder()
        .code(ErrorCode.UNAUTHORIZED.getCode())
        .status(HttpStatus.UNAUTHORIZED.value())
        .message("invalid credential")
        .build();
  }

  @ExceptionHandler(AuthorizationDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseDto<Void> handleAuthorizationFailedError(Exception e) {

    return ResponseDto.<Void>builder()
        .code(ErrorCode.FORBIDDEN.getCode())
        .status(HttpStatus.FORBIDDEN.value())
        .message("access denied")
        .build();
  }

  @ExceptionHandler(StoreAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseDto<Void> handleStoreAlreadyExistsError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.SLUG_TAKEN.getCode())
        .status(HttpStatus.CONFLICT.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(GreyScaleColorAreNotAllowedException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleGreyScaleColorAreNotAllowedError(Exception e) {

    return ResponseDto.<Void>builder()
        .code(ErrorCode.GREY_SCALE_COLORS_NOT_ALLOWED.getCode())
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(StoreNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleStoreNotFoundError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.STORE_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(AccessToStoreDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseDto<Void> handleAccessToStoreDeniedError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.ACCESS_TO_STORE_DENIED.getCode())
        .status(HttpStatus.FORBIDDEN.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(CantUpdateVerifiedStoreException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleCantUpdateVerifiedStoreError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.CANT_UPDATE_VERIFIED_STORE.getCode())
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(OtpIsNotExpiredException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleOtpIsNotExpiredError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.OTP_IS_NOT_EXPIRED.getCode())
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleCategoryNotFoundError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.CATEGORY_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(FieldNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleFieldNotFoundError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.FIELD_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(CantDeleteCategoryException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleCantDeleteCategoryError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.CANT_DELETE_CATEGORY.getCode())
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleProductNotFoundError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.PRODUCT_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(CantUpdateProductException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleCantUpdateProductError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.CANT_UPDATE_PRODUCT.getCode())
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(VariantNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleVariantNotFoundError(Exception e) {
    return ResponseDto.<Void>builder()
        .code(ErrorCode.VARIANT_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .build();
  }
}
