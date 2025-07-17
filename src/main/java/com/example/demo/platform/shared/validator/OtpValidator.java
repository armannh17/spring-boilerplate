package com.example.demo.platform.shared.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^\\d{1,10}$")
public @interface OtpValidator {
	String message() default "otp is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}