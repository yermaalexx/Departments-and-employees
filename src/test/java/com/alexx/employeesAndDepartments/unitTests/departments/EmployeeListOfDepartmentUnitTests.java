package com.alexx.employeesAndDepartments.unitTests.departments;

import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import com.alexx.employeesAndDepartments.exceptions.NoDepartmentWithThisIDException;
import com.alexx.employeesAndDepartments.models.EmployeeDTO;
import com.alexx.employeesAndDepartments.repositories.DepartmentRepository;
import com.alexx.employeesAndDepartments.repositories.EmployeeRepository;
import com.alexx.employeesAndDepartments.services.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test support method employeeListOfDepartment() from DepartmentService.class")
public class EmployeeListOfDepartmentUnitTests {

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private DepartmentService departmentService;

    @Test
    @DisplayName("Normal flow")
    public void employeeListOfDepartmentNormal() {
        Integer id = 3;
        given(departmentRepository.existsById(id)).willReturn(true);
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 3, "Manager");
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 3, "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        List<EmployeeDTO> employeeDTOList = List.of(employeeDTO);
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        given(modelMapper.map(employeeEntity, EmployeeDTO.class)).willReturn(employeeDTO);
        List<EmployeeDTO> result = departmentService.employeeListOfDepartment(id);
        assertEquals(employeeDTOList, result);
    }

    @Test
    @DisplayName("No department with this ID")
    public void employeeListOfDepartmentIdIsIncorrect() {
        Integer id = 3;
        given(departmentRepository.existsById(id)).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> departmentService.employeeListOfDepartment(id));
    }

}
