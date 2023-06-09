package com.github.yermaalexx.departmentsandemployees.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String additionalInformation;

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

    public DepartmentEntity() {
    }

    public DepartmentEntity(UUID id, String name, String description, String additionalInformation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentEntity that)) return false;
        return getId().equals(that.getId()) && getName().equals(that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getAdditionalInformation(), that.getAdditionalInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getAdditionalInformation());
    }
}
