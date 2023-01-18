package fr.insy2s.commerce.shoponlineback.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintsValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
