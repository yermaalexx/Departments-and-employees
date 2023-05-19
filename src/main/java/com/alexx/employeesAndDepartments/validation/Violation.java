package com.alexx.employeesAndDepartments.validation;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public class Violation {
    private final String fieldName;
    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return fieldName + ": " + message;
    }
}
