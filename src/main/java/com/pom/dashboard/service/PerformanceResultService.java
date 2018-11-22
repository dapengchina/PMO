package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.vo.PresultVo;

public interface PerformanceResultService {
	
	
	
	   public PresultVo getPerformance(PerformanceManageEvaBean pmb);
	   
	   public List<PresultVo> getPerformanceList(PerformanceManageEvaBean pmb);
	   
	   public int save(PresultVo pv);

}
