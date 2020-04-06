package edu.born.overseer.annotation;

import edu.born.overseer.annotation.validator.PaymentFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaymentFormatValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PaymentFormat {
    String message() default "Invalid payment format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
