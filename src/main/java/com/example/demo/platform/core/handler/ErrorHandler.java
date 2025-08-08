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

  @ExceptionHandler(StoreAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseDto<Void> handleStoreAlreadyExistsError(Exception e) {
    return ResponseDto.error(ErrorCode.SLUG_TAKEN, HttpStatus.CONFLICT, e.getMessage());
  }

  @ExceptionHandler(GreyScaleColorAreNotAllowedException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleGreyScaleColorAreNotAllowedError(Exception e) {
    return ResponseDto.error(
        ErrorCode.GREY_SCALE_COLORS_NOT_ALLOWED, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
  }

  @ExceptionHandler(StoreNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleStoreNotFoundError(Exception e) {
    return ResponseDto.error(ErrorCode.STORE_NOT_FOUND, HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(AccessToStoreDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseDto<Void> handleAccessToStoreDeniedError(Exception e) {
    return ResponseDto.error(
        ErrorCode.ACCESS_TO_STORE_DENIED, HttpStatus.FORBIDDEN, e.getMessage());
  }

  @ExceptionHandler(CantUpdateVerifiedStoreException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleCantUpdateVerifiedStoreError(Exception e) {
    return ResponseDto.error(
        ErrorCode.CANT_UPDATE_VERIFIED_STORE, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
  }

  @ExceptionHandler(OtpIsNotExpiredException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleOtpIsNotExpiredError(Exception e) {
    return ResponseDto.error(
        ErrorCode.OTP_IS_NOT_EXPIRED, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleCategoryNotFoundError(Exception e) {
    return ResponseDto.error(ErrorCode.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(FieldNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleFieldNotFoundError(Exception e) {
    return ResponseDto.error(ErrorCode.FIELD_NOT_FOUND, HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(CantDeleteCategoryException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleCantDeleteCategoryError(Exception e) {
    return ResponseDto.error(
        ErrorCode.CANT_DELETE_CATEGORY, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleProductNotFoundError(Exception e) {
    return ResponseDto.error(ErrorCode.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(CantUpdateProductException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseDto<Void> handleCantUpdateProductError(Exception e) {
    return ResponseDto.error(
        ErrorCode.CANT_UPDATE_PRODUCT, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
  }

  @ExceptionHandler(VariantNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseDto<Void> handleVariantNotFoundError(Exception e) {
    return ResponseDto.error(ErrorCode.VARIANT_NOT_FOUND, HttpStatus.NOT_FOUND, e.getMessage());
  }
}
