package com.pom.dashboard.service;

import com.pmo.dashboard.entity.PerformanceLobApprove;
import com.pmo.dashboard.entity.PerformanceLobApproveProportions;
import com.pmo.dashboard.entity.PerformanceLobDetails;
import com.pmo.dashboard.entity.PerformanceLobQueryCondition;

import java.util.List;

public interface PerformanceLobApproveService {

    int updateState(PerformanceLobQueryCondition performanceLobQueryCondition);
    int updateResultComments(PerformanceLobQueryCondition performanceLobQueryCondition);
    PerformanceLobApprove getPerformanceLobApprove(PerformanceLobQueryCondition performanceLobQueryCondition);
    List<PerformanceLobDetails> getPerformanceLobApproveDetails(PerformanceLobQueryCondition performanceLobQueryCondition);
    List<String> getDuListByBu(PerformanceLobQueryCondition performanceLobQueryCondition);
    List<PerformanceLobDetails> getPerformanceLobApproveReport(PerformanceLobQueryCondition performanceLobQueryCondition);
    PerformanceLobApproveProportions getPerformanceLobReportProportions(PerformanceLobQueryCondition performanceLobQueryCondition);
    int getPerformanceLobApproveDetailsCount(PerformanceLobQueryCondition performanceLobQueryCondition);
}