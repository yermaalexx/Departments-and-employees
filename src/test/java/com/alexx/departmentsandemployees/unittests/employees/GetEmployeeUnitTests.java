package com.alexx.departmentsandemployees.unittests.employees;

import com.alexx.departmentsandemployees.entities.EmployeeEntity;
import com.alexx.departmentsandemployees.exceptions.NoEmployeeWithThisIDException;
import com.alexx.departmentsandemployees.models.EmployeeDTO;
import com.alexx.departmentsandemployees.repositories.EmployeeRepository;
import com.alexx.departmentsandemployees.services.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

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
        int id = 3;
        given(employeeRepository.existsById(id)).willReturn(true);
        EmployeeEntity fromRepository = new EmployeeEntity(3, "Musk Ilon", LocalDate.of(1999, 03, 17), LocalDate.of(2020, 04, 15), 2, "Manager");
        given(employeeRepository.findById(id)).willReturn(Optional.of(fromRepository));
        employeeService.getEmployee(id);
        verify(modelMapper).map(fromRepository, EmployeeDTO.class);
    }

    @Test
    @DisplayName("No employee with this ID")
    public void getEmployeeIdIsIncorrect() {
        int id = 3;
        given(employeeRepository.existsById(id)).willReturn(false);
        assertThrows(NoEmployeeWithThisIDException.class, () -> employeeService.getEmployee(id));
    }

}
