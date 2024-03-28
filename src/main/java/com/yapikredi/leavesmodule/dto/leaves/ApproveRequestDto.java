package com.yapikredi.leavesmodule.dto.leaves;

public class ApproveRequestDto {
    private Long leaveId;
    private Long leaveStatusId;

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Long getLeaveStatusId() {
        return leaveStatusId;
    }

    public void setLeaveStatusId(Long leaveStatusId) {
        this.leaveStatusId = leaveStatusId;
    }
}
