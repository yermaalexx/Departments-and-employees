package com.alexx.employees_and_departments.repositories;

import com.alexx.employees_and_departments.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, UUID> {
}
