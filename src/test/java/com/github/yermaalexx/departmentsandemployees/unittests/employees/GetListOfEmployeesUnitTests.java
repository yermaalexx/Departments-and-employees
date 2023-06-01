package com.github.yermaalexx.departmentsandemployees.unittests.employees;

import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import com.github.yermaalexx.departmentsandemployees.models.EmployeeDTO;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method getListOfEmployees() from EmployeeService.class")
public class GetListOfEmployeesUnitTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    @DisplayName("Normal flow")
    public void getListOfEmployeesNormal() {
        EmployeeEntity entity1 = new EmployeeEntity(7, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 1, "Manager");
        EmployeeEntity entity2 = new EmployeeEntity(9, "Mesk Alan", LocalDate.of(1998, 07, 11), LocalDate.of(2020, 05, 19), 1, "Manager");
        EmployeeDTO dto1 = new EmployeeDTO(7, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 1, "Manager");
        EmployeeDTO dto2 = new EmployeeDTO(9, "Mesk Alan", LocalDate.of(1998, 07, 11), LocalDate.of(2020, 05, 19), 1, "Manager");
        List<EmployeeEntity> entityList = List.of(entity1, entity2);
        List<EmployeeDTO> dtoList = List.of(dto1, dto2);
        given(employeeRepository.findAll()).willReturn(entityList);
        given(modelMapper.map(entity1, EmployeeDTO.class)).willReturn(dto1);
        given(modelMapper.map(entity2, EmployeeDTO.class)).willReturn(dto2);
        List<EmployeeDTO> result = employeeService.getListOfEmployees();
        assertEquals(result, dtoList);
    }

}
