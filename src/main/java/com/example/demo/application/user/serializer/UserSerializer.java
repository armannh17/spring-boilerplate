package com.example.demo.application.user.serializer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.application.user.command.LoginUserCommand;
import com.example.demo.application.user.command.VerifyUserCommand;
import com.example.demo.application.user.dto.LoginUserReqDto;
import com.example.demo.application.user.dto.VerifyUserReqDto;
import com.example.demo.application.user.dto.VerifyUserResDto;
import com.example.demo.platform.shared.dto.ResponseDto;

@Component
public class UserSerializer {

	public LoginUserCommand serializeLoginUserCommand(LoginUserReqDto dto) {
		return LoginUserCommand.builder().phone(dto.getPhone()).build();
	}

	public ResponseDto<Void> serializeLoginUserResponse() {
		return ResponseDto.<Void>builder().status(HttpStatus.OK.value()).message("successful").build();
	}

	public VerifyUserCommand serializeVeirfyUserCommand(VerifyUserReqDto dto) {
		return VerifyUserCommand.builder().phone(dto.getPhone()).otp(dto.getOtp()).build();
	}

	public ResponseDto<VerifyUserResDto> serializeVerifyUserResponse(String token) {
		return ResponseDto.<VerifyUserResDto>builder().status(HttpStatus.OK.value()).message("successful")
				.data(VerifyUserResDto.builder().token(token).build()).build();
	}
}
