package com.github.yermaalexx.departmentsandemployees.services;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.github.yermaalexx.departmentsandemployees.entities.EmployeeEntity;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.exceptions.NoEmployeeWithThisIDException;
import com.github.yermaalexx.departmentsandemployees.models.EmployeeDTO;
import com.github.yermaalexx.departmentsandemployees.repositories.DepartmentRepository;
import com.github.yermaalexx.departmentsandemployees.repositories.EmployeeRepository;
import com.github.yermaalexx.departmentsandemployees.validation.Marker;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Validated(Marker.OnCreate.class)
    public EmployeeDTO addEmployee(@Valid EmployeeDTO employeeDTO) {
        UUID departmentUUID = null;
        try{
            departmentUUID = UUID.fromString(employeeDTO.getIdOfDepartment());
        } catch (IllegalArgumentException exc) {}
        if(departmentUUID!=null)
            if(!departmentRepository.existsById(departmentUUID))
                departmentUUID=null;
        if(departmentUUID==null)
            employeeDTO.setIdOfDepartment(null);
        employeeDTO.setId(null);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity = employeeRepository.save(employeeEntity);
        employeeDTO.setId(employeeEntity.getId().toString());
        return employeeDTO;
    }

    @Validated(Marker.OnUpdate.class)
    public EmployeeDTO editEmployee(@Valid EmployeeDTO employeeDTO) {
        UUID uuid = null;
        UUID departmentUUID = null;
        try{
            uuid = UUID.fromString(employeeDTO.getId());
            departmentUUID = UUID.fromString(employeeDTO.getIdOfDepartment());
        } catch (IllegalArgumentException exc) {}
        if(departmentUUID==null)
            employeeDTO.setIdOfDepartment(null);
        if(uuid==null || !employeeRepository.existsById(uuid))
            throw new NoEmployeeWithThisIDException();
        EmployeeEntity employeeEntity = employeeRepository.findById(uuid).get();
        if(employeeDTO.getName()!=null)
            employeeEntity.setName(employeeDTO.getName());
        if(employeeDTO.getBirthDate()!=null)
            employeeEntity.setBirthDate(employeeDTO.getBirthDate());
        if(employeeDTO.getEmploymentDate()!=null)
            employeeEntity.setEmploymentDate(employeeDTO.getEmploymentDate());
        if(departmentUUID!=null && departmentRepository.existsById(departmentUUID))
            employeeEntity.setIdOfDepartment(departmentUUID);
        if(employeeDTO.getJobTitle()!=null)
            employeeEntity.setJobTitle(employeeDTO.getJobTitle());
        employeeRepository.save(employeeEntity);
        employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
        return employeeDTO;
    }

    public void deleteEmployee(String id) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exc) {}
        if(uuid==null || !employeeRepository.existsById(uuid))
            throw new NoEmployeeWithThisIDException();
        employeeRepository.deleteById(uuid);
    }

    public EmployeeDTO getEmployee(String id) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exc) {}
        if(uuid==null || !employeeRepository.existsById(uuid))
            throw new NoEmployeeWithThisIDException();
        EmployeeEntity employeeEntity = employeeRepository.findById(uuid).get();
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO editDepartmentOfEmployee(String employeeID, String departmentID) {
        UUID employeeUUID = null;
        UUID departmentUUID = null;
        try{
            employeeUUID = UUID.fromString(employeeID);
            departmentUUID = UUID.fromString(departmentID);
        } catch (IllegalArgumentException exc) {}
        if(employeeUUID==null || !employeeRepository.existsById(employeeUUID))
            throw new NoEmployeeWithThisIDException();
        UUID nullValue = UUID.fromString("0-0-0-0-0");
        if(departmentUUID==null || (!departmentUUID.equals(nullValue) && !departmentRepository.existsById(departmentUUID)))
            throw new NoDepartmentWithThisIDException();
        if(departmentUUID.compareTo(nullValue)==0)
            departmentUUID=null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeUUID).get();
        employeeEntity.setIdOfDepartment(departmentUUID);
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getListOfEmployees() {
        List<EmployeeDTO> listOfDTO = new ArrayList<>();
        employeeRepository.findAll().forEach(entity -> {
            listOfDTO.add(modelMapper.map(entity, EmployeeDTO.class));
        });
        return listOfDTO;
    }
}
