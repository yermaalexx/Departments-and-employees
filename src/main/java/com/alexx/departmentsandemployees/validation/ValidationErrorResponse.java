package com.alexx.departmentsandemployees.validation;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;

@Hidden
public class ValidationErrorResponse {
    private final List<Violation> violations;

    public ValidationErrorResponse(List<Violation> violations) {
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }
}
