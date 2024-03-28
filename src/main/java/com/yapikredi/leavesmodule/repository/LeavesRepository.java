package com.yapikredi.leavesmodule.repository;

import com.yapikredi.leavesmodule.entity.Calendar;
import com.yapikredi.leavesmodule.entity.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves,Long> {
    @Query("SELECT l FROM Leaves l JOIN Employee e ON l.employee.id = e.id WHERE l.leaveStatus.id = :leaveStatusId AND e.department.id = :departmentId")
    List<Leaves> getLeavesByStatusIdAndDepartmentId(Long leaveStatusId, Long departmentId);

}
