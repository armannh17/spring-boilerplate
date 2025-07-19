package com.example.demo.application.user.model;

import com.example.demo.application.user.type.Role;
import com.example.demo.platform.shared.model.BaseModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserModel extends BaseModel {
  private String phone;
  private Boolean locked;
  private Role role;
  private CredentialModel credential;
}
