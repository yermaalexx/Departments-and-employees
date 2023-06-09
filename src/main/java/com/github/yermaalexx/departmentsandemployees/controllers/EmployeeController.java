package com.github.yermaalexx.departmentsandemployees.controllers;

import com.github.yermaalexx.departmentsandemployees.models.EmployeeDTO;
import com.github.yermaalexx.departmentsandemployees.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        log.info("Add employee.");
        log.debug("Name: {}, birth date: {}.", employeeDTO.getName(), employeeDTO.getBirthDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employeeDTO));
    }

    @PutMapping("/employees")
    @Operation(summary = "Edit employee", description = "Edit an existing employee, Request body with ID of employee required, " +
            "birthDate must be over 18 years ago, idOfDepartment must match the existing department")
    public ResponseEntity<EmployeeDTO> editEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Edit employee.");
        log.debug("ID = {}.", employeeDTO.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.editEmployee(employeeDTO));
    }

    @DeleteMapping("/employees/{employeeId}")
    @Operation(summary = "Delete employee", description = "Delete an existing employee, ID of employee in path required")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId")
                                               @Parameter(description = "ID of the existing employee to remove")
                                               String id) {
        log.info("Delete employee.");
        log.debug("ID = {}.", id);
        employeeService.deleteEmployee(id);
        log.info("Employee with ID = {} deleted.", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/employees/{employeeId}")
    @Operation(summary = "Get employee", description = "Get an existing employee, ID of employee in path required")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("employeeId")
                                                   @Parameter(description = "ID of the existing employee to get")
                                                   String id) {
        log.info("Get employee.");
        log.debug("ID = {}.", id);
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PatchMapping("/employees/{employeeId}/{newDepartmentId}")
    @Operation(summary = "Set new idOfDepartment", description = "Set new idOfDepartment for existing employee, " +
            "ID's of employee and department in path required, if newDepartmentId is 0-0-0-0-0, then null will be setted")
    public ResponseEntity<EmployeeDTO> editDepartmentOfEmployee(
            @PathVariable("employeeId")
            @Parameter(description = "ID of the existing employee")
            String employeeID,
            @PathVariable("newDepartmentId")
            @Parameter(description = "New ID of department, 0-0-0-0-0 to set null")
            String departmentID) {
        log.info("Change ID of department for employee.");
        log.debug("For employee with ID = {} set new ID of department: {}.", employeeID, departmentID);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.editDepartmentOfEmployee(employeeID, departmentID));
    }

    @GetMapping("/employees/all")
    @Operation(summary = "Get all employees", description = "Get list of all employees")
    public ResponseEntity<List<EmployeeDTO>> getListOfEmployees() {
        log.info("Get list of all employees.");
        return ResponseEntity.ok(employeeService.getListOfEmployees());
    }
}
