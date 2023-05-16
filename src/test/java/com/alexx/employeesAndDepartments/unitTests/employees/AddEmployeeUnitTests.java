package com.alexx.employeesAndDepartments.unitTests.employees;

import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import com.alexx.employeesAndDepartments.models.EmployeeDTO;
import com.alexx.employeesAndDepartments.repositories.DepartmentRepository;
import com.alexx.employeesAndDepartments.repositories.EmployeeRepository;
import com.alexx.employeesAndDepartments.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method addEmployee(EmployeeDTO employeeDTO) from EmployeeService.class")
public class AddEmployeeUnitTests {
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
    public void addEmployeeNormal() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        EmployeeEntity fromRepository = new EmployeeEntity(256, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        EmployeeEntity afterMapper = new EmployeeEntity(null, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        given(departmentRepository.existsById(employeeDTO.getIdOfDepartment())).willReturn(true);
        given(modelMapper.map(employeeDTO, EmployeeEntity.class)).willReturn(afterMapper);
        given(employeeRepository.save(afterMapper)).willReturn(fromRepository);
        EmployeeDTO result = employeeService.addEmployee(employeeDTO);
        assertEquals(new EmployeeDTO(256, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager")
                , result);
    }

    @Test
    @DisplayName("Department with employee's idOfDepartment does not exist")
    public void addEmployeeIdOfDepartmentNotExist() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        EmployeeEntity fromRepository = new EmployeeEntity(256, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), null, "Manager");
        EmployeeEntity afterMapper = new EmployeeEntity(null, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), null, "Manager");
        given(departmentRepository.existsById(employeeDTO.getIdOfDepartment())).willReturn(false);
        given(modelMapper.map(employeeDTO, EmployeeEntity.class)).willReturn(afterMapper);
        given(employeeRepository.save(afterMapper)).willReturn(fromRepository);
        EmployeeDTO result = employeeService.addEmployee(employeeDTO);
        assertEquals(new EmployeeDTO(256, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), null, "Manager")
                , result);
    }

    @Test
    @DisplayName("Name is empty")
    public void addEmployeeNameIsEmpty() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        assertThrows(Exception.class, () -> employeeService.addEmployee(employeeDTO));
    }

    @Test
    @DisplayName("Date of birth less than 18 years ago")
    public void addEmployeeBirthDateIncorrect() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Musk Ilon", LocalDate.of(2015, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        assertThrows(Exception.class, () -> employeeService.addEmployee(employeeDTO));
    }

}