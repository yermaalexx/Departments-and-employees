package com.alexx.departmentsandemployees.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OverMinimumAgeValidator.class)
@Documented
public @interface OverMinimumAge {

    String message() default "Employee must be over minimum age.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
