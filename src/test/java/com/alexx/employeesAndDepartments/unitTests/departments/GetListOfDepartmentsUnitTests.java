package com.alexx.employeesAndDepartments.unitTests.departments;

import com.alexx.employeesAndDepartments.entities.DepartmentEntity;
import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import com.alexx.employeesAndDepartments.models.DepartmentDTO;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method getListOfDepartments() from DepartmentService.class")
public class GetListOfDepartmentsUnitTests {

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
    public void getListOfDepartmentsNormal() {
        DepartmentEntity departmentEntity = new DepartmentEntity(UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Development department", "Some description", "Location: NYC");
        DepartmentDTO departmentDTO = new DepartmentDTO("86c6ff27-a386-4479-8cec-cfde91cb9474", "Development department", "Some description", "Location: NYC");
        List<DepartmentEntity> departmentEntityList = List.of(departmentEntity);
        given(departmentRepository.findAll()).willReturn(departmentEntityList);
        given(modelMapper.map(departmentEntity, DepartmentDTO.class)).willReturn(departmentDTO);
        given(departmentRepository.existsById(UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"))).willReturn(true);
        EmployeeDTO employeeDTO = new EmployeeDTO("1bf10eeb-7105-4102-9c50-00d9f880651e", "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), "86c6ff27-a386-4479-8cec-cfde91cb9474", "Manager");
        EmployeeEntity employeeEntity = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        List<EmployeeDTO> employeeDTOList = List.of(employeeDTO);
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        given(modelMapper.map(employeeEntity, EmployeeDTO.class)).willReturn(employeeDTO);
        DepartmentDTO endDTO = new DepartmentDTO("86c6ff27-a386-4479-8cec-cfde91cb9474", "Development department", "Some description", "Location: NYC", employeeDTOList);
        List<DepartmentDTO> departmentDTOList = List.of(endDTO);
        List<DepartmentDTO> result = departmentService.getListOfDepartments();
        assertEquals(departmentDTOList, result);
    }




}
