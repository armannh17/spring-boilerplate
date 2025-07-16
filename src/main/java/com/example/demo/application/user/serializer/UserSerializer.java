package com.example.demo.application.user.serializer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.application.user.command.AuthenticateUserCommand;
import com.example.demo.application.user.command.LoginUserCommand;
import com.example.demo.application.user.dto.AuthenticateUserReqDto;
import com.example.demo.application.user.dto.AuthenticateUserResDto;
import com.example.demo.application.user.dto.LoginUserReqDto;
import com.example.demo.platform.shared.dto.ResponseDto;

@Component
public class UserSerializer {

	public LoginUserCommand serializeLoginUserCommand(LoginUserReqDto dto) {
		return LoginUserCommand.builder().phone(dto.getPhone()).build();
	}

	public ResponseDto<Void> serializeLoginUserResponse() {
		return ResponseDto.<Void>builder().status(HttpStatus.OK.value()).message("successful").build();
	}

	public AuthenticateUserCommand serializeAuthenticateUserCommand(AuthenticateUserReqDto dto) {
		return AuthenticateUserCommand.builder().phone(dto.getPhone()).otp(dto.getOtp()).build();
	}

	public ResponseDto<AuthenticateUserResDto> serializeAuthenticateUserResponse(String token) {
		return ResponseDto.<AuthenticateUserResDto>builder().status(HttpStatus.OK.value()).message("successful")
				.data(AuthenticateUserResDto.builder().token(token).build()).build();
	}

	public ResponseDto<Void> serializeVerifyAuthenticationResponse() {
		return ResponseDto.<Void>builder().status(HttpStatus.OK.value()).message("successful").build();
	}
}
