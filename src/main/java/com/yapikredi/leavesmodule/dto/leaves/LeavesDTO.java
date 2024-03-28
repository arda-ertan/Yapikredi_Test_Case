package com.yapikredi.leavesmodule.dto.leaves;


import java.time.LocalDate;

public class LeavesDTO {
    private Long employee_id;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long leave_status_id;

    private String reason;

    public LeavesDTO() {
    }

    public LeavesDTO(Long employee_id, LocalDate startDate, LocalDate endDate, Long leave_status_id, String reason) {
        this.employee_id = employee_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leave_status_id = leave_status_id;
        this.reason = reason;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getLeave_status_id() {
        return leave_status_id;
    }

    public void setLeave_status_id(Long leave_status_id) {
        this.leave_status_id = leave_status_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
