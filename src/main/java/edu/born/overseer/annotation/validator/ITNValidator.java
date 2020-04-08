package edu.born.overseer.annotation.validator;

import edu.born.overseer.annotation.ITN;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ITNValidator implements ConstraintValidator<ITN, String> {

    private static final Pattern ITN_PATTERN = Pattern.compile("^\\d{10}|\\d{12}$");

    @Override
    public boolean isValid(String itn, ConstraintValidatorContext constraintValidatorContext) {
        return ITN_PATTERN.matcher(itn).find();
    }

}
