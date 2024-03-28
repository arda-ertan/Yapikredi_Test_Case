package com.yapikredi.leavesmodule.service.scheduler;

import com.yapikredi.leavesmodule.constant.AnniversaryLeaveDays;
import com.yapikredi.leavesmodule.entity.Employee;
import com.yapikredi.leavesmodule.service.EmployeeService;
import com.yapikredi.leavesmodule.service.LeavesService;
import com.yapikredi.leavesmodule.util.CalculatorUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulerService {
    private final EmployeeService employeeService;
    private final LeavesService leavesService;


    public SchedulerService(EmployeeService employeeService, LeavesService leavesService) {
        this.employeeService = employeeService;
        this.leavesService = leavesService;
    }

    // hergun gece 12'de yıl dönümü olan personele izin haklarını ekler
    @Scheduled(cron = "0 0 0 * * *")
    public void checkAndAddLeave() {
        // Tüm çalışanları al
        List<Employee> employees = employeeService.getAllEmployees();
        LocalDate today = LocalDate.now();

        // Her bir çalışan için yıl donumu geldiyse 1-5 5-10 10+ kuralına göre izin hakkı tanımla
        for (Employee employee : employees) {
            int yearsOfWork = CalculatorUtils.calculateYearsOfWork(employee.getStartDate(), today);

            if (today.getMonth() == employee.getStartDate().getMonth() && today.getDayOfMonth() == employee.getStartDate().getDayOfMonth()) {
               if(yearsOfWork < AnniversaryLeaveDays.FIVE_YEAR.getYear())
               {
                   leavesService.addLeave(employee.getId(), AnniversaryLeaveDays.ONE_YEAR.getLeaves());
               } else if (yearsOfWork < AnniversaryLeaveDays.TEN_YEAR.getYear()) {
                   leavesService.addLeave(employee.getId(), AnniversaryLeaveDays.FIVE_YEAR.getLeaves());
               } else {
                   leavesService.addLeave(employee.getId(), AnniversaryLeaveDays.TEN_YEAR.getLeaves());
               }
            }
        }
    }


}
