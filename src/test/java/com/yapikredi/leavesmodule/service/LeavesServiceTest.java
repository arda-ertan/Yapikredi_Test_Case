package com.yapikredi.leavesmodule.service;

import com.yapikredi.leavesmodule.dto.leaves.ApproveRequestDto;
import com.yapikredi.leavesmodule.dto.leaves.LeavesDTO;
import com.yapikredi.leavesmodule.entity.Employee;
import com.yapikredi.leavesmodule.entity.LeaveStatus;
import com.yapikredi.leavesmodule.entity.Leaves;
import com.yapikredi.leavesmodule.exception.NotEnoughLeavesException;
import com.yapikredi.leavesmodule.repository.CalendarRepository;
import com.yapikredi.leavesmodule.repository.EmployeeRepository;
import com.yapikredi.leavesmodule.repository.LeaveStatusRepository;
import com.yapikredi.leavesmodule.repository.LeavesRepository;
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

public class LeavesServiceTest {

    @Mock
    private LeavesRepository leaveRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private LeaveStatusRepository leaveStatusRepository;

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private LeavesService leavesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateLeaves_WithEnoughLeaves() {
        LeavesDTO leavesDTO = new LeavesDTO();
        leavesDTO.setEmployee_id(1L);
        leavesDTO.setStartDate(LocalDate.now());
        leavesDTO.setEndDate(LocalDate.now().plusDays(2));
        leavesDTO.setReason("Test reason");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setRemainingLeaves(5); // Enough leaves for the request

        LeaveStatus leaveStatus = new LeaveStatus();
        leaveStatus.setId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(leaveStatusRepository.findById(1L)).thenReturn(Optional.of(leaveStatus));

        leavesService.createLeaves(leavesDTO);

        verify(leaveRepository, times(1)).save(any(Leaves.class));
    }

    @Test
    public void testCreateLeaves_WithNotEnoughLeaves() {
        LeavesDTO leavesDTO = new LeavesDTO();
        leavesDTO.setEmployee_id(1L);
        leavesDTO.setStartDate(LocalDate.now());
        leavesDTO.setEndDate(LocalDate.now().plusDays(10));
        leavesDTO.setReason("Test reason");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setRemainingLeaves(5); // Not enough leaves for the request

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(NotEnoughLeavesException.class, () -> leavesService.createLeaves(leavesDTO));

        verify(leaveRepository, never()).save(any(Leaves.class));
    }

    @Test
    public void testApproveLeaves() {
        ApproveRequestDto approveRequestDto = new ApproveRequestDto();
        approveRequestDto.setLeaveId(1L);
        approveRequestDto.setLeaveStatusId(1L);

        Leaves leave = new Leaves();
        leave.setId(1L);
        leave.setStartDate(LocalDate.now());
        leave.setEndDate(LocalDate.now().plusDays(5));

        LeaveStatus leaveStatus = new LeaveStatus();
        leaveStatus.setId(1L);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setRemainingLeaves(20); // Assume employee has enough leaves

        when(leaveRepository.findById(1L)).thenReturn(Optional.of(leave));
        when(leaveStatusRepository.findById(1L)).thenReturn(Optional.of(leaveStatus));
        when(calendarRepository.findOfficialHolidaysInDateRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(new ArrayList<>());
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        leavesService.approveLeaves(approveRequestDto);

        assertEquals(15, employee.getRemainingLeaves());
    }

    @Test
    public void testGetLeavesByStatusIdAndDepartmentId() {
        List<Leaves> leavesList = new ArrayList<>();
        Leaves leaves = new Leaves();
        leaves.setLeaveStatus(new LeaveStatus());
        leaves.setReason("Test reason");
        leaves.setStartDate(LocalDate.now());
        leaves.setEndDate(LocalDate.now().plusDays(3));
        leavesList.add(leaves);

        when(leaveRepository.getLeavesByStatusIdAndDepartmentId(1L, 1L)).thenReturn(leavesList);

        List<LeavesDTO> leavesDTOList = leavesService.getLeavesByStatusIdAndDepartmentId(1L, 1L);

        assertFalse(leavesDTOList.isEmpty());
        assertEquals(1, leavesDTOList.size());
        assertEquals("Test reason", leavesDTOList.get(0).getReason());
    }
}
