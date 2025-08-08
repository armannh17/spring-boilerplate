package com.example.demo.application.user.controller;

import com.example.demo.application.user.command.AuthenticateUserCommand;
import com.example.demo.application.user.command.LoginUserCommand;
import com.example.demo.application.user.dto.AuthenticateUserReqDto;
import com.example.demo.application.user.dto.AuthenticateUserResDto;
import com.example.demo.application.user.dto.LoginUserReqDto;
import com.example.demo.application.user.serializer.UserSerializer;
import com.example.demo.application.user.service.UserService;
import com.example.demo.platform.shared.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "User", description = "Register and Login")
public class UserController {
  private final UserSerializer userSerializer;
  private final UserService userService;

  public UserController(UserSerializer userSerializer, UserService userService) {
    this.userSerializer = userSerializer;
    this.userService = userService;
  }

  @PostMapping(path = "/login")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Login the user")
  ResponseDto<Void> loginUser(@Valid @RequestBody LoginUserReqDto dto) {
    LoginUserCommand command = userSerializer.serializeToLoginUserCommand(dto);

    userService.loginUser(command);

    return ResponseDto.success(HttpStatus.OK);
  }

  @PostMapping(path = "/auth")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Authenticate the user")
  ResponseDto<AuthenticateUserResDto> authenticateUser(
      @Valid @RequestBody AuthenticateUserReqDto dto) {
    AuthenticateUserCommand command = userSerializer.serializeToAuthenticateUserCommand(dto);

    String token = userService.authenticateUser(command);

    AuthenticateUserResDto response = userSerializer.serializeAuthenticateUserDto(token);

    return ResponseDto.success(HttpStatus.OK, response);
  }

  @PreAuthorize("isAuthenticated()")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(path = "/verify")
  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Verify the users token")
  ResponseDto<Void> verifyAuthentication() {
    return ResponseDto.success(HttpStatus.OK);
  }
}
