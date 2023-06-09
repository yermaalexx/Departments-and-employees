package com.github.yermaalexx.departmentsandemployees.unittests.departments;

import com.github.yermaalexx.departmentsandemployees.entities.DepartmentEntity;
import com.github.yermaalexx.departmentsandemployees.models.DepartmentDTO;
import com.github.yermaalexx.departmentsandemployees.repositories.DepartmentRepository;
import com.github.yermaalexx.departmentsandemployees.services.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test method addDepartment(DepartmentDTO departmentDTO) from DepartmentService.class")
public class AddDepartmentUnitTests {

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private DepartmentService departmentService;

    @Test
    @DisplayName("Normal flow")
    public void addDepartmentNormal() {
        DepartmentDTO departmentDTO = new DepartmentDTO("86c6ff27-a386-4479-8cec-cfde91cb9474", "Development department", "Some description", "Location: NYC");
        DepartmentEntity fromMapper = new DepartmentEntity(null, "Development department", "Some description", "Location: NYC");
        DepartmentEntity fromRepository = new DepartmentEntity(UUID.fromString("9775f157-f638-494d-a93a-10d61f5ea9a3"), "Development department", "Some description", "Location: NYC");
        DepartmentDTO newDTO = new DepartmentDTO("9775f157-f638-494d-a93a-10d61f5ea9a3", "Development department", "Some description", "Location: NYC");
        given(modelMapper.map(departmentDTO, DepartmentEntity.class)).willReturn(fromMapper);
        given(departmentRepository.save(fromMapper)).willReturn(fromRepository);
        DepartmentDTO result = departmentService.addDepartment(departmentDTO);
        assertEquals(newDTO, result);
    }

    @Test
    @DisplayName("Name is empty")
    public void addDepartmentNameIsEmpty() {
        DepartmentDTO departmentDTO = new DepartmentDTO("86c6ff27-a386-4479-8cec-cfde91cb9474", "", "Some description", "Location: NYC");
        assertThrows(Exception.class, () -> departmentService.addDepartment(departmentDTO));
    }

}
