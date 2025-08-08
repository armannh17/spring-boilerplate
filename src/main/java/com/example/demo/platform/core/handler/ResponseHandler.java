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
    ErrorCode code = ErrorCode.INVALID_INPUT;
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String message = "some fields are missing or invalid";

    return ResponseEntity.status(status).body(ResponseDto.error(code, status, message));
  }

  public void handleUnauthorizedResponse(HttpServletResponse response)
      throws StreamWriteException, DatabindException, IOException {
    ErrorCode code = ErrorCode.UNAUTHORIZED;
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    String message = "token is missing or invalid";

    response.setStatus(status.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    mapper.writeValue(response.getWriter(), ResponseDto.error(code, status, message));
  }

  public ResponseEntity<ResponseDto<Void>> handleNotFoundResponse() {
    ErrorCode code = ErrorCode.NOT_FOUND;
    HttpStatus status = HttpStatus.NOT_FOUND;
    String message = "uri or asset not found";

    return ResponseEntity.status(status).body(ResponseDto.error(code, status, message));
  }

  public ResponseEntity<ResponseDto<Void>> handleMethodNotAllowedResponse() {
    ErrorCode code = ErrorCode.METHOD_NOT_ALLOWED;
    HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
    String message = "method not allowed";

    return ResponseEntity.status(status).body(ResponseDto.error(code, status, message));
  }

  public ResponseEntity<ResponseDto<Void>> handleContentNotSupportedResponse() {
    ErrorCode code = ErrorCode.UNSUPPORTED_MEDIA_TYPE;
    HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    String message = "content not supported";

    return ResponseEntity.status(status).body(ResponseDto.error(code, status, message));
  }

  public ResponseEntity<ResponseDto<Void>> handleUnknownResponse() {
    ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String message = "internal server error";

    return ResponseEntity.status(status).body(ResponseDto.error(code, status, message));
  }
}
