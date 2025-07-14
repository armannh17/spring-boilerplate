package com.example.demo.application.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.user.command.LoginUserCommand;
import com.example.demo.application.user.command.VerifyUserCommand;
import com.example.demo.application.user.dto.LoginUserReqDto;
import com.example.demo.application.user.dto.VerifyUserReqDto;
import com.example.demo.application.user.dto.VerifyUserResDto;
import com.example.demo.application.user.serializer.UserSerializer;
import com.example.demo.application.user.service.UserService;
import com.example.demo.platform.shared.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
		LoginUserCommand command = userSerializer.serializeLoginUserCommand(dto);

		userService.loginUser(command);

		return userSerializer.serializeLoginUserResponse();
	}

	@PostMapping(path = "/verify")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Verify the user")
	ResponseDto<VerifyUserResDto> verifyUser(@Valid @RequestBody VerifyUserReqDto dto) {
		VerifyUserCommand command = userSerializer.serializeVeirfyUserCommand(dto);

		String token = userService.verifyUser(command);

		return userSerializer.serializeVerifyUserResponse(token);
	}

	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/")
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Check user auth status")
	ResponseDto<Void> getList() {
		return ResponseDto.<Void>builder().status(200).message("successful").build();
	}
}
