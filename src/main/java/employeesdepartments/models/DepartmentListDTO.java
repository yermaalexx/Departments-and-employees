package employeesdepartments.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import employeesdepartments.models.DepartmentDTO;
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
 * DepartmentListDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T17:22:50.332529600+03:00[Europe/Kiev]")
public class DepartmentListDTO {

  @Valid
  private List<@Valid DepartmentDTO> departments;

  public DepartmentListDTO departments(List<@Valid DepartmentDTO> departments) {
    this.departments = departments;
    return this;
  }

  public DepartmentListDTO addDepartmentsItem(DepartmentDTO departmentsItem) {
    if (this.departments == null) {
      this.departments = new ArrayList<>();
    }
    this.departments.add(departmentsItem);
    return this;
  }

  /**
   * Get departments
   * @return departments
  */
  @Valid 
  @Schema(name = "departments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("departments")
  public List<@Valid DepartmentDTO> getDepartments() {
    return departments;
  }

  public void setDepartments(List<@Valid DepartmentDTO> departments) {
    this.departments = departments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DepartmentListDTO departmentListDTO = (DepartmentListDTO) o;
    return Objects.equals(this.departments, departmentListDTO.departments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(departments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DepartmentListDTO {\n");
    sb.append("    departments: ").append(toIndentedString(departments)).append("\n");
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

