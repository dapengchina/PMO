package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;

public interface PerformanceResultMapper {

	List<PerformanceManageEvaBean> queryManageEvaSecondQueryDUList(PerformanceQueryCondition condition) ;
}
