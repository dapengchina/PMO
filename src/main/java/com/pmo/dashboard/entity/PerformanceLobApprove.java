package com.pmo.dashboard.entity;

import java.util.List;

public class PerformanceLobApprove {

    private PerformanceLobApproveProportions performanceLobApproveProportions;
    private List<PerformanceLobApprovePerformances> performanceLobApprovePerformances;

    public PerformanceLobApproveProportions getPerformanceLobApproveProportions() {
        return performanceLobApproveProportions;
    }

    public void setPerformanceLobApproveProportions(PerformanceLobApproveProportions performanceLobApproveProportions) {
        this.performanceLobApproveProportions = performanceLobApproveProportions;
    }

    public List<PerformanceLobApprovePerformances> getPerformanceLobApprovePerformances() {
        return performanceLobApprovePerformances;
    }

    public void setPerformanceLobApprovePerformances(List<PerformanceLobApprovePerformances> performanceLobApprovePerformances) {
        this.performanceLobApprovePerformances = performanceLobApprovePerformances;
    }
}
