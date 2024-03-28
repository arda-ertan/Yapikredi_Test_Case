package com.yapikredi.leavesmodule.controller;

import com.yapikredi.leavesmodule.dto.employee.EmployeeDTO;
import com.yapikredi.leavesmodule.entity.Employee;
import com.yapikredi.leavesmodule.entity.Leaves;
import com.yapikredi.leavesmodule.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Employee Creates", notes = "Yeni giriş yapılan empoyee oluşturulur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Başarılı", response = Leaves.class),
            @ApiResponse(code = 404, message = "Bulunamadı")
    })
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Employee Updates", notes = "Çalışan günceller yapılan empoyee oluşturulur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Başarılı", response = Leaves.class),
            @ApiResponse(code = 404, message = "Bulunamadı")
    })
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}
