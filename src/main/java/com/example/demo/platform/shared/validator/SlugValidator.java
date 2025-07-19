package com.example.demo.platform.shared.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$")
public @interface SlugValidator {
  String message() default "slug is not valid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
