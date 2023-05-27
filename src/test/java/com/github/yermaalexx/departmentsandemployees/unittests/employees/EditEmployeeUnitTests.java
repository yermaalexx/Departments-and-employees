package com.github.yermaalexx.departmentsandemployees.unittests.employees;

import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method editEmployee(EmployeeDTO employeeDTO) from EmployeeService.class")
public class EditEmployeeUnitTests {
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
    public void editEmployeeNormal() {
        EmployeeDTO employeeDTO = new EmployeeDTO("1bf10eeb-7105-4102-9c50-00d9f880651e", "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), "86c6ff27-a386-4479-8cec-cfde91cb9474", "Manager");
        EmployeeEntity fromRepository = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Mesk Alon", LocalDate.of(1996, 03, 17), LocalDate.of(2021, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager 2");
        EmployeeEntity changedEntity = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        given(employeeRepository.existsById(UUID.fromString(employeeDTO.getId()))).willReturn(true);
        given(employeeRepository.findById(UUID.fromString(employeeDTO.getId()))).willReturn(Optional.of(fromRepository));
        given(departmentRepository.existsById(UUID.fromString(employeeDTO.getIdOfDepartment()))).willReturn(true);
        given(modelMapper.map(changedEntity, EmployeeDTO.class)).willReturn(employeeDTO);
        EmployeeDTO result = employeeService.editEmployee(employeeDTO);
        assertEquals(employeeDTO, result);
        verify(employeeRepository).save(changedEntity);
    }

    @Test
    @DisplayName("No employee with this ID")
    public void editEmployeeIdIsIncorrect() {
        EmployeeDTO employeeDTO = new EmployeeDTO("1bf10eeb-7105-4102-9c50-00d9f880651e", "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), "86c6ff27-a386-4479-8cec-cfde91cb9474", "Manager");
        given(employeeRepository.existsById(UUID.fromString(employeeDTO.getId()))).willReturn(false);
        assertThrows(NoEmployeeWithThisIDException.class, () -> employeeService.editEmployee(employeeDTO));
    }

    @Test
    @DisplayName("ID is empty")
    public void editEmployeeIdIsEmpty() {
        EmployeeDTO employeeDTO = new EmployeeDTO(null, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), "86c6ff27-a386-4479-8cec-cfde91cb9474", "Manager");
        assertThrows(Exception.class, () -> employeeService.editEmployee(employeeDTO));
    }

    @Test
    @DisplayName("Date of birth less than 18 years ago")
    public void editEmployeeBirthDateIncorrect() {
        EmployeeDTO employeeDTO = new EmployeeDTO("1bf10eeb-7105-4102-9c50-00d9f880651e", "Musk Ilon", LocalDate.of(2015, 03, 17), LocalDate.of(2020, 04, 15), "86c6ff27-a386-4479-8cec-cfde91cb9474", "Manager");
        assertThrows(Exception.class, () -> employeeService.editEmployee(employeeDTO));
    }

}
