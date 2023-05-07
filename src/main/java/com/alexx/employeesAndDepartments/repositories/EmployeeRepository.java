package com.alexx.employeesAndDepartments.repositories;

import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}
