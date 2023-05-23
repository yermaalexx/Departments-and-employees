package com.alexx.employeesAndDepartments.unitTests.departments;

import com.alexx.employeesAndDepartments.entities.DepartmentEntity;
import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import com.alexx.employeesAndDepartments.exceptions.NoDepartmentWithThisIDException;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method editDepartment(DepartmentDTO departmentDTO) from DepartmentService.class")
public class EditDepartmentUnitTests {

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
    public void editDepartmentNormal() {
        DepartmentDTO departmentDTO = new DepartmentDTO("86c6ff27-a386-4479-8cec-cfde91cb9474", "Development department", "Some description", "Location: NYC");
        DepartmentEntity fromRepository = new DepartmentEntity(UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Old department", "Old description", "Location: NYC");
        DepartmentEntity newEntity = new DepartmentEntity(UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Development department", "Some description", "Location: NYC");
        EmployeeDTO employeeDTO = new EmployeeDTO("1bf10eeb-7105-4102-9c50-00d9f880651e", "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), "86c6ff27-a386-4479-8cec-cfde91cb9474", "Manager");
        EmployeeEntity employeeEntity = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        List<EmployeeDTO> employeeDTOList = List.of(employeeDTO);
        given(departmentRepository.existsById(UUID.fromString(departmentDTO.getId()))).willReturn(true);
        given(departmentRepository.findById(UUID.fromString(departmentDTO.getId()))).willReturn(Optional.of(fromRepository));
        given(modelMapper.map(newEntity, DepartmentDTO.class)).willReturn(departmentDTO);
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        given(modelMapper.map(employeeEntity, EmployeeDTO.class)).willReturn(employeeDTO);
        DepartmentDTO endDTO = new DepartmentDTO("86c6ff27-a386-4479-8cec-cfde91cb9474", "Development department", "Some description", "Location: NYC", employeeDTOList);
        DepartmentDTO result = departmentService.editDepartment(departmentDTO);
        assertEquals(endDTO, result);
    }

    @Test
    @DisplayName("No department with this ID")
    public void editDepartmentIdIsIncorrect() {
        DepartmentDTO departmentDTO = new DepartmentDTO("86c6ff27-a386-4479-8cec-cfde91cb9474", "Development department", "Some description", "Location: NYC");
        given(departmentRepository.existsById(UUID.fromString(departmentDTO.getId()))).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> departmentService.editDepartment(departmentDTO));
    }

    @Test
    @DisplayName("ID is empty")
    public void editDepartmentIdIsEmpty() {
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "Development department", "Some description", "Location: NYC");
        assertThrows(Exception.class, () -> departmentService.editDepartment(departmentDTO));
    }


}
