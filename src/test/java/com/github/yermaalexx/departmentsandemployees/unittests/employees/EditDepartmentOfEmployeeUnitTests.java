package com.github.yermaalexx.departmentsandemployees.unittests.employees;

import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoEmployeeWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.models.EmployeeDTO;
import com.github.yermaalexx.departmentsandemployees.repositories.DepartmentRepository;
import com.github.yermaalexx.departmentsandemployees.repositories.EmployeeRepository;
import com.github.yermaalexx.departmentsandemployees.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
