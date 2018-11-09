package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.PerformanceMatrixMapper;
import com.pmo.dashboard.entity.Performancematrix;
import com.pom.dashboard.service.PerformanceMatrixService;


@Service
public class PerformanceMatrixServiceImpl implements PerformanceMatrixService{
	
	
	
	@Resource
	private PerformanceMatrixMapper performanceMatrixMapper;

	@Override
	public List<Performancematrix> getBasePerforTemplate(Performancematrix pm) {
		return performanceMatrixMapper.selectBasePerforTemplate(pm);
	}

}
