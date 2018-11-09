package com.pmo.dashboard.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.ProcessTypeMapper;
import com.pmo.dashboard.entity.ProcessType;
import com.pom.dashboard.service.ProcessTypeService;

@Service
public class ProcessTypeServiceImpl implements ProcessTypeService{
	
	
	@Resource
	private ProcessTypeMapper processTypeMapper;

	@Override
	public ProcessType getProType(ProcessType pt) {
		return processTypeMapper.selectProType(pt);
	}

}
