package edu.born.overseer.annotation.validator;

import edu.born.overseer.annotation.PaymentFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PaymentFormatValidator implements ConstraintValidator<PaymentFormat, String> {

    private static final String FORMAT = "^[0-9]{1,3}-[0-9]{1,3}-[0-9]{1,3}$";

    @Override
    public boolean isValid(String paymentFormat, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(FORMAT, paymentFormat);
    }
}
