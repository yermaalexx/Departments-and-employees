package com.github.yermaalexx.departmentsandemployees.repositories;

import com.github.yermaalexx.departmentsandemployees.entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, UUID> {

}
