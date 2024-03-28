package com.yapikredi.leavesmodule.dto.department;

public class DepartmentDTO {
    private String name;

    public DepartmentDTO(String name) {
        this.name = name;
    }

    public DepartmentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
