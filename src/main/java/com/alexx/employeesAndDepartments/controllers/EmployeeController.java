package com.alexx.employeesAndDepartments.controllers;

import com.alexx.employeesAndDepartments.models.EmployeeDTO;
import com.alexx.employeesAndDepartments.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name="Employees", description = "Allows to change employee data")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/employees")
    @Operation(summary = "Add employee", description = "Add new employee, Request body with name of employee required, " +
            "ID is generated automatically, birthDate must be over 18 years ago, idOfDepartment must match the existing department")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
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
                                               @Parameter(description = "ID of the existing employee to remove")
                                               UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/employees/{employeeId}")
    @Operation(summary = "Get employee", description = "Get an existing employee, ID of employee in path required")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("employeeId")
                                                   @Parameter(description = "ID of the existing employee to get")
                                                   UUID id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PatchMapping("/employees/{employeeId}/{newDepartmentId}")
    @Operation(summary = "Set new idOfDepartment", description = "Set new idOfDepartment for existing employee, " +
            "ID's of employee and department in path required, if newDepartmentId is 0-0-0-0-0, then null will be setted")
    public ResponseEntity<EmployeeDTO> editDepartmentOfEmployee(
            @PathVariable("employeeId")
            @Parameter(description = "ID of the existing employee")
            UUID employeeID,
            @PathVariable("newDepartmentId")
            @Parameter(description = "New ID of department, 0-0-0-0-0 to set null")
            UUID departmentID) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.editDepartmentOfEmployee(employeeID, departmentID));
    }

    @GetMapping("/employees/all")
    @Operation(summary = "Get all employees", description = "Get list of all employees")
    public ResponseEntity<List<EmployeeDTO>> getListOfEmployees() {
        return ResponseEntity.ok(employeeService.getListOfEmployees());
    }
}
