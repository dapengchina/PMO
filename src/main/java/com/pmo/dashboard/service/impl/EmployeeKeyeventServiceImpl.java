package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeKeyeventMapper;
import com.pmo.dashboard.entity.EmployeeKeyevent;
import com.pom.dashboard.service.EmployeeKeyeventService;


@Service
public class EmployeeKeyeventServiceImpl implements EmployeeKeyeventService{
	
	
	@Resource
	private EmployeeKeyeventMapper employeeKeyeventMapper;

	@Override
	public List<EmployeeKeyevent> getEmployeeKeyEvent(EmployeeKeyevent ek) {
		return employeeKeyeventMapper.selectEmployeeKeyEvent(ek);
	}
	
	

}
