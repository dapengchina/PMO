package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.PerformanceResultMapper;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pom.dashboard.service.PerformanceResultService;

@Service
public class PerformanceResultServiceImpl implements PerformanceResultService{
	
	
	@Resource
	private PerformanceResultMapper performanceResultMapper;

	@Override
	public PresultVo getPerformance(PerformanceManageEvaBean pmb) {
           return performanceResultMapper.queryCurrentYearQuarter(pmb);
	}

	@Override
	public List<PresultVo> getPerformanceList(PerformanceManageEvaBean pmb) {
		   return performanceResultMapper.queryPerformanceList(pmb);
	}

}
