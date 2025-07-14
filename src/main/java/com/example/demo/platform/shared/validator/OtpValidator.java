package com.example.demo.platform.shared.validator;

import com.example.demo.platform.shared.validation.OtpValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OtpValidator implements ConstraintValidator<OtpValidation, String> {
	private static final String PATTERN = "^\\d{1,10}$";

	@Override
	public boolean isValid(String otp, ConstraintValidatorContext context) {
		return otp == null ? false : otp.matches(PATTERN);
	}
}
