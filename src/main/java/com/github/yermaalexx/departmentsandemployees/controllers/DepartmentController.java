package com.github.yermaalexx.departmentsandemployees.controllers;

import com.github.yermaalexx.departmentsandemployees.models.DepartmentDTO;
import com.github.yermaalexx.departmentsandemployees.models.EmployeeDTO;
import com.github.yermaalexx.departmentsandemployees.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Departments", description = "Allows to change department data")
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    @Operation(summary = "Add department", description = "Add new department, Request body with name of department required, ID is generated automatically")
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        log.info("Add department.");
        log.debug("Name: {}.", departmentDTO.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(departmentDTO));
    }

    @PutMapping("/departments")
    @Operation(summary = "Edit department", description = "Edit an existing department, Request body with ID of department required")
    public ResponseEntity<DepartmentDTO> editDepartment(@RequestBody DepartmentDTO departmentDTO) {
        log.info("Edit department.");
        log.debug("ID = {}.", departmentDTO.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(departmentService.editDepartment(departmentDTO));
    }

    @DeleteMapping("/departments/{departmentId}")
    @Operation(summary = "Delete department", description = "Delete an existing department, ID of department in path required")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("departmentId")
                                                 @Parameter(description = "ID of the existing department to remove")
                                                 String id) {
        log.info("Delete department.");
        log.debug("ID = {}.", id);
        departmentService.deleteDepartment(id);
        log.info("Department with ID = {} deleted.", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/departments/{departmentId}")
    @Operation(summary = "Get department", description = "Get an existing department with its list of employees, ID of department in path required")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("departmentId")
                                                       @Parameter(description = "ID of the existing department to get")
                                                       String id) {
        log.info("Get department.");
        log.debug("ID = {}.", id);
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }

    @GetMapping("/departments/{departmentId}/list")
    @Operation(summary = "Get list of employees", description = "Get list of employees in this department, ID of department in path required")
    public ResponseEntity<List<EmployeeDTO>> getListOfEmployeesForDepartment(@PathVariable("departmentId")
                                                                             @Parameter(description = "ID of the existing department to get its list of employees")
                                                                             String id) {
        log.info("Get list of employees for department.");
        log.debug("ID = {}.", id);
        return ResponseEntity.ok(departmentService.getListOfEmployeesForDepartment(id));
    }

    @GetMapping("/departments/all")
    @Operation(summary = "Get all departments", description = "Get all departments with their lists of employees")
    public ResponseEntity<List<DepartmentDTO>> getListOfDepartments() {
        log.info("Get list of all departments.");
        return ResponseEntity.ok(departmentService.getListOfDepartments());
    }

}
