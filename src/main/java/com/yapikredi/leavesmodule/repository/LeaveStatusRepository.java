package com.yapikredi.leavesmodule.repository;

import com.yapikredi.leavesmodule.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus,Long> {
}
