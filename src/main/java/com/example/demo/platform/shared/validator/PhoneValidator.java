package com.example.demo.platform.shared.validator;

import com.example.demo.platform.shared.validation.PhoneValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneValidation, String> {
	private static final String PATTERN = "^09\\d{9}$";

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		return phone == null ? false : phone.matches(PATTERN);
	}
}
