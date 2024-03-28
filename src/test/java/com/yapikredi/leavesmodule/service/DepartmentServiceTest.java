package com.yapikredi.leavesmodule.service;

import com.yapikredi.leavesmodule.dto.department.DepartmentDTO;
import com.yapikredi.leavesmodule.entity.Department;
import com.yapikredi.leavesmodule.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void createDepartment_Success() {
        // Arrange
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Test Department");

        Department department = new Department();
        department.setName(departmentDTO.getName());

        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        // Act
        Department createdDepartment = departmentService.createDepartment(departmentDTO);

        // Assert
        assertNotNull(createdDepartment);
        assertEquals(departmentDTO.getName(), createdDepartment.getName());
    }

    @Test
    void createDepartment_NullName_ReturnsNull() {
        // Arrange
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(null);

        // Act
        Department createdDepartment = departmentService.createDepartment(departmentDTO);

        // Assert
        assertNull(createdDepartment);
    }
}