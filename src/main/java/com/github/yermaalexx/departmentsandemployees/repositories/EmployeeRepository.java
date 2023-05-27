package com.github.yermaalexx.departmentsandemployees.repositories;

import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, UUID> {
}
