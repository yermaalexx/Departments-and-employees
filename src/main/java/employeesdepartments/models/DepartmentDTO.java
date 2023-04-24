package employeesdepartments.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import employeesdepartments.models.EmployeeListDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * DepartmentDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-23T17:22:50.332529600+03:00[Europe/Kiev]")
public class DepartmentDTO {

  private Integer id;

  private String name;

  private String description;

  private String additionalInformation;

  private EmployeeListDTO listOfEmployees;

  /**
   * Default constructor
   * @deprecated Use {@link DepartmentDTO#DepartmentDTO(String)}
   */
  @Deprecated
  public DepartmentDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DepartmentDTO(String name) {
    this.name = name;
  }

  public DepartmentDTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DepartmentDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "Development department", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DepartmentDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", example = "Some description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DepartmentDTO additionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
    return this;
  }

  /**
   * Get additionalInformation
   * @return additionalInformation
  */
  
  @Schema(name = "additionalInformation", example = "Location: LA", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additional_information")
  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public DepartmentDTO listOfEmployees(EmployeeListDTO listOfEmployees) {
    this.listOfEmployees = listOfEmployees;
    return this;
  }

  /**
   * Get listOfEmployees
   * @return listOfEmployees
  */
  @Valid 
  @Schema(name = "listOfEmployees", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("listOfEmployees")
  public EmployeeListDTO getListOfEmployees() {
    return listOfEmployees;
  }

  public void setListOfEmployees(EmployeeListDTO listOfEmployees) {
    this.listOfEmployees = listOfEmployees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DepartmentDTO departmentDTO = (DepartmentDTO) o;
    return Objects.equals(this.id, departmentDTO.id) &&
        Objects.equals(this.name, departmentDTO.name) &&
        Objects.equals(this.description, departmentDTO.description) &&
        Objects.equals(this.additionalInformation, departmentDTO.additionalInformation) &&
        Objects.equals(this.listOfEmployees, departmentDTO.listOfEmployees);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, additionalInformation, listOfEmployees);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DepartmentDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    additionalInformation: ").append(toIndentedString(additionalInformation)).append("\n");
    sb.append("    listOfEmployees: ").append(toIndentedString(listOfEmployees)).append("\n");
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

