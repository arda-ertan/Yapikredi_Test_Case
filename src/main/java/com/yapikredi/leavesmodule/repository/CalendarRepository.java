package com.yapikredi.leavesmodule.repository;

import com.yapikredi.leavesmodule.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Long> {
    @Query("SELECT c FROM Calendar c WHERE c.isOfficialHoliday = true")
    List<Calendar> findOfficialHolidays();
    @Query("SELECT c FROM Calendar c WHERE c.isOfficialHoliday = true AND c.date BETWEEN :startDate AND :endDate")
    List<Calendar> findOfficialHolidaysInDateRange(LocalDate startDate, LocalDate endDate);
}
