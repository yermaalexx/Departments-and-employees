package com.alexx.employeesAndDepartments.controllers;

import com.alexx.employeesAndDepartments.models.EmployeeDTO;
import com.alexx.employeesAndDepartments.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Employees", description = "Allows to change employee data")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/employees")
    @Operation(summary = "Add employee", description = "Add new employee, Request body with name of employee required, " +
            "ID is generated automatically, birthDate must be over 18 years ago, idOfDepartment must match the existing department")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("");
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employeeDTO));
    }

    @PutMapping("/employees")
    @Operation(summary = "Edit employee", description = "Edit an existing employee, Request body with ID of employee required, " +
            "birthDate must be over 18 years ago, idOfDepartment must match the existing department")
    public ResponseEntity<EmployeeDTO> editEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.editEmployee(employeeDTO));
    }

    @DeleteMapping("/employees/{employeeId}")
    @Operation(summary = "Delete employee", description = "Delete an existing employee, ID of employee in path required")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId")
                                                   @Min(value = 1, message = "ID of employee must be positive.")
                                                   @Parameter(description = "ID of the existing employee to remove")
                                                   Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/employees/{employeeId}")
    @Operation(summary = "Get employee", description = "Get an existing employee, ID of employee in path required")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("employeeId")
                                                       @Min(value = 1, message = "ID of employee must be positive.")
                                                       @Parameter(description = "ID of the existing employee to get")
                                                       Integer id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PatchMapping("/employees/{employeeId}/{newDepartmentId}")
    @Operation(summary = "Set new idOfDepartment", description = "Set new idOfDepartment for existing employee, " +
            "ID's of employee and department in path required, if newDepartmentId = -1, then null will be setted")
    public ResponseEntity<EmployeeDTO> editDepartmentOfEmployee(
            @PathVariable("employeeId")
            @Min(value = 1, message = "ID of employee must be positive.")
            @Parameter(description = "ID of the existing employee")
            Integer employeeID,
            @PathVariable("newDepartmentId")
            @Min(value = -1, message = "ID of department must be positive, or -1 to make it null.")
            @Parameter(description = "New ID of department, -1 to set null")
            Integer departmentID) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.editDepartmentOfEmployee(employeeID, departmentID));
    }

    @GetMapping("/employees/all")
    @Operation(summary = "Get all employees", description = "Get list of all employees")
    public ResponseEntity<List<EmployeeDTO>> getListOfEmployees() {
        return ResponseEntity.ok(employeeService.getListOfEmployees());
    }
}
