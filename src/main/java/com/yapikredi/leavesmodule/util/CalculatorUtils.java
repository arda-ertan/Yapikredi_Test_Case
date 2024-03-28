package com.yapikredi.leavesmodule.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class CalculatorUtils {
    // İki tarih arasındaki yıl farkını hesaplar
    public static int calculateYearsOfWork(LocalDate startDate, LocalDate endDate) {
        return endDate.getYear() - startDate.getYear();
    }

    //iki tarih arası günleri hesaplar
    public static int calculateDays(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
