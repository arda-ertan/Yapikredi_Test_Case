package com.yapikredi.leavesmodule.entity;

import com.yapikredi.leavesmodule.entity.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "calendar")
public class Calendar extends BaseEntity {
    @Column(name = "entry_date")
    private LocalDate date;

    @Column(name = "week_day")
    private String weekDay;

    @Column(name = "entry_year")
    private int year;

    @Column(name = "entry_month")
    private int month;

    @Column(name = "entry_day")
    private int day;

    @Column(name = "is_official_holiday")
    private boolean isOfficialHoliday;

    @Column(name = "is_weekend")
    private boolean isWeekend;

    @Column(name = "description")
    private String description;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isOfficialHoliday() {
        return isOfficialHoliday;
    }

    public void setOfficialHoliday(boolean officialHoliday) {
        isOfficialHoliday = officialHoliday;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
