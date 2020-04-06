package edu.born.overseer.annotation;

import edu.born.overseer.annotation.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ITN {
    String message() default "Invalid ITN format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
