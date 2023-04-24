package employeesdepartments.services;

import employeesdepartments.entities.EmployeeEntity;
import employeesdepartments.exceptions.NoDepartmentWithThisIDException;
import employeesdepartments.exceptions.NoEmployeeWithThisIDException;
import employeesdepartments.models.EmployeeDTO;
import employeesdepartments.models.EmployeeListDTO;
import employeesdepartments.repositories.DepartmentRepository;
import employeesdepartments.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        if(employeeDTO.getIdOfDepartment()!=null)
            if(!departmentRepository.existsById(employeeDTO.getIdOfDepartment()))
                employeeDTO.setIdOfDepartment(null);
        employeeDTO.setId(null);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity = employeeRepository.save(employeeEntity);
        employeeDTO.setId(employeeEntity.getId());
        return employeeDTO;
    }

    public EmployeeDTO editEmployee(EmployeeDTO employeeDTO) {
        if(employeeDTO.getId()==null || !employeeRepository.existsById(employeeDTO.getId()))
            throw new NoEmployeeWithThisIDException();
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDTO.getId()).get();
        if(employeeDTO.getName()!=null)
            employeeEntity.setName(employeeDTO.getName());
        if(employeeDTO.getBirthDate()!=null)
            employeeEntity.setBirthDate(employeeDTO.getBirthDate());
        if(employeeDTO.getEmploymentDate()!=null)
            employeeEntity.setEmploymentDate(employeeDTO.getEmploymentDate());
        if(employeeDTO.getIdOfDepartment()!=null)
            employeeEntity.setIdOfDepartment(employeeDTO.getIdOfDepartment());
        if(employeeDTO.getJobTitle()!=null)
            employeeEntity.setJobTitle(employeeDTO.getJobTitle());
        employeeRepository.save(employeeEntity);
        employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
        return employeeDTO;
    }

    public void deleteEmployee(Integer id) {
        if(id==null || !employeeRepository.existsById(id))
            throw new NoEmployeeWithThisIDException();
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO getEmployee(Integer id) {
        if(id==null || !employeeRepository.existsById(id))
            throw new NoEmployeeWithThisIDException();
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO editDepartmentOfEmployee(Integer employeeID, Integer departmentID) {
        if(employeeID==null || !employeeRepository.existsById(employeeID))
            throw new NoEmployeeWithThisIDException();
        if(departmentID==null || !departmentRepository.existsById(departmentID))
            throw new NoDepartmentWithThisIDException();
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeID).get();
        employeeEntity.setIdOfDepartment(departmentID);
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeListDTO getListOfEmployees() {
        List<EmployeeDTO> listOfDTO = new ArrayList<>();
        employeeRepository.findAll().forEach(entity -> {
            listOfDTO.add(modelMapper.map(entity, EmployeeDTO.class));
        });
        EmployeeListDTO employeeListDTO = new EmployeeListDTO();
        employeeListDTO.setEmployees(listOfDTO);
        return employeeListDTO;
    }
}
