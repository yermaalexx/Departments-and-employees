package employeesdepartments.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * EmployeeDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T17:22:50.332529600+03:00[Europe/Kiev]")
public class EmployeeDTO {

  private Integer id;

  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate employmentDate;

  private Integer idOfDepartment;

  private String jobTitle;

  /**
   * Default constructor
   * @deprecated Use {@link EmployeeDTO#EmployeeDTO(String)}
   */
  @Deprecated
  public EmployeeDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public EmployeeDTO(String name) {
    this.name = name;
  }

  public EmployeeDTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "12", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EmployeeDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "Mask Ilon", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EmployeeDTO birthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Get birthDate
   * @return birthDate
  */
  @Valid 
  @Schema(name = "birth_date", example = "Sun Jan 30 02:00:00 EET 2000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("birth_date")
  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public EmployeeDTO employmentDate(LocalDate employmentDate) {
    this.employmentDate = employmentDate;
    return this;
  }

  /**
   * Get employmentDate
   * @return employmentDate
  */
  @Valid 
  @Schema(name = "employment_date", example = "Mon Mar 30 03:00:00 EEST 2020", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("employment_date")
  public LocalDate getEmploymentDate() {
    return employmentDate;
  }

  public void setEmploymentDate(LocalDate employmentDate) {
    this.employmentDate = employmentDate;
  }

  public EmployeeDTO idOfDepartment(Integer idOfDepartment) {
    this.idOfDepartment = idOfDepartment;
    return this;
  }

  /**
   * Get idOfDepartment
   * @return idOfDepartment
  */
  
  @Schema(name = "id_of_department", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id_of_department")
  public Integer getIdOfDepartment() {
    return idOfDepartment;
  }

  public void setIdOfDepartment(Integer idOfDepartment) {
    this.idOfDepartment = idOfDepartment;
  }

  public EmployeeDTO jobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  /**
   * Get jobTitle
   * @return jobTitle
  */
  
  @Schema(name = "job_title", example = "Manager", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("job_title")
  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployeeDTO employeeDTO = (EmployeeDTO) o;
    return Objects.equals(this.id, employeeDTO.id) &&
        Objects.equals(this.name, employeeDTO.name) &&
        Objects.equals(this.birthDate, employeeDTO.birthDate) &&
        Objects.equals(this.employmentDate, employeeDTO.employmentDate) &&
        Objects.equals(this.idOfDepartment, employeeDTO.idOfDepartment) &&
        Objects.equals(this.jobTitle, employeeDTO.jobTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, birthDate, employmentDate, idOfDepartment, jobTitle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployeeDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    employmentDate: ").append(toIndentedString(employmentDate)).append("\n");
    sb.append("    idOfDepartment: ").append(toIndentedString(idOfDepartment)).append("\n");
    sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
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

