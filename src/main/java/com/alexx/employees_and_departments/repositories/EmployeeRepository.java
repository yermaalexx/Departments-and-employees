package com.alexx.employees_and_departments.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alexx.employees_and_departments.entities.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}
