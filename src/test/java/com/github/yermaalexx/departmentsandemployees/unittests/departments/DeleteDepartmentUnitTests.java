package com.github.yermaalexx.departmentsandemployees.unittests.departments;

import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.repositories.DepartmentRepository;
import com.github.yermaalexx.departmentsandemployees.repositories.EmployeeRepository;
import com.github.yermaalexx.departmentsandemployees.services.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

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
        Integer id = 3;
        given(departmentRepository.existsById(id)).willReturn(true);
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 3, "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        EmployeeEntity changedEntity = new EmployeeEntity(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), null, "Manager");
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        departmentService.deleteDepartment(id);
        verify(departmentRepository).deleteById(id);
        verify(employeeRepository).save(changedEntity);
    }

    @Test
    @DisplayName("No department with this ID")
    public void deleteDepartmentIdIsIncorrect() {
        Integer id = 3;
        given(departmentRepository.existsById(id)).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> departmentService.deleteDepartment(id));
    }

}
