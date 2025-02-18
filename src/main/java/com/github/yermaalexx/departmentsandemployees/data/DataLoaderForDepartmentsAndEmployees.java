package com.github.yermaalexx.departmentsandemployees.data;

import com.github.yermaalexx.departmentsandemployees.entities.DepartmentEntity;
import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import com.github.yermaalexx.departmentsandemployees.repositories.DepartmentRepository;
import com.github.yermaalexx.departmentsandemployees.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoaderForDepartmentsAndEmployees {

    @Bean
    public CommandLineRunner dataLoader(DepartmentRepository departmentRepository,
                                        EmployeeRepository employeeRepository) {
        return (args -> {
            if(departmentRepository.count()==0 || employeeRepository.count()==0) {
                DepartmentEntity department = departmentRepository.save(
                        new DepartmentEntity(null, "Development department", "Some description", "Location: NYC"));
                employeeRepository.save(new EmployeeEntity(null, "Musk Ilon", LocalDate.of(1999, 3, 17), LocalDate.of(2020, 4, 15), department.getId(), "Manager"));
            }
        });
    }

}
