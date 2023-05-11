package com.alexx.employees_and_departments.unit_tests.employees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.alexx.employees_and_departments.entities.EmployeeEntity;
import com.alexx.employees_and_departments.exceptions.NoEmployeeWithThisIDException;
import com.alexx.employees_and_departments.models.EmployeeDTO;
import com.alexx.employees_and_departments.repositories.EmployeeRepository;
import com.alexx.employees_and_departments.services.EmployeeService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method getEmployee(Integer id) from EmployeeService.class")
public class GetEmployeeUnitTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    @DisplayName("Normal flow")
    public void getEmployeeNormal() {
        UUID id = UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e");
        given(employeeRepository.existsById(id)).willReturn(true);
        EmployeeEntity fromRepository = new EmployeeEntity(UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e"), "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), UUID.fromString("86c6ff27-a386-4479-8cec-cfde91cb9474"), "Manager");
        given(employeeRepository.findById(id)).willReturn(Optional.of(fromRepository));
        employeeService.getEmployee(id);
        verify(modelMapper).map(fromRepository, EmployeeDTO.class);
    }

    @Test
    @DisplayName("No employee with this ID")
    public void getEmployeeIdIsIncorrect() {
        UUID id = UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e");
        given(employeeRepository.existsById(id)).willReturn(false);
        assertThrows(NoEmployeeWithThisIDException.class, () -> employeeService.getEmployee(id));
    }

}
