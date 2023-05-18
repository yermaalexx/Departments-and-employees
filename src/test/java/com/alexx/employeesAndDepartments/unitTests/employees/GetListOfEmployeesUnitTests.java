package com.alexx.employeesAndDepartments.unitTests.employees;

import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import com.alexx.employeesAndDepartments.models.EmployeeDTO;
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
import java.util.List;
import java.util.UUID;

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
        EmployeeEntity entity1 = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        EmployeeEntity entity2 = new EmployeeEntity(UUID.fromString("c1b8f921-5099-4d5c-b650-d0c249e013b9"), "Mesk Alan", LocalDate.of(1998, 07, 11), LocalDate.of(2020, 05, 19), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        EmployeeDTO dto1 = new EmployeeDTO(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        EmployeeDTO dto2 = new EmployeeDTO(UUID.fromString("c1b8f921-5099-4d5c-b650-d0c249e013b9"), "Mesk Alan", LocalDate.of(1998, 07, 11), LocalDate.of(2020, 05, 19), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        List<EmployeeEntity> entityList = List.of(entity1, entity2);
        List<EmployeeDTO> dtoList = List.of(dto1, dto2);
        given(employeeRepository.findAll()).willReturn(entityList);
        given(modelMapper.map(entity1, EmployeeDTO.class)).willReturn(dto1);
        given(modelMapper.map(entity2, EmployeeDTO.class)).willReturn(dto2);
        List<EmployeeDTO> result = employeeService.getListOfEmployees();
        assertEquals(result, dtoList);
    }

}
