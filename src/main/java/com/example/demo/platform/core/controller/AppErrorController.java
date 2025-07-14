package com.example.demo.platform.core.controller;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.demo.application.user.exception.InvalidTokenException;
import com.example.demo.platform.shared.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;

@Hidden
@RestController
public class AppErrorController implements ErrorController {
	private final ErrorAttributes errorAttributes;

	public AppErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	@RequestMapping("/error")
	public ResponseEntity<ResponseDto<Void>> handleUnknownError(HttpServletRequest request,
			ServletWebRequest webRequest) {
		// get the error
		Throwable exception = errorAttributes.getError(webRequest);

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
		System.out.println(exception.getMessage());

		return handleUnknownError();
	}

	private ResponseEntity<ResponseDto<Void>> handleUnauthorizedError(Exception exception) {
		int status = HttpStatus.UNAUTHORIZED.value();
		String message = exception.getMessage();

		return ResponseEntity.status(status).body(ResponseDto.<Void>builder().status(status).message(message).build());
	}

	private ResponseEntity<ResponseDto<Void>> handleNotFoundError() {
		int status = HttpStatus.NOT_FOUND.value();
		String message = "uri or asset not found";

		return ResponseEntity.status(status).body(ResponseDto.<Void>builder().status(status).message(message).build());
	}

	private ResponseEntity<ResponseDto<Void>> handleMethodNotAllowedError() {
		int status = HttpStatus.METHOD_NOT_ALLOWED.value();
		String message = "method not allowed";

		return ResponseEntity.status(status).body(ResponseDto.<Void>builder().status(status).message(message).build());
	}

	private ResponseEntity<ResponseDto<Void>> handleContentNotSupportedError() {
		int status = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
		String message = "content not supported";

		return ResponseEntity.status(status).body(ResponseDto.<Void>builder().status(status).message(message).build());
	}

	private ResponseEntity<ResponseDto<Void>> handleUnknownError() {
		int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		String message = "internal server error";

		return ResponseEntity.status(status).body(ResponseDto.<Void>builder().status(status).message(message).build());
	}
}
