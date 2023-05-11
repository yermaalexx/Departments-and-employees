package com.alexx.employees_and_departments.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alexx.employees_and_departments.entities.DepartmentEntity;

import java.util.UUID;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, UUID> {
}
