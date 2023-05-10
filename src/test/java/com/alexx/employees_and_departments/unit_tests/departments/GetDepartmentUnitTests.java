package com.alexx.employees_and_departments.unit_tests.departments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.alexx.employees_and_departments.entities.DepartmentEntity;
import com.alexx.employees_and_departments.entities.EmployeeEntity;
import com.alexx.employees_and_departments.exceptions.NoDepartmentWithThisIDException;
import com.alexx.employees_and_departments.models.DepartmentDTO;
import com.alexx.employees_and_departments.models.EmployeeDTO;
import com.alexx.employees_and_departments.repositories.DepartmentRepository;
import com.alexx.employees_and_departments.repositories.EmployeeRepository;
import com.alexx.employees_and_departments.services.DepartmentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method getDepartment(Integer id) from DepartmentService.class")
public class GetDepartmentUnitTests {

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
    public void getDepartmentNormal() {
        Integer id = 3;
        given(departmentRepository.existsById(id)).willReturn(true);
        DepartmentEntity fromRepository = new DepartmentEntity(3, "Development department", "Some description", "Location: NYC");
        DepartmentDTO fromMapper = new DepartmentDTO(3, "Development department", "Some description", "Location: NYC");
        given(departmentRepository.findById(id)).willReturn(Optional.of(fromRepository));
        given(modelMapper.map(fromRepository, DepartmentDTO.class)).willReturn(fromMapper);
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 3, "Manager");
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 3, "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        List<EmployeeDTO> employeeDTOList = List.of(employeeDTO);
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        given(modelMapper.map(employeeEntity, EmployeeDTO.class)).willReturn(employeeDTO);
        DepartmentDTO endDTO = new DepartmentDTO(3, "Development department", "Some description", "Location: NYC", employeeDTOList);
        DepartmentDTO result = departmentService.getDepartment(id);
        assertEquals(endDTO, result);
    }

    @Test
    @DisplayName("No department with this ID")
    public void getDepartmentIdIsIncorrect() {
        Integer id = 3;
        given(departmentRepository.existsById(id)).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> departmentService.getDepartment(id));
    }

}