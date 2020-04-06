package edu.born.overseer.annotation.validator;

import edu.born.overseer.annotation.ITN;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ITNValidator implements ConstraintValidator<ITN, String> {
    @Override
    public boolean isValid(String itn, ConstraintValidatorContext constraintValidatorContext) {
        int length = itn.length();
        if (length == 7 || length == 11)
            if (Pattern.matches("\\d+", itn))
                return true;
        return false;
    }
}
