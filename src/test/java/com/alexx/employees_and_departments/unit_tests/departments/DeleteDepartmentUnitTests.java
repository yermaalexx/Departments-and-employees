package com.alexx.employees_and_departments.unit_tests.departments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alexx.employees_and_departments.entities.EmployeeEntity;
import com.alexx.employees_and_departments.exceptions.NoDepartmentWithThisIDException;
import com.alexx.employees_and_departments.repositories.DepartmentRepository;
import com.alexx.employees_and_departments.repositories.EmployeeRepository;
import com.alexx.employees_and_departments.services.DepartmentService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method deleteDepartment(Integer id) from DepartmentService.class")
public class DeleteDepartmentUnitTests {

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private DepartmentService departmentService;

    @Test
    @DisplayName("Normal flow")
    public void deleteDepartmentNormal() {
        UUID id = UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474");
        given(departmentRepository.existsById(id)).willReturn(true);
        EmployeeEntity employeeEntity = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        EmployeeEntity changedEntity = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), null, "Manager");
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        departmentService.deleteDepartment(id);
        verify(departmentRepository).deleteById(id);
        verify(employeeRepository).save(changedEntity);
    }

    @Test
    @DisplayName("No department with this ID")
    public void deleteDepartmentIdIsIncorrect() {
        UUID id = UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474");
        given(departmentRepository.existsById(id)).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> departmentService.deleteDepartment(id));
    }

}
