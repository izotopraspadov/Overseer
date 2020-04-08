package edu.born.overseer.annotation.validator;

import edu.born.overseer.annotation.PaymentFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PaymentFormatValidator implements ConstraintValidator<PaymentFormat, String> {

    private static final Pattern PAYMENT_FORMAT = Pattern.compile("^\\d{3}|\\d{2}-\\d{2}|\\d{2}-\\d{2}-\\d{2}$");

    @Override
    public boolean isValid(String paymentFormat, ConstraintValidatorContext constraintValidatorContext) {
        return PAYMENT_FORMAT.matcher(paymentFormat).matches();
    }

}
