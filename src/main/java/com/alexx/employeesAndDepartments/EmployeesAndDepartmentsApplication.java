package com.alexx.employeesAndDepartments;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class EmployeesAndDepartmentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeesAndDepartmentsApplication.class, args);
        log.info("Starting application with {} args", args.length);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
