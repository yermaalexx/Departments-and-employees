package com.github.yermaalexx.departmentsandemployees.unitTests.departments;

import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.models.EmployeeDTO;
import com.github.yermaalexx.departmentsandemployees.repositories.DepartmentRepository;
import com.github.yermaalexx.departmentsandemployees.repositories.EmployeeRepository;
import com.github.yermaalexx.departmentsandemployees.services.DepartmentService;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method getListOfEmployeesForDepartment(Integer departmentID) from DepartmentService.class")
public class GetListOfEmployeesForDepartmentUnitTests {

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
    public void getListOfEmployeesForDepartmentNormal() {
        UUID id = UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474");
        given(departmentRepository.existsById(id)).willReturn(true);
        EmployeeDTO employeeDTO = new EmployeeDTO("1bf10eeb-7105-4102-9c50-00d9f880651e", "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), "86c6ff27-a386-4479-8cec-cfde91cb9474", "Manager");
        EmployeeEntity employeeEntity = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        List<EmployeeEntity> employeeEntityList = List.of(employeeEntity);
        List<EmployeeDTO> employeeDTOList = List.of(employeeDTO);
        given(employeeRepository.findAll()).willReturn(employeeEntityList);
        given(modelMapper.map(employeeEntity, EmployeeDTO.class)).willReturn(employeeDTO);
        List<EmployeeDTO> result = departmentService.getListOfEmployeesForDepartment(id.toString());
        assertEquals(employeeDTOList, result);
    }

    @Test
    @DisplayName("No department with this ID")
    public void getListOfEmployeesForDepartmentIdIsIncorrect() {
        UUID id = UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474");
        given(departmentRepository.existsById(id)).willReturn(false);
        assertThrows(NoDepartmentWithThisIDException.class, () -> departmentService.getListOfEmployeesForDepartment(id.toString()));
    }

}
