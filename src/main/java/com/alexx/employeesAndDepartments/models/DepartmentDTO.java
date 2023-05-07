package com.alexx.employeesAndDepartments.models;

import com.alexx.employeesAndDepartments.validation.Marker;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Department description")
public class DepartmentDTO {

  @NotNull(groups = Marker.OnUpdate.class, message = "ID of department must not be empty.")
  @Schema(description = "Automatically generated department ID", example = "2")
  private Integer id;

  @NotBlank(groups = Marker.OnCreate.class, message = "Name of department must not be empty.")
  @Schema(description = "Department name", example = "Development department")
  private String name;

  @Schema(description = "Brief description of the department", example = "Department is responsible for the acquisition of funds")
  private String description;

  @Schema(description = "Some additional information about the department", example = "Location: NYC")
  private String additionalInformation;

  @Hidden
  private List<EmployeeDTO> listOfEmployees;

  public DepartmentDTO() {
  }

  public DepartmentDTO(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public List<EmployeeDTO> getListOfEmployees() {
    return listOfEmployees;
  }

  public void setListOfEmployees(List<EmployeeDTO> listOfEmployees) {
    this.listOfEmployees = listOfEmployees;
  }
}

