package com.example.demo.platform.core.controller;

import com.example.demo.platform.core.handler.ResponseHandler;
import com.example.demo.platform.shared.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
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
  private final ResponseHandler responseHandler;

  public AppErrorController(ErrorAttributes errorAttributes, ResponseHandler responseHandler) {
    this.errorAttributes = errorAttributes;
    this.responseHandler = responseHandler;
  }

  @RequestMapping("/error")
  public ResponseEntity<ResponseDto<Void>> handleUnknownError(
      HttpServletRequest request, ServletWebRequest webRequest) {
    // get the error
    Throwable exception = errorAttributes.getError(webRequest);

    // check for invalid input
    if (exception instanceof HttpMessageNotReadableException) {
      return responseHandler.handleInvalidInputResponse();
    }

    // check for not found error
    if (exception instanceof NoResourceFoundException) {
      return responseHandler.handleNotFoundResponse();
    }

    // check for method not allowed error
    if (exception instanceof HttpRequestMethodNotSupportedException) {
      return responseHandler.handleMethodNotAllowedResponse();
    }

    // check for content not supported error
    if (exception instanceof HttpMediaTypeNotSupportedException) {
      return responseHandler.handleContentNotSupportedResponse();
    }

    // unknown error, log the error
    logger.error(exception.getMessage(), exception);

    return responseHandler.handleUnknownResponse();
  }
}
