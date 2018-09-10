package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceManageResultHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;

public interface PerformanceManageEvaService {

	public List<PerformanceManageEvaBean> queryManageEvaFirstDetailList(PerformanceQueryCondition condition);
	public List<PerformanceManageEvaBean> queryManageEvaFirstDetailWithAchieveList(PerformanceQueryCondition condition);
	
	public List<PerformanceEmpHistoryBean> queryManageEvaSecondDUList(PerformanceQueryCondition condition);
	public List<PerformanceEmpHistoryBean> queryManageEvaSecondBUList(PerformanceQueryCondition condition);
	
	public List<PerformanceManageEvaBean> queryManageEvaSecondQueryList(PerformanceQueryCondition condition);
	
	public List<PerformanceManageEvaBean> queryManageEvaSecondQueryDUList(PerformanceQueryCondition condition);
	
	public List<PerformanceManageResultHistoryBean> queryManageResultHistoryQueryList(PerformanceQueryCondition condition);
}
