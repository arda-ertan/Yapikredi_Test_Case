package com.yapikredi.leavesmodule.service;

import com.yapikredi.leavesmodule.constant.LeaveStatusConstants;
import com.yapikredi.leavesmodule.dto.leaves.ApproveRequestDto;
import com.yapikredi.leavesmodule.dto.leaves.LeavesDTO;
import com.yapikredi.leavesmodule.entity.Calendar;
import com.yapikredi.leavesmodule.entity.Employee;
import com.yapikredi.leavesmodule.entity.LeaveStatus;
import com.yapikredi.leavesmodule.entity.Leaves;
import com.yapikredi.leavesmodule.exception.EmployeeNotFoundException;
import com.yapikredi.leavesmodule.exception.LeaveNotFoundException;
import com.yapikredi.leavesmodule.exception.NotEnoughLeavesException;
import com.yapikredi.leavesmodule.exception.StatusNotFoundException;
import com.yapikredi.leavesmodule.repository.CalendarRepository;
import com.yapikredi.leavesmodule.repository.EmployeeRepository;
import com.yapikredi.leavesmodule.repository.LeaveStatusRepository;
import com.yapikredi.leavesmodule.repository.LeavesRepository;
import com.yapikredi.leavesmodule.util.CalculatorUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeavesService {
    private final LeavesRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveStatusRepository leaveStatusRepository;
    private final CalendarRepository calendarRepository;

    private final MessageSource messageSource;

    
    public LeavesService(LeavesRepository leaveRepository, EmployeeRepository employeeRepository, LeaveStatusRepository leaveStatusRepository, CalendarRepository calendarRepository, MessageSource messageSource) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.leaveStatusRepository = leaveStatusRepository;
        this.calendarRepository = calendarRepository;
        this.messageSource = messageSource;
    }


    @Transactional
    public Leaves createLeaves(LeavesDTO leavesDTO) {
        Leaves leaves = new Leaves();
        Employee employee = employeeRepository.findById(leavesDTO.getEmployee_id()).orElseThrow(()->new EmployeeNotFoundException(messageSource.getMessage("employee.not.found",new Object[]{leavesDTO.getEmployee_id()}, LocaleContextHolder.getLocale())));

        int leaveDays = CalculatorUtils.calculateDays(leavesDTO.getStartDate(),leavesDTO.getEndDate());

        if(employee.getRemainingLeaves()>=leaveDays){
            leaves.setEmployee(employee);
            leaves.setReason(leavesDTO.getReason());
            leaves.setStartDate(leavesDTO.getStartDate());
            leaves.setEndDate(leavesDTO.getEndDate());
            leaves.setLeaveStatus(leaveStatusRepository.findById(LeaveStatusConstants.PENDING).get());
        }else {
            throw new NotEnoughLeavesException(messageSource.getMessage("not.enough.leaves",new Object[]{employee.getRemainingLeaves()},LocaleContextHolder.getLocale()));
        }

        return leaveRepository.save(leaves);
    }

    @Transactional
    public LeavesDTO approveLeaves(ApproveRequestDto approveRequestDto) {
        LeavesDTO leavesDTO = new LeavesDTO();
        Leaves leave = leaveRepository.findById(approveRequestDto.getLeaveId()).orElseThrow(()-> new LeaveNotFoundException(messageSource.getMessage("leave.not.found",null,LocaleContextHolder.getLocale())));
        LeaveStatus leaveStatus = leaveStatusRepository.findById(approveRequestDto.getLeaveStatusId()).orElseThrow(()-> new StatusNotFoundException(messageSource.getMessage("status.not.found",null,LocaleContextHolder.getLocale())));

        if(leaveStatus.getId().equals(LeaveStatusConstants.APPROVED))
        {
            //izin tarihleri arasında tatil günü var mı
            List<Calendar> calendar = calendarRepository.findOfficialHolidaysInDateRange(leave.getStartDate(),leave.getEndDate());
            Employee employee = leave.getEmployee();
            int daysBetween = CalculatorUtils.calculateDays(leave.getStartDate(), leave.getEndDate());

            employee.setRemainingLeaves(daysBetween-calendar.size());
            employeeRepository.save(employee);
        }
        leave.setLeaveStatus(leaveStatus);
        leaveRepository.save(leave);

        leavesDTO.setEmployee_id(leave.getEmployee().getId());
        leavesDTO.setLeave_status_id(leave.getLeaveStatus().getId());
        leavesDTO.setEndDate(leave.getEndDate());
        leavesDTO.setStartDate(leave.getStartDate());
        leavesDTO.setReason(leave.getReason());

        return leavesDTO;
    }


    //Scheduler servisinde her gün kullanıcıların yıl dönümleri kontrollerine göre izin ekliyor
    @Transactional
    public void addLeave(Long employeeId, int leaveDays) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(messageSource.getMessage("employee.not.found",new Object[]{employeeId}, LocaleContextHolder.getLocale())));
        employee.setRemainingLeaves(employee.getRemainingLeaves() + leaveDays);
        employeeRepository.save(employee);
    }
    @Transactional
    public List<LeavesDTO> getLeavesByStatusIdAndDepartmentId(Long leaveStatusId, Long departmentId)
    {
        List<LeavesDTO> leavesDTOList = new ArrayList<>();
        leaveRepository.getLeavesByStatusIdAndDepartmentId(leaveStatusId,departmentId)
                .stream()
                .forEach(item->{
            LeavesDTO leavesDTO = new LeavesDTO();

            leavesDTO.setLeave_status_id(item.getLeaveStatus().getId());
            leavesDTO.setReason(item.getReason());
            leavesDTO.setStartDate(item.getStartDate());
            leavesDTO.setEndDate(item.getEndDate());
            leavesDTO.setEmployee_id(item.getEmployee().getId());

            leavesDTOList.add(leavesDTO);
        });
        return leavesDTOList;
    }
}
