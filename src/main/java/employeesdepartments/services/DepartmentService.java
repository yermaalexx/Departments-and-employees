package employeesdepartments.services;

import employeesdepartments.entities.DepartmentEntity;
import employeesdepartments.exceptions.NoDepartmentWithThisIDException;
import employeesdepartments.models.DepartmentDTO;
import employeesdepartments.models.DepartmentListDTO;
import employeesdepartments.models.EmployeeDTO;
import employeesdepartments.models.EmployeeListDTO;
import employeesdepartments.repositories.DepartmentRepository;
import employeesdepartments.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        departmentDTO.setId(null);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity = departmentRepository.save(departmentEntity);
        departmentDTO.setId(departmentEntity.getId());
        return departmentDTO;
    }

    public DepartmentDTO editDepartment(DepartmentDTO departmentDTO) {
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
        EmployeeListDTO employeeListDTO = employeeListOfDepartment(departmentDTO.getId());
        departmentDTO.setListOfEmployees(employeeListDTO);
        return departmentDTO;
    }

    public void deleteDepartment(Integer id) {
        if(id==null || !departmentRepository.existsById(id))
            throw new NoDepartmentWithThisIDException();
        departmentRepository.deleteById(id);
        employeeRepository.findAll().forEach(employeeEntity -> {
            if(employeeEntity.getIdOfDepartment().equals(id)) {
                employeeEntity.setIdOfDepartment(null);
                employeeRepository.save(employeeEntity);
            }
        });
    }

    public DepartmentDTO getDepartment(Integer id) {
        if(id==null || !departmentRepository.existsById(id))
            throw new NoDepartmentWithThisIDException();
        DepartmentEntity departmentEntity = departmentRepository.findById(id).get();
        DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
        departmentDTO.setListOfEmployees(employeeListOfDepartment(id));
        return departmentDTO;
    }

    public EmployeeListDTO getListOfEmployeesForDepartment(Integer departmentID) {
        if(departmentID==null || !departmentRepository.existsById(departmentID))
            throw new NoDepartmentWithThisIDException();
        return employeeListOfDepartment(departmentID);
    }

    public DepartmentListDTO getListOfDepartments() {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentEntity -> {
            DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
            departmentDTO.setListOfEmployees(employeeListOfDepartment(departmentDTO.getId()));
            departmentDTOList.add(departmentDTO);
        });
        DepartmentListDTO departmentListDTO = new DepartmentListDTO();
        departmentListDTO.setDepartments(departmentDTOList);
        return departmentListDTO;
    }

    public EmployeeListDTO employeeListOfDepartment(Integer departmentID) {
        if(departmentID==null || !departmentRepository.existsById(departmentID))
            throw new NoDepartmentWithThisIDException();
        List<EmployeeDTO> listEmployeeDTO = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeEntity -> {
            if(employeeEntity.getIdOfDepartment()!=null &&
                    employeeEntity.getIdOfDepartment().equals(departmentID))
                listEmployeeDTO.add(modelMapper.map(employeeEntity, EmployeeDTO.class));
        });
        EmployeeListDTO employeeListDTO = new EmployeeListDTO();
        employeeListDTO.setEmployees(listEmployeeDTO);
        return employeeListDTO;
    }

}
