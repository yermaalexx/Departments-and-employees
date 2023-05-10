package com.alexx.employees_and_departments.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alexx.employees_and_departments.entities.ErrorDetails;
import com.alexx.employees_and_departments.exceptions.NoDepartmentWithThisIDException;
import com.alexx.employees_and_departments.exceptions.NoEmployeeWithThisIDException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoEmployeeWithThisIDException.class)
    public ResponseEntity<ErrorDetails> noEmployeeWithThisIDHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("No employee with this ID.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(NoDepartmentWithThisIDException.class)
    public ResponseEntity<ErrorDetails> noDepartmentWithThisIDHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("No department with this ID.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }
}

