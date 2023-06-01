package com.github.yermaalexx.departmentsandemployees;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class DepartmentsAndEmployeesApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentsAndEmployeesApplication.class, args);
        log.info("Starting application with {} args: {}", args.length, Arrays.toString(args));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
