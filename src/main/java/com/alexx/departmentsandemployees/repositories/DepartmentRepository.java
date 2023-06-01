package com.alexx.departmentsandemployees.repositories;

import com.alexx.departmentsandemployees.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {
}
