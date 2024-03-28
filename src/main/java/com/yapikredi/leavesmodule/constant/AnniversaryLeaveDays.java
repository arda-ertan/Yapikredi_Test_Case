package com.yapikredi.leavesmodule.constant;

public enum AnniversaryLeaveDays {
    ZERO_YEAR(0,5),
    ONE_YEAR (1,15),
    FIVE_YEAR (5,18),
    TEN_YEAR(10,24);
    private final int year;
    private final int leaves;

    AnniversaryLeaveDays(int year, int leaves) {
        this.year = year;
        this.leaves = leaves;
    }

    public int getYear() {
        return year;
    }

    public int getLeaves() {
        return leaves;
    }

}
