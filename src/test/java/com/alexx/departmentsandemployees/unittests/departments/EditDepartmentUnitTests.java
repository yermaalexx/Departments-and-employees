package com.alexx.departmentsandemployees.unittests.departments;

import com.alexx.departmentsandemployees.entities.DepartmentEntity;
import com.alexx.departmentsandemployees.entities.EmployeeEntity;
import com.alexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.alexx.departmentsandemployees.models.DepartmentDTO;
import com.alexx.departmentsandemployees.models.EmployeeDTO;
import com.alexx.departmentsandemployees.repositories.DepartmentRepository;
import com.alexx.departmentsandemployees.repositories.EmployeeRepository;
import com.alexx.departmentsandemployees.services.DepartmentService;
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
        DepartmentDTO departmentDTO = new DepartmentDTO(1, "Development department", "Some description", "Location: NYC");
        DepartmentEntity fromRepository = new DepartmentEntity(1, "Old department", "Old description", "Location: NYC");
        DepartmentEntity newEntity = new DepartmentEntity(1, "Development department", "Some description", "Location: NYC");
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 1, "Manager");
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 1, "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        List<EmployeeDTO> employeeDTOList = List.of(employeeDTO);
        given(departmentRepository.existsById(departmentDTO.getId())).willReturn(true);
        given(departmentRepository.findById(departmentDTO.getId())).willReturn(Optional.of(fromRepository));
        given(modelMapper.map(newEntity, DepartmentDTO.class)).willReturn(departmentDTO);
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        given(modelMapper.map(employeeEntity, EmployeeDTO.class)).willReturn(employeeDTO);
        DepartmentDTO endDTO = new DepartmentDTO(1, "Development department", "Some description", "Location: NYC", employeeDTOList);
        DepartmentDTO result = departmentService.editDepartment(departmentDTO);
        assertEquals(endDTO, result);
    }

    @Test
    @DisplayName("No department with this ID")
    public void editDepartmentIdIsIncorrect() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1, "Development department", "Some description", "Location: NYC");
        given(departmentRepository.existsById(departmentDTO.getId())).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> departmentService.editDepartment(departmentDTO));
    }

    @Test
    @DisplayName("ID is empty")
    public void editDepartmentIdIsEmpty() {
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "Development department", "Some description", "Location: NYC");
        assertThrows(Exception.class, () -> departmentService.editDepartment(departmentDTO));
    }


}
