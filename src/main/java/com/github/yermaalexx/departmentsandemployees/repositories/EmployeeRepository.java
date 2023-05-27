package com.github.yermaalexx.departmentsandemployees.repositories;

import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}
