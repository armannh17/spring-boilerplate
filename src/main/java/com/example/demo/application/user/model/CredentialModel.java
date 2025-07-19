package com.example.demo.application.user.model;

import com.example.demo.application.user.exception.InvalidCredentialException;
import com.example.demo.application.user.exception.OtpIsNotExpiredException;
import com.example.demo.platform.shared.model.BaseModel;
import java.util.Date;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CredentialModel extends BaseModel {
  private String otpCode;
  private Date otpExpiry;

  public void requestOtp(Integer length, Long expiry) {
    // check for existing otp
    Date currentTimestamp = new Date();

    if (otpCode != null && otpExpiry.after(currentTimestamp)) {
      throw new OtpIsNotExpiredException();
    }

    // generate a random number for otp
    int min = (int) Math.pow(10, length - 1);
    int max = (int) Math.pow(10, length) - 1;
    int number = new java.util.Random().nextInt(max - min + 1) + min;

    otpCode = String.valueOf(number);

    // set the expiry time
    otpExpiry = new Date(System.currentTimeMillis() + expiry);
  }

  public void verifyOtp(String otp) {
    Date currentTimestamp = new Date();

    if (otp.equals(otpCode) && otpExpiry.after(currentTimestamp)) {
      return;
    }

    throw new InvalidCredentialException();
  }
}
