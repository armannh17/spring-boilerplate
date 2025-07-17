package com.example.demo.platform.core.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.application.store.exception.GreyScaleColorAreNotAllowedException;
import com.example.demo.application.store.exception.StoreAlreadyExistsException;
import com.example.demo.application.store.exception.StoreNotFoundException;
import com.example.demo.application.user.exception.InvalidCredentialException;
import com.example.demo.platform.shared.dto.ResponseDto;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDto<Void> handleValidationErrors(MethodArgumentNotValidException ex) {
		ResponseDto<Void> response = ResponseDto.<Void>builder().status(HttpStatus.BAD_REQUEST.value())
				.message("some fields are missing or invalid").build();

		return response;
	}

	@ExceptionHandler(exception = InvalidCredentialException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseDto<Void> handleInvalidCredentialError(Exception e) {
		ResponseDto<Void> response = ResponseDto.<Void>builder().status(HttpStatus.UNAUTHORIZED.value())
				.message("invalid credential").build();

		return response;
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseDto<Void> handleAuthorizationFailedError(Exception e) {
		ResponseDto<Void> response = ResponseDto.<Void>builder().status(HttpStatus.FORBIDDEN.value())
				.message("access denied").build();

		return response;
	}

	@ExceptionHandler(StoreAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseDto<Void> handleStoreAlreadyExistsError(Exception e) {
		ResponseDto<Void> response = ResponseDto.<Void>builder().status(HttpStatus.CONFLICT.value())
				.message(e.getMessage()).build();

		return response;
	}

	@ExceptionHandler(GreyScaleColorAreNotAllowedException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseDto<Void> handleGreyScaleColorAreNotAllowedError(Exception e) {
		ResponseDto<Void> response = ResponseDto.<Void>builder().status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.message(e.getMessage()).build();

		return response;
	}

	@ExceptionHandler(StoreNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseDto<Void> handleStoreNotFoundError(Exception e) {
		ResponseDto<Void> response = ResponseDto.<Void>builder().status(HttpStatus.NOT_FOUND.value())
				.message(e.getMessage()).build();

		return response;
	}
}
