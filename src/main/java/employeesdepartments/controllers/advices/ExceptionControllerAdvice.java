package employeesdepartments.controllers.advices;

import employeesdepartments.entities.ErrorDetails;
import employeesdepartments.exceptions.NoDepartmentWithThisIDException;
import employeesdepartments.exceptions.NoEmployeeWithThisIDException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoEmployeeWithThisIDException.class)
    public ResponseEntity<ErrorDetails> noEmployeeWithThisIDHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("No employee with this ID.");
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(NoDepartmentWithThisIDException.class)
    public ResponseEntity<ErrorDetails> noDepartmentWithThisIDHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("No department with this ID.");
        return ResponseEntity.badRequest().body(errorDetails);
    }
}

