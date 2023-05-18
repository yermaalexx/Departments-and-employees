package com.alexx.employeesAndDepartments.repositories;

import com.alexx.employeesAndDepartments.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, UUID> {
}
