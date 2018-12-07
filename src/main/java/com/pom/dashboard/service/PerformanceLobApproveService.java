package com.pom.dashboard.service;

import com.pmo.dashboard.entity.PerformanceLobApprove;
import com.pmo.dashboard.entity.PerformanceLobApproveProportions;
import com.pmo.dashboard.entity.PerformanceLobDetails;
import com.pmo.dashboard.entity.PerformanceLobQueryCondition;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;

import java.util.List;
import java.util.Map;

public interface PerformanceLobApproveService {

    int updateState(PerformanceLobQueryCondition performanceLobQueryCondition);
    int updateResultComments(PerformanceLobQueryCondition performanceLobQueryCondition);
    PerformanceLobApprove getPerformanceLobApprove(PerformanceLobQueryCondition performanceLobQueryCondition);
    List<PerformanceLobDetails> getPerformanceLobApproveDetails(PerformanceLobQueryCondition performanceLobQueryCondition);
    List<String> getDuListByBu(PerformanceLobQueryCondition performanceLobQueryCondition);
    List<PerformanceLobDetails> getPerformanceLobApproveReport(PerformanceLobQueryCondition performanceLobQueryCondition);
    PerformanceLobApproveProportions getPerformanceLobReportProportions(PerformanceLobQueryCondition performanceLobQueryCondition);
    int getPerformanceLobApproveDetailsCount(PerformanceLobQueryCondition performanceLobQueryCondition);
    
    
    
    
    public List<PerformanceManageEvaBean> hrReport();
    
    public List<Map<String, Object>> groupStatByResultFinalize();
    
    public Map<String, Object> percentage(List<Map<String, Object>> list);
}