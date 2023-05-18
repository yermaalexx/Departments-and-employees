package com.alexx.employeesAndDepartments.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private LocalDate birthDate;

    @Column
    private LocalDate employmentDate;

    @Column
    private UUID idOfDepartment;

    @Column
    private String jobTitle;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public UUID getIdOfDepartment() {
        return idOfDepartment;
    }

    public void setIdOfDepartment(UUID idOfDepartment) {
        this.idOfDepartment = idOfDepartment;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public EmployeeEntity() {
    }

    public EmployeeEntity(UUID id, String name, LocalDate birthDate, LocalDate employmentDate, UUID idOfDepartment, String jobTitle) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.employmentDate = employmentDate;
        this.idOfDepartment = idOfDepartment;
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && getName().equals(that.getName()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getEmploymentDate(), that.getEmploymentDate()) && Objects.equals(getIdOfDepartment(), that.getIdOfDepartment()) && Objects.equals(getJobTitle(), that.getJobTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthDate(), getEmploymentDate(), getIdOfDepartment(), getJobTitle());
    }
}
