package com.pmo.dashboard.entity;

public class PerformanceLobApprovePerformances {

    private String bu;
    private String year;
    private String quarter;
    /**
     * lob 总审批状态
     */
    private String state;

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
