package com.alexx.departmentsandemployees.services;

import com.alexx.departmentsandemployees.entities.EmployeeEntity;
import com.alexx.departmentsandemployees.exceptions.NoDepartmentWithThisIDException;
import com.alexx.departmentsandemployees.exceptions.NoEmployeeWithThisIDException;
import com.alexx.departmentsandemployees.models.EmployeeDTO;
import com.alexx.departmentsandemployees.repositories.DepartmentRepository;
import com.alexx.departmentsandemployees.repositories.EmployeeRepository;
import com.alexx.departmentsandemployees.validation.Marker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Slf4j
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
        log.info("New employee saved with ID = {}.", employeeEntity.getId());
        employeeDTO.setId(employeeEntity.getId());
        return employeeDTO;
    }

    @Validated(Marker.OnUpdate.class)
    public EmployeeDTO editEmployee(@Valid EmployeeDTO employeeDTO) {
        if(employeeDTO.getId()==null || !employeeRepository.existsById(employeeDTO.getId())) {
            throw new NoEmployeeWithThisIDException();
        }
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
        log.info("Employee with ID = {} edited.", employeeDTO.getId());
        employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
        return employeeDTO;
    }

    public void deleteEmployee(Integer id) {
        if(id==null || !employeeRepository.existsById(id)) {
            throw new NoEmployeeWithThisIDException();
        }
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO getEmployee(Integer id) {
        if(id==null || !employeeRepository.existsById(id)) {
            throw new NoEmployeeWithThisIDException();
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        log.info("Employee with ID = {} received.", id);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO editDepartmentOfEmployee(Integer employeeID, Integer departmentID) {
        if(employeeID==null || !employeeRepository.existsById(employeeID)) {
            throw new NoEmployeeWithThisIDException();
        }
        if(departmentID!=-1 && (departmentID==null || !departmentRepository.existsById(departmentID))) {
            throw new NoDepartmentWithThisIDException();
        }
        if(departmentID==-1)
            departmentID=null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).get();
        employeeEntity.setIdOfDepartment(departmentID);
        employeeRepository.save(employeeEntity);
        log.info("New department ID: {}", departmentID);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getListOfEmployees() {
        List<EmployeeDTO> listOfDTO = new ArrayList<>();
        employeeRepository.findAll().forEach(entity -> {
            listOfDTO.add(modelMapper.map(entity, EmployeeDTO.class));
        });
        log.info("List of all employees received.");
        return listOfDTO;
    }
}
