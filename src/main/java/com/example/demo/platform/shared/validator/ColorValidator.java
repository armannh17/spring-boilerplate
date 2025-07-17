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
@Pattern(regexp = "^#(?:[0-9a-fA-F]{3}|[0-9a-fA-F]{6})$")
public @interface ColorValidator {
	String message() default "color is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}