package com.yapikredi.leavesmodule.service;

import com.yapikredi.leavesmodule.dto.department.DepartmentDTO;
import com.yapikredi.leavesmodule.entity.Department;
import com.yapikredi.leavesmodule.repository.DepartmentRepository;
import com.yapikredi.leavesmodule.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;


    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        return departmentRepository.save(department);
    }

}
