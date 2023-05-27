package com.github.yermaalexx.departmentsandemployees.repositories;

import com.github.yermaalexx.departmentsandemployees.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {
}
