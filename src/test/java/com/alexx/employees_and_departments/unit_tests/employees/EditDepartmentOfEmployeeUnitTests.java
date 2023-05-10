package com.alexx.employees_and_departments.unit_tests.employees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.alexx.employees_and_departments.entities.EmployeeEntity;
import com.alexx.employees_and_departments.exceptions.NoDepartmentWithThisIDException;
import com.alexx.employees_and_departments.exceptions.NoEmployeeWithThisIDException;
import com.alexx.employees_and_departments.models.EmployeeDTO;
import com.alexx.employees_and_departments.repositories.DepartmentRepository;
import com.alexx.employees_and_departments.repositories.EmployeeRepository;
import com.alexx.employees_and_departments.services.EmployeeService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method editDepartmentOfEmployee(Integer employeeID, Integer departmentID) from EmployeeService.class")
public class EditDepartmentOfEmployeeUnitTests {

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    @DisplayName("Normal flow")
    public void editDepartmentOfEmployeeNormal() {
        Integer employeeID = 7;
        Integer departmentID = 2;
        EmployeeEntity fromRepository = new EmployeeEntity(7, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 1, "Manager");
        EmployeeEntity changedEntity = new EmployeeEntity(7, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        given(employeeRepository.existsById(employeeID)).willReturn(true);
        given(departmentRepository.existsById(departmentID)).willReturn(true);
        given(employeeRepository.findById(employeeID)).willReturn(Optional.of(fromRepository));
        employeeService.editDepartmentOfEmployee(employeeID, departmentID);
        verify(employeeRepository).save(changedEntity);
        verify(modelMapper).map(changedEntity, EmployeeDTO.class);
    }

    @Test
    @DisplayName("No employee with this ID")
    public void editDepartmentOfEmployeeIdIsIncorrect() {
        Integer employeeID = 7;
        Integer departmentID = 2;
        given(employeeRepository.existsById(employeeID)).willReturn(false);
        assertThrows(NoEmployeeWithThisIDException.class, () -> employeeService.editDepartmentOfEmployee(employeeID, departmentID));
    }

    @Test
    @DisplayName("No department with this ID")
    public void editDepartmentOfEmployeeDepartmentIdIsIncorrect() {
        Integer employeeID = 7;
        Integer departmentID = 2;
        given(employeeRepository.existsById(employeeID)).willReturn(true);
        given(departmentRepository.existsById(departmentID)).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> employeeService.editDepartmentOfEmployee(employeeID, departmentID));
    }

    @Test
    @DisplayName("departmentID = -1")
    public void editDepartmentOfEmployeeDepartmentIdIsNegative1() {
        Integer employeeID = 7;
        Integer departmentID = -1;
        EmployeeEntity fromRepository = new EmployeeEntity(7, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 1, "Manager");
        EmployeeEntity changedEntity = new EmployeeEntity(7, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), null, "Manager");
        given(employeeRepository.existsById(employeeID)).willReturn(true);
        given(employeeRepository.findById(employeeID)).willReturn(Optional.of(fromRepository));
        employeeService.editDepartmentOfEmployee(employeeID, departmentID);
        verify(employeeRepository).save(changedEntity);
        verify(modelMapper).map(changedEntity, EmployeeDTO.class);
    }

}
