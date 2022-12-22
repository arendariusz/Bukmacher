package com.example.bukmacher.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectEmailValidator implements ConstraintValidator<CorrectEmail, String> {

    @Override
    public void initialize(CorrectEmail annotation) {
        ConstraintValidator.super.initialize(annotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return isEmailValid(s);
    }

    public boolean isEmailValid(String emailAddress) {

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return emailAddress.matches(regexPattern);
    }
}
