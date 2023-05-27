package com.github.yermaalexx.departmentsandemployees.controllers.advices;

import com.github.yermaalexx.departmentsandemployees.entities.ErrorDetails;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoEmployeeWithThisIDException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

