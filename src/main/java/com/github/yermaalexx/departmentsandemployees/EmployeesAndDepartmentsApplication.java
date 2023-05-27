package com.github.yermaalexx.departmentsandemployees;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesAndDepartmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesAndDepartmentsApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
