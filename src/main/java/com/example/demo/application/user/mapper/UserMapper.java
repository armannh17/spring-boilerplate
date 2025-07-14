package com.example.demo.application.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.application.user.entity.CredentialEntity;
import com.example.demo.application.user.entity.UserEntity;
import com.example.demo.application.user.model.CredentialModel;
import com.example.demo.application.user.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(source = "credential", target = "credential")
	UserModel mapToUserModel(UserEntity user);

	@Mapping(source = "credential", target = "credential")
	UserEntity mapToUserEntity(UserModel model);

	CredentialModel mapToCredentialModel(CredentialEntity credential);

	CredentialEntity mapToCredentialEntity(CredentialModel credential);
}
