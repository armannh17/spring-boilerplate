package com.example.demo.application.user.serializer;

import com.example.demo.application.user.command.AuthenticateUserCommand;
import com.example.demo.application.user.command.LoginUserCommand;
import com.example.demo.application.user.dto.AuthenticateUserReqDto;
import com.example.demo.application.user.dto.AuthenticateUserResDto;
import com.example.demo.application.user.dto.LoginUserReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserSerializer {

  LoginUserCommand serializeToLoginUserCommand(LoginUserReqDto dto);

  AuthenticateUserCommand serializeToAuthenticateUserCommand(AuthenticateUserReqDto dto);

  @Mapping(target = "token", source = "token")
  AuthenticateUserResDto serializeAuthenticateUserDto(String token);
}
