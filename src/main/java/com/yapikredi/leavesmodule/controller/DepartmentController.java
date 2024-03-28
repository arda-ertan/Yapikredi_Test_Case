package com.yapikredi.leavesmodule.controller;

import com.yapikredi.leavesmodule.dto.department.DepartmentDTO;
import com.yapikredi.leavesmodule.entity.Department;
import com.yapikredi.leavesmodule.entity.Leaves;
import com.yapikredi.leavesmodule.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/departments")
public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Department Creates", notes = "Department oluşturulur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Başarılı", response = Leaves.class),
            @ApiResponse(code = 404, message = "Bulunamadı")
    })
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department department = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }
}
