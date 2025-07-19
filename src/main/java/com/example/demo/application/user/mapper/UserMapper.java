package com.example.demo.application.user.mapper;

import com.example.demo.application.user.entity.CredentialEntity;
import com.example.demo.application.user.entity.UserEntity;
import com.example.demo.application.user.model.CredentialModel;
import com.example.demo.application.user.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserModel mapToUserModel(UserEntity user);

  UserEntity mapToUserEntity(UserModel model);

  CredentialModel mapToCredentialModel(CredentialEntity credential);

  CredentialEntity mapToCredentialEntity(CredentialModel credential);
}
