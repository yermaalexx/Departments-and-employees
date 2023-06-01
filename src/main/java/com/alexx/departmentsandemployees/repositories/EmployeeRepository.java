package com.alexx.departmentsandemployees.repositories;

import com.alexx.departmentsandemployees.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}
