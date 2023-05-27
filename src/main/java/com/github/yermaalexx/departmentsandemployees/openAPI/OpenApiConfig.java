<<<<<<<< HEAD:src/main/java/com/alexx/employeesAndDepartments/config/OpenApiConfig.java
package com.alexx.employeesAndDepartments.config;
========
package com.github.yermaalexx.departmentsandemployees.openAPI;
>>>>>>>> master:src/main/java/com/github/yermaalexx/departmentsandemployees/openAPI/OpenApiConfig.java

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
