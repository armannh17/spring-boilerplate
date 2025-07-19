package com.example.demo.application.user.service;

import com.example.demo.application.user.command.AuthenticateUserCommand;
import com.example.demo.application.user.command.LoginUserCommand;
import com.example.demo.application.user.config.UserConfig;
import com.example.demo.application.user.event.LoginAttemptedEvent;
import com.example.demo.application.user.exception.InvalidCredentialException;
import com.example.demo.application.user.helper.TokenHelper;
import com.example.demo.application.user.model.CredentialModel;
import com.example.demo.application.user.model.UserModel;
import com.example.demo.application.user.repository.UserRepository;
import com.example.demo.application.user.type.Role;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final ApplicationEventPublisher publisher;
  private final UserRepository userRepository;
  private final UserConfig userConfig;
  private final TokenHelper tokenHelper;

  public UserService(
      ApplicationEventPublisher publisher,
      UserRepository userRepository,
      UserConfig userConfig,
      TokenHelper tokenHelper) {
    this.publisher = publisher;
    this.userRepository = userRepository;
    this.userConfig = userConfig;
    this.tokenHelper = tokenHelper;
  }

  @Transactional
  public void loginUser(LoginUserCommand command) {
    // login or register the user
    UserModel user =
        userRepository
            .findByPhone(command.getPhone())
            .orElseGet(() -> registerUser(command.getPhone()));

    // make the otp
    user.getCredential().requestOtp(userConfig.getOtpLength(), userConfig.getOtpExpiry());

    // save the user
    userRepository.save(user);

    // publish a login attempt event
    LoginAttemptedEvent event =
        LoginAttemptedEvent.builder()
            .phone(user.getPhone())
            .otp(user.getCredential().getOtpCode())
            .build();

    publisher.publishEvent(event);
  }

  @Transactional
  public String authenticateUser(AuthenticateUserCommand command) {
    // find the user with the given phone
    UserModel user =
        userRepository.findByPhone(command.getPhone()).orElseThrow(InvalidCredentialException::new);

    // verify the provided otp code
    user.getCredential().verifyOtp(command.getOtp());

    // generate a jwt token
    String token = tokenHelper.generateToken(user.getId());

    return token;
  }

  private UserModel registerUser(String phone) {
    // make a new user
    UserModel user =
        UserModel.builder()
            .phone(phone)
            .locked(false)
            .role(Role.USER)
            .credential(CredentialModel.builder().build())
            .build();

    return user;
  }
}
