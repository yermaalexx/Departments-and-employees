package com.github.yermaalexx.departmentsandemployees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class DepartmentsAndEmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentsAndEmployeesApplication.class, args);
        log.info("Starting application with {} args: {}", args.length, Arrays.toString(args));
    }

}
