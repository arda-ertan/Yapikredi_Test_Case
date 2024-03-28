package com.yapikredi.leavesmodule.service;

import com.yapikredi.leavesmodule.dto.employee.EmployeeDTO;
import com.yapikredi.leavesmodule.constant.AnniversaryLeaveDays;
import com.yapikredi.leavesmodule.entity.Department;
import com.yapikredi.leavesmodule.entity.Employee;
import com.yapikredi.leavesmodule.exception.EmployeeNotFoundException;
import com.yapikredi.leavesmodule.repository.EmployeeRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final MessageSource messageSource;

    public EmployeeService(EmployeeRepository employeeRepository, MessageSource messageSource) {
        this.employeeRepository = employeeRepository;
        this.messageSource = messageSource;
    }


    //employee yaratma servisi ilk defa kayıt olduğu için AnniversaryLeaveDays.ZERO_YEAR.getLeaves() 5gün izin ekleniyor
    @Transactional
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setPhone(employeeDTO.getPhone());
        employee.setMail(employeeDTO.getMail());
        // Set department using departmentId
        Department department = new Department();
        department.setId(employeeDTO.getDepartmentId());
        employee.setDepartment(department);
        employee.setTitle(employeeDTO.getTitle());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setRemainingLeaves(AnniversaryLeaveDays.ZERO_YEAR.getLeaves());
        return employeeRepository.save(employee);
    }

    //employee güncelleme servisi
    @Transactional
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(messageSource.getMessage("employee.not.found",new Object[]{id}, LocaleContextHolder.getLocale())));

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setSurname(employeeDTO.getSurname());
        existingEmployee.setPhone(employeeDTO.getPhone());
        existingEmployee.setMail(employeeDTO.getMail());
        // Set department using departmentId
        Department department = new Department();
        department.setId(employeeDTO.getDepartmentId());
        existingEmployee.setDepartment(department);
        existingEmployee.setTitle(employeeDTO.getTitle());
        existingEmployee.setStartDate(employeeDTO.getStartDate());
        existingEmployee.setRemainingLeaves(employeeDTO.getRemainingLeaves());

        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


}
