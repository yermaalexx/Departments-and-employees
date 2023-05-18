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
        departmentDTO.setId(departmentEntity.getId());
        return departmentDTO;
    }

    @Validated(Marker.OnUpdate.class)
    public DepartmentDTO editDepartment(@Valid DepartmentDTO departmentDTO) {
        if(departmentDTO.getId()==null || !departmentRepository.existsById(departmentDTO.getId()))
            throw new NoDepartmentWithThisIDException();
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentDTO.getId()).get();
        if(departmentDTO.getName()!=null)
            departmentEntity.setName(departmentDTO.getName());
        if(departmentDTO.getDescription()!=null)
            departmentEntity.setDescription(departmentDTO.getDescription());
        if(departmentDTO.getAdditionalInformation()!=null)
            departmentEntity.setAdditionalInformation(departmentDTO.getAdditionalInformation());
        departmentRepository.save(departmentEntity);
        departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
        List<EmployeeDTO> employeeListDTO = employeeListOfDepartment(departmentDTO.getId());
        departmentDTO.setListOfEmployees(employeeListDTO);
        return departmentDTO;
    }

    public void deleteDepartment(UUID id) {
        if(id==null || !departmentRepository.existsById(id))
            throw new NoDepartmentWithThisIDException();
        departmentRepository.deleteById(id);
        employeeRepository.findAll().forEach(employeeEntity -> {
            if(employeeEntity.getIdOfDepartment()!=null && employeeEntity.getIdOfDepartment().equals(id)) {
                employeeEntity.setIdOfDepartment(null);
                employeeRepository.save(employeeEntity);
            }
        });
    }

    public DepartmentDTO getDepartment(UUID id) {
        if(id==null || !departmentRepository.existsById(id))
            throw new NoDepartmentWithThisIDException();
        DepartmentEntity departmentEntity = departmentRepository.findById(id).get();
        DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
        departmentDTO.setListOfEmployees(employeeListOfDepartment(id));
        return departmentDTO;
    }

    public List<EmployeeDTO> getListOfEmployeesForDepartment(UUID departmentID) {
        if(departmentID==null || !departmentRepository.existsById(departmentID))
            throw new NoDepartmentWithThisIDException();
        return employeeListOfDepartment(departmentID);
    }

    public List<DepartmentDTO> getListOfDepartments() {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentEntity -> {
            DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
            departmentDTO.setListOfEmployees(employeeListOfDepartment(departmentDTO.getId()));
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
