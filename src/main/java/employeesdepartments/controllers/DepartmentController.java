package employeesdepartments.controllers;

import employeesdepartments.models.DepartmentDTO;
import employeesdepartments.models.DepartmentListDTO;
import employeesdepartments.models.EmployeeListDTO;
import employeesdepartments.services.DepartmentService;
import org.openapitools.api.DepartmentsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController implements DepartmentsApi {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public ResponseEntity<DepartmentDTO> addDepartment(DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.addDepartment(departmentDTO));
    }

    @Override
    public ResponseEntity<DepartmentDTO> editDepartment(DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.editDepartment(departmentDTO));
    }

    @Override
    public ResponseEntity<Void> deleteDepartment(Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DepartmentDTO> getDepartment(Integer id) {
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }

    @Override
    public ResponseEntity<EmployeeListDTO> getListOfEmployeesForDepartment(Integer id) {
        return ResponseEntity.ok(departmentService.getListOfEmployeesForDepartment(id));
    }

    @Override
    public ResponseEntity<DepartmentListDTO> getListOfDepartments() {
        return ResponseEntity.ok(departmentService.getListOfDepartments());
    }

}
