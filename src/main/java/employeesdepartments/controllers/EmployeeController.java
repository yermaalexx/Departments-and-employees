package employeesdepartments.controllers;

import employeesdepartments.models.EmployeeDTO;
import employeesdepartments.models.EmployeeListDTO;
import employeesdepartments.services.EmployeeService;
import org.openapitools.api.EmployeesApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController implements EmployeesApi {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
    }

    @Override
    public ResponseEntity<EmployeeDTO> editEmployee(EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.editEmployee(employeeDTO));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(Integer id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @Override
    public ResponseEntity<EmployeeDTO> editDepartmentOfEmployee(Integer employeeID, Integer departmentID) {
        return ResponseEntity.ok(employeeService.editDepartmentOfEmployee(employeeID, departmentID));
    }

    @Override
    public ResponseEntity<EmployeeListDTO> getListOfEmployees() {
        return ResponseEntity.ok(employeeService.getListOfEmployees());
    }
}
