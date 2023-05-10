package com.alexx.employees_and_departments.openAPI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Registration of Employees and Departments",
                description = "API for registration of employees and departments",
                version = "1.0.0",
                contact = @Contact(
                        name = "Yermakov Olexii",
                        email = "yermakov80@gmail.com"
                )
        )
)
public class OpenApiConfig {

}
