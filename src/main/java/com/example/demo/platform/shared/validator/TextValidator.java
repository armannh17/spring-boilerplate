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
@Pattern(regexp = "^[a-zA-Z0-9 .,?!:;'\"-]*$")
public @interface TextValidator {
    String message() default "text is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
