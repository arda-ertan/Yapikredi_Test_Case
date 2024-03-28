package com.yapikredi.leavesmodule.repository;

import com.yapikredi.leavesmodule.entity.Department;
import com.yapikredi.leavesmodule.entity.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
