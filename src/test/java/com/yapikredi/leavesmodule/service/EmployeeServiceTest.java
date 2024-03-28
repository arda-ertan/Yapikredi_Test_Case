package com.yapikredi.leavesmodule.service;

import com.yapikredi.leavesmodule.constant.AnniversaryLeaveDays;
import com.yapikredi.leavesmodule.dto.employee.EmployeeDTO;
import com.yapikredi.leavesmodule.entity.Department;
import com.yapikredi.leavesmodule.entity.Employee;
import com.yapikredi.leavesmodule.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John");
        employeeDTO.setSurname("Doe");
        employeeDTO.setPhone("1234567890");
        employeeDTO.setMail("john.doe@example.com");
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setTitle("Software Engineer");
        employeeDTO.setStartDate(LocalDate.now());

        Department department = new Department();
        department.setId(1L);

        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> {
            Employee employee = invocation.getArgument(0);
            employee.setId(1L); // Assigning an ID to the employee
            return employee;
        });

        Employee createdEmployee = employeeService.createEmployee(employeeDTO);

        assertNotNull(createdEmployee);
        assertEquals("John", createdEmployee.getName());
        assertEquals("Doe", createdEmployee.getSurname());
        assertEquals("1234567890", createdEmployee.getPhone());
        assertEquals("john.doe@example.com", createdEmployee.getMail());
        assertEquals(1L, createdEmployee.getDepartment().getId());
        assertEquals("Software Engineer", createdEmployee.getTitle());
        assertEquals(LocalDate.now(), createdEmployee.getStartDate());
        assertEquals(AnniversaryLeaveDays.ZERO_YEAR.getLeaves(), createdEmployee.getRemainingLeaves());
    }

    @Test
    public void testUpdateEmployee() {
        Long employeeId = 1L;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Updated John");
        employeeDTO.setSurname("Updated Doe");
        employeeDTO.setPhone("9876543210");
        employeeDTO.setMail("updated.john.doe@example.com");
        employeeDTO.setDepartmentId(2L);
        employeeDTO.setTitle("Senior Software Engineer");
        employeeDTO.setStartDate(LocalDate.now().minusYears(1));
        employeeDTO.setRemainingLeaves(20);

        Department department = new Department();
        department.setId(2L);

        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setName("John");
        existingEmployee.setSurname("Doe");
        existingEmployee.setPhone("1234567890");
        existingEmployee.setMail("john.doe@example.com");
        existingEmployee.setDepartment(new Department());
        existingEmployee.setTitle("Software Engineer");
        existingEmployee.setStartDate(LocalDate.now());
        existingEmployee.setRemainingLeaves(AnniversaryLeaveDays.ZERO_YEAR.getLeaves());

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeDTO);

        assertNotNull(updatedEmployee);
        assertEquals("Updated John", updatedEmployee.getName());
        assertEquals("Updated Doe", updatedEmployee.getSurname());
        assertEquals("9876543210", updatedEmployee.getPhone());
        assertEquals("updated.john.doe@example.com", updatedEmployee.getMail());
        assertEquals(2L, updatedEmployee.getDepartment().getId());
        assertEquals("Senior Software Engineer", updatedEmployee.getTitle());
        assertEquals(LocalDate.now().minusYears(1), updatedEmployee.getStartDate());
        assertEquals(20, updatedEmployee.getRemainingLeaves());
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee());
        employeeList.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> retrievedEmployees = employeeService.getAllEmployees();

        assertNotNull(retrievedEmployees);
        assertEquals(2, retrievedEmployees.size());
    }
}
