package com.alexx.employees_and_departments.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public class OverMinimumAgeValidator implements ConstraintValidator<OverMinimumAge, LocalDate> {

    @Value("${minimumAgeOfEmployee}")
    long age;

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if(birthDate != null) {
            return birthDate.isBefore(LocalDate.now().minusYears(age));
        }
        return true;
    }
}
