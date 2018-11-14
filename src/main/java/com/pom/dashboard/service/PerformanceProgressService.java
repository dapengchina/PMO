package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.PerformanceEmpProcessBean;


public interface PerformanceProgressService {

	public List<PerformanceEmpProcessBean> queryPerformanceProgressList(PerformanceEmpProcessBean pb);

	public int saveProcess(PerformanceEmpProcessBean pb);
	
	public int updateState(PerformanceEmpProcessBean pb);
}
