package com.example.demo.platform.core.handler;

import com.example.demo.platform.shared.constant.ErrorCode;
import com.example.demo.platform.shared.dto.ResponseDto;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHandler {
  private final ObjectMapper mapper = new ObjectMapper();

  public ResponseEntity<ResponseDto<Void>> handleInvalidInputResponse() {
    int code = ErrorCode.INVALID_INPUT.getCode();
    int status = HttpStatus.BAD_REQUEST.value();
    String message = "some fields are missing or invalid";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  public void handleUnauthorizedResponse(HttpServletResponse response)
      throws StreamWriteException, DatabindException, IOException {
    int code = ErrorCode.UNAUTHORIZED.getCode();
    int status = HttpStatus.UNAUTHORIZED.value();
    String message = "token is missing or invalid";

    response.setStatus(status);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    mapper.writeValue(
        response.getWriter(),
        ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  public ResponseEntity<ResponseDto<Void>> handleNotFoundResponse() {
    int code = ErrorCode.NOT_FOUND.getCode();
    int status = HttpStatus.NOT_FOUND.value();
    String message = "uri or asset not found";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  public ResponseEntity<ResponseDto<Void>> handleMethodNotAllowedResponse() {
    int code = ErrorCode.METHOD_NOT_ALLOWED.getCode();
    int status = HttpStatus.METHOD_NOT_ALLOWED.value();
    String message = "method not allowed";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  public ResponseEntity<ResponseDto<Void>> handleContentNotSupportedResponse() {
    int code = ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode();
    int status = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();
    String message = "content not supported";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }

  public ResponseEntity<ResponseDto<Void>> handleUnknownResponse() {
    int code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String message = "internal server error";

    return ResponseEntity.status(status)
        .body(ResponseDto.<Void>builder().code(code).status(status).message(message).build());
  }
}
