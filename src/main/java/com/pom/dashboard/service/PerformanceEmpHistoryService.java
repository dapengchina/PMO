package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;


public interface PerformanceEmpHistoryService {
	public List<PerformanceEmpHistoryBean> queryPerformanceEmpHistoryList(PerformanceQueryCondition condition);
	public List<PerformanceEmpHistoryBean> queryPerformanceEmpCurrentList(PerformanceQueryCondition condition);
	public String queryCurrentLoginUserEhr(PerformanceQueryCondition condition);
}
