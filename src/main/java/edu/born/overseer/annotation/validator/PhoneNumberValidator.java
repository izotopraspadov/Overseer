package edu.born.overseer.annotation.validator;

import edu.born.overseer.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final Pattern RUSSIAN_NUMBER_FORMAT = Pattern.compile("^((\\+\\d)(-([0-9]){3}){2}(-([0-9]){2}){2})$");

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        return RUSSIAN_NUMBER_FORMAT.matcher(number).matches();
    }

}
