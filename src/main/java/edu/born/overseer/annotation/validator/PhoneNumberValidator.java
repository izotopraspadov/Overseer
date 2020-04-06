package edu.born.overseer.annotation.validator;

import edu.born.overseer.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String RUSSIAN_FORMAT_NUMBER = "^((\\+\\d)((-)([0-9]){3}){2}((-)([0-9]){2}){2})$";

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(RUSSIAN_FORMAT_NUMBER, number);
    }

}
