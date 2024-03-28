package com.yapikredi.leavesmodule.dto.employee;

import java.time.LocalDate;

public class EmployeeDTO {

    private String name;
    private String surname;
    private String phone;
    private String mail;
    private Long departmentId;
    private String title;
    private LocalDate startDate;
    private int remainingLeaves;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String surname, String phone, String mail, Long departmentId, String title, LocalDate startDate, int remainingLeaves) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
        this.departmentId = departmentId;
        this.title = title;
        this.startDate = startDate;
        this.remainingLeaves = remainingLeaves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getRemainingLeaves() {
        return remainingLeaves;
    }

    public void setRemainingLeaves(int remainingLeaves) {
        this.remainingLeaves = remainingLeaves;
    }
}