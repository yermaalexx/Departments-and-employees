package com.alexx.employeesAndDepartments.services;

import com.alexx.employeesAndDepartments.entities.EmployeeEntity;
import com.alexx.employeesAndDepartments.exceptions.NoDepartmentWithThisIDException;
import com.alexx.employeesAndDepartments.exceptions.NoEmployeeWithThisIDException;
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
        if(employeeDTO.getIdOfDepartment()!=null)
            if(!departmentRepository.existsById(employeeDTO.getIdOfDepartment()))
                employeeDTO.setIdOfDepartment(null);
        employeeDTO.setId(null);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity = employeeRepository.save(employeeEntity);
        employeeDTO.setId(employeeEntity.getId());
        return employeeDTO;
    }

    @Validated(Marker.OnUpdate.class)
    public EmployeeDTO editEmployee(@Valid EmployeeDTO employeeDTO) {
        if(employeeDTO.getId()==null || !employeeRepository.existsById(employeeDTO.getId()))
            throw new NoEmployeeWithThisIDException();
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDTO.getId()).get();
        if(employeeDTO.getName()!=null)
            employeeEntity.setName(employeeDTO.getName());
        if(employeeDTO.getBirthDate()!=null)
            employeeEntity.setBirthDate(employeeDTO.getBirthDate());
        if(employeeDTO.getEmploymentDate()!=null)
            employeeEntity.setEmploymentDate(employeeDTO.getEmploymentDate());
        if(employeeDTO.getIdOfDepartment()!=null && departmentRepository.existsById(employeeDTO.getIdOfDepartment()))
            employeeEntity.setIdOfDepartment(employeeDTO.getIdOfDepartment());
        if(employeeDTO.getJobTitle()!=null)
            employeeEntity.setJobTitle(employeeDTO.getJobTitle());
        employeeRepository.save(employeeEntity);
        employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
        return employeeDTO;
    }

    public void deleteEmployee(UUID id) {
        if(id==null || !employeeRepository.existsById(id))
            throw new NoEmployeeWithThisIDException();
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO getEmployee(UUID id) {
        if(id==null || !employeeRepository.existsById(id))
            throw new NoEmployeeWithThisIDException();
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO editDepartmentOfEmployee(UUID employeeID, UUID departmentID) {
        if(employeeID==null || !employeeRepository.existsById(employeeID))
            throw new NoEmployeeWithThisIDException();
        UUID nullValue = UUID.fromString("0-0-0-0-0");
        if(departmentID.compareTo(nullValue)!=0 && (departmentID==null || !departmentRepository.existsById(departmentID)))
            throw new NoDepartmentWithThisIDException();
        if(departmentID.compareTo(nullValue)==0)
            departmentID=null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).get();
        employeeEntity.setIdOfDepartment(departmentID);
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
