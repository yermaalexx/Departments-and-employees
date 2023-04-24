package employeesdepartments.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import employeesdepartments.models.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * EmployeeListDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T17:22:50.332529600+03:00[Europe/Kiev]")
public class EmployeeListDTO {

  @Valid
  private List<@Valid EmployeeDTO> employees;

  public EmployeeListDTO employees(List<@Valid EmployeeDTO> employees) {
    this.employees = employees;
    return this;
  }

  public EmployeeListDTO addEmployeesItem(EmployeeDTO employeesItem) {
    if (this.employees == null) {
      this.employees = new ArrayList<>();
    }
    this.employees.add(employeesItem);
    return this;
  }

  /**
   * Get employees
   * @return employees
  */
  @Valid 
  @Schema(name = "employees", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employees")
  public List<@Valid EmployeeDTO> getEmployees() {
    return employees;
  }

  public void setEmployees(List<@Valid EmployeeDTO> employees) {
    this.employees = employees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployeeListDTO employeeListDTO = (EmployeeListDTO) o;
    return Objects.equals(this.employees, employeeListDTO.employees);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employees);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployeeListDTO {\n");
    sb.append("    employees: ").append(toIndentedString(employees)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

