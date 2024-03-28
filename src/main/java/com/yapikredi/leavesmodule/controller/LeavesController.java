package com.yapikredi.leavesmodule.controller;

import com.yapikredi.leavesmodule.dto.leaves.ApproveRequestDto;
import com.yapikredi.leavesmodule.dto.leaves.LeavesDTO;
import com.yapikredi.leavesmodule.entity.Leaves;
import com.yapikredi.leavesmodule.service.LeavesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/leaves")
public class LeavesController {
    private final LeavesService leaveService;
    
    public LeavesController(LeavesService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Leaves Kaydı Oluşturur", notes = "İzin kaydı oluşturur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Başarılı", response = Leaves.class),
            @ApiResponse(code = 404, message = "Bulunamadı")
    })
    public ResponseEntity<Leaves> createLeaves(@RequestBody LeavesDTO leavesDTO) {
        Leaves createdLeaves = leaveService.createLeaves(leavesDTO);
        return new ResponseEntity<>(createdLeaves, HttpStatus.CREATED);
    }

    @PutMapping("/approve")
    @ApiOperation(value = "Leaves Bilgisinin Onay işlemi", notes = "Manager ilgili kaydın leaves bilgisini onaylar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Başarılı", response = Leaves.class),
            @ApiResponse(code = 404, message = "Bulunamadı")
    })
    public ResponseEntity<LeavesDTO> approveLeaves(@RequestBody ApproveRequestDto approveRequestDto) {
        LeavesDTO approvedLeaves = leaveService.approveLeaves(approveRequestDto);
        if (approvedLeaves != null) {
            return new ResponseEntity<>(approvedLeaves, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{leaveStatusId}/{departmentId}")
    @ApiOperation(value = "Leaves Bilgisini Getir", notes = "LeaveStatusId ve departmanId'sine göre filtre yaparak izinleri getirir.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Başarılı", response = Leaves.class),
            @ApiResponse(code = 404, message = "Bulunamadı")
    })
    public ResponseEntity<List<LeavesDTO>> getLeavesByStatusIdAndDepartmentId(@PathVariable Long leaveStatusId,@PathVariable Long departmentId) {
        List<LeavesDTO> leaves = leaveService.getLeavesByStatusIdAndDepartmentId(leaveStatusId ,departmentId);
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }


}
