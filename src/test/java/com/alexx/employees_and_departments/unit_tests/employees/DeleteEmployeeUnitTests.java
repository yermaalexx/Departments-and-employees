package com.alexx.employees_and_departments.unit_tests.employees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alexx.employees_and_departments.exceptions.NoEmployeeWithThisIDException;
import com.alexx.employees_and_departments.repositories.EmployeeRepository;
import com.alexx.employees_and_departments.services.EmployeeService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method deleteEmployee(Integer id) from EmployeeService.class")
public class DeleteEmployeeUnitTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    @DisplayName("Normal flow")
    public void deleteEmployeeNormal() {
        UUID id = UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e");
        given(employeeRepository.existsById(id)).willReturn(true);
        employeeService.deleteEmployee(id);
        verify(employeeRepository).deleteById(id);
    }

    @Test
    @DisplayName("No employee with this ID")
    public void deleteEmployeeIdIsIncorrect() {
        UUID id = UUID.fromString("1bf10eeb-7105-4102-9c50-00d9f880651e");
        given(employeeRepository.existsById(id)).willReturn(false);
        assertThrows(NoEmployeeWithThisIDException.class, () -> employeeService.deleteEmployee(id));
    }

}
