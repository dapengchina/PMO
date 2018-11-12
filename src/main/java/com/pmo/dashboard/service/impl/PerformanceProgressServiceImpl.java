package com.pmo.dashboard.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.PerformanceProgressMapper;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pom.dashboard.service.PerformanceProgressService;

@Service
public class PerformanceProgressServiceImpl implements PerformanceProgressService {
	
	
	@Resource
	private PerformanceProgressMapper performanceProgressMapper;
	
	
	@Override
	public List<PerformanceEmpProcessBean> queryPerformanceProgressList(PerformanceEmpProcessBean pb){
		List<PerformanceEmpProcessBean> processList = performanceProgressMapper.selectEmpProcess(pb);		
		return processList;
	}


	@Override
	public int saveProcess(PerformanceEmpProcessBean pb) {
		return performanceProgressMapper.savePerformanceEmpProcess(pb);
	}


}
