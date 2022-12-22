package com.example.bukmacher.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CorrectEmailValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface CorrectEmail {
    String message() default "Podaj poprawny adres email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}