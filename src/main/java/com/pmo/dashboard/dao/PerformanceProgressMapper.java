package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.PerformanceEmpProcessBean;

public interface PerformanceProgressMapper {
	
	
	
	
	   public List<PerformanceEmpProcessBean> selectEmpProcess(PerformanceEmpProcessBean pb);

	   public int savePerformanceEmpProcess(PerformanceEmpProcessBean pb);
}
