package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeImpplanMapper;
import com.pmo.dashboard.entity.EmployeeImpplan;
import com.pom.dashboard.service.EmployeeImpplanService;

@Service
public class EmployeeImpplanServiceImpl implements EmployeeImpplanService{
	
	
	@Resource
	private EmployeeImpplanMapper employeeImpplanMapper;

	@Override
	public List<EmployeeImpplan> getEmployeeImpplan(EmployeeImpplan ep) {
		return employeeImpplanMapper.selectEmployeeImpplan(ep);
	}

}
