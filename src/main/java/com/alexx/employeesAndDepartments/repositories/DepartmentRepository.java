package com.alexx.employeesAndDepartments.repositories;

import com.alexx.employeesAndDepartments.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {
}
