package com.github.yermaalexx.departmentsandemployees.services;

import com.github.yermaalexx.departmentsandemployees.entities.DepartmentEntity;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.models.DepartmentDTO;
import com.github.yermaalexx.departmentsandemployees.models.EmployeeDTO;
import com.github.yermaalexx.departmentsandemployees.repositories.DepartmentRepository;
import com.github.yermaalexx.departmentsandemployees.repositories.EmployeeRepository;
import com.github.yermaalexx.departmentsandemployees.validation.Marker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@Slf4j
public class DepartmentService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Validated(Marker.OnCreate.class)
    public DepartmentDTO addDepartment(@Valid DepartmentDTO departmentDTO) {
        departmentDTO.setId(null);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity = departmentRepository.save(departmentEntity);
        departmentDTO.setId(departmentEntity.getId().toString());
        log.info("New department saved with ID = {}.", departmentEntity.getId());
        return departmentDTO;
    }

    @Validated(Marker.OnUpdate.class)
    public DepartmentDTO editDepartment(@Valid DepartmentDTO departmentDTO) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(departmentDTO.getId());
        } catch (IllegalArgumentException exc) {}
        if(uuid==null || !departmentRepository.existsById(uuid))
            throw new NoDepartmentWithThisIDException();
        DepartmentEntity departmentEntity = departmentRepository.findById(uuid).get();
        if(departmentDTO.getName()!=null)
            departmentEntity.setName(departmentDTO.getName());
        if(departmentDTO.getDescription()!=null)
            departmentEntity.setDescription(departmentDTO.getDescription());
        if(departmentDTO.getAdditionalInformation()!=null)
            departmentEntity.setAdditionalInformation(departmentDTO.getAdditionalInformation());
        departmentRepository.save(departmentEntity);
        departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
        List<EmployeeDTO> employeeListDTO = employeeListOfDepartment(uuid);
        log.info("Department with ID = {} edited.", uuid);
        departmentDTO.setListOfEmployees(employeeListDTO);
        return departmentDTO;
    }

    public void deleteDepartment(String id) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exc) {}
        if(uuid==null || !departmentRepository.existsById(uuid))
            throw new NoDepartmentWithThisIDException();
        departmentRepository.deleteById(uuid);
        employeeRepository.findAll().forEach(employeeEntity -> {
            if(employeeEntity.getIdOfDepartment()!=null && employeeEntity.getIdOfDepartment().toString().equals(id)) {
                employeeEntity.setIdOfDepartment(null);
                employeeRepository.save(employeeEntity);
            }
        });
    }

    public DepartmentDTO getDepartment(String id) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exc) {}
        if(uuid==null || !departmentRepository.existsById(uuid))
            throw new NoDepartmentWithThisIDException();
        DepartmentEntity departmentEntity = departmentRepository.findById(uuid).get();
        DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
        departmentDTO.setListOfEmployees(employeeListOfDepartment(uuid));
        log.info("Department with ID = {} received.", id);
        return departmentDTO;
    }

    public List<EmployeeDTO> getListOfEmployeesForDepartment(String departmentID) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(departmentID);
        } catch (IllegalArgumentException exc) {}
        if(uuid==null || !departmentRepository.existsById(uuid))
            throw new NoDepartmentWithThisIDException();
        log.info("List of employees for department with ID = {} received.", departmentID);
        return employeeListOfDepartment(uuid);
    }

    public List<DepartmentDTO> getListOfDepartments() {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentEntity -> {
            DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
            departmentDTO.setListOfEmployees(employeeListOfDepartment(departmentEntity.getId()));
            departmentDTOList.add(departmentDTO);
        });
        log.info("List of all departments received.");
        return departmentDTOList;
    }

    public List<EmployeeDTO> employeeListOfDepartment(UUID departmentID) {
        if(departmentID==null || !departmentRepository.existsById(departmentID))
            throw new NoDepartmentWithThisIDException();
        List<EmployeeDTO> listEmployeeDTO = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeEntity -> {
            if(employeeEntity.getIdOfDepartment()!=null &&
                    employeeEntity.getIdOfDepartment().equals(departmentID))
                listEmployeeDTO.add(modelMapper.map(employeeEntity, EmployeeDTO.class));
        });
        return listEmployeeDTO;
    }

}
