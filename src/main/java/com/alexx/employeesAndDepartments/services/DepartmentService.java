package com.alexx.employeesAndDepartments.services;

import com.alexx.employeesAndDepartments.entities.DepartmentEntity;
import com.alexx.employeesAndDepartments.exceptions.NoDepartmentWithThisIDException;
import com.alexx.employeesAndDepartments.models.DepartmentDTO;
import com.alexx.employeesAndDepartments.models.EmployeeDTO;
import com.alexx.employeesAndDepartments.repositories.DepartmentRepository;
import com.alexx.employeesAndDepartments.repositories.EmployeeRepository;
import com.alexx.employeesAndDepartments.validation.Marker;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Validated
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
        return departmentDTO;
    }

    public List<EmployeeDTO> getListOfEmployeesForDepartment(String departmentID) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(departmentID);
        } catch (IllegalArgumentException exc) {}
        if(uuid==null || !departmentRepository.existsById(uuid))
            throw new NoDepartmentWithThisIDException();
        return employeeListOfDepartment(uuid);
    }

    public List<DepartmentDTO> getListOfDepartments() {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentEntity -> {
            DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
            departmentDTO.setListOfEmployees(employeeListOfDepartment(departmentEntity.getId()));
            departmentDTOList.add(departmentDTO);
        });
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
