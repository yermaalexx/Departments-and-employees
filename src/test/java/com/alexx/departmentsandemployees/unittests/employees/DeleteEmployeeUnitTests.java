package com.alexx.departmentsandemployees.unittests.employees;

import com.alexx.departmentsandemployees.exceptions.NoEmployeeWithThisIDException;
import com.alexx.departmentsandemployees.repositories.EmployeeRepository;
import com.alexx.departmentsandemployees.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        int id = 3;
        given(employeeRepository.existsById(id)).willReturn(true);
        employeeService.deleteEmployee(id);
        verify(employeeRepository).deleteById(id);
    }

    @Test
    @DisplayName("No employee with this ID")
    public void deleteEmployeeIdIsIncorrect() {
        int id = 3;
        given(employeeRepository.existsById(id)).willReturn(false);
        assertThrows(NoEmployeeWithThisIDException.class, () -> employeeService.deleteEmployee(id));
    }

}
