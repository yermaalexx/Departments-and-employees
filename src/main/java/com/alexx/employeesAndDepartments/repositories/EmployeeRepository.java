package com.alexx.employeesAndDepartments.repositories;

import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, UUID> {
}
