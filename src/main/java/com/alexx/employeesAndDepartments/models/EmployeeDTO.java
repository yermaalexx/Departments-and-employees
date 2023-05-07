package com.alexx.employeesAndDepartments.models;

import com.alexx.employeesAndDepartments.validation.Marker;
import com.alexx.employeesAndDepartments.validation.OverMinimumAge;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Employee description")
public class EmployeeDTO {

  @NotNull(groups = Marker.OnUpdate.class, message = "ID of employee must not be empty.")
  @Schema(description = "Automatically generated employee ID", example = "7")
  private Integer id;

  @NotBlank(groups = Marker.OnCreate.class, message = "Name of employee must not be empty.")
  @Schema(description = "Employee name", example = "Musk Ilon")
  private String name;

  @OverMinimumAge(groups = {Marker.OnCreate.class, Marker.OnUpdate.class})
  @Schema(description = "Employee birth date", example = "1999-03-17")
  private LocalDate birthDate;

  @Schema(description = "Employment date of the employee", example = "2020-04-15")
  private LocalDate employmentDate;

  @Schema(description = "ID of department of the employee", example = "2")
  private Integer idOfDepartment;

  @Schema(description = "Employee job title", example = "Manager")
  private String jobTitle;

  public EmployeeDTO() { }

  public EmployeeDTO(String name) {
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

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getEmploymentDate() {
    return employmentDate;
  }

  public void setEmploymentDate(LocalDate employmentDate) {
    this.employmentDate = employmentDate;
  }

  public Integer getIdOfDepartment() {
    return idOfDepartment;
  }

  public void setIdOfDepartment(Integer idOfDepartment) {
    this.idOfDepartment = idOfDepartment;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }
}

