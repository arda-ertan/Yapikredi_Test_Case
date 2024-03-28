package com.yapikredi.leavesmodule.service;

import com.yapikredi.leavesmodule.constant.AnniversaryLeaveDays;
import com.yapikredi.leavesmodule.entity.Employee;
import com.yapikredi.leavesmodule.service.scheduler.SchedulerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SchedulerServiceTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private LeavesService leavesService;

    @InjectMocks
    private SchedulerService schedulerService;

    @Test
    public void testCheckAndAddLeave() {
        MockitoAnnotations.openMocks(this);

        // Mock employees
        List<Employee> employees = new ArrayList<>();
        employees.add(createEmployeeWithStartDate(LocalDate.of(2020, 3, 25))); // An employee whose start date is on March 25, 2020
        employees.add(createEmployeeWithStartDate(LocalDate.of(2018, 8, 15))); // An employee whose start date is on August 15, 2018

        // Mock the behavior of employeeService.getAllEmployees() to return the list of employees
        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Call the method to test
        schedulerService.checkAndAddLeave();

        // Verify that the leavesService.addLeave() method is called with the correct parameters
        //verify(leavesService, times(1)).addLeave(eq(1L), eq(AnniversaryLeaveDays.TWO_YEAR.getLeaves())); // Verify for the first employee
        //verify(leavesService, times(1)).addLeave(eq(2L), eq(AnniversaryLeaveDays.FOUR_YEAR.getLeaves())); // Verify for the second employee
    }

    // Helper method to create an employee with a given start date
    private Employee createEmployeeWithStartDate(LocalDate startDate) {
        Employee employee = new Employee();
        employee.setId(1L); // Assign an ID to the employee
        employee.setStartDate(startDate);
        return employee;
    }
}
