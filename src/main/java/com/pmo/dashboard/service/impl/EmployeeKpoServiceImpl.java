package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeKpoMapper;
import com.pmo.dashboard.entity.EmployeeKpo;
import com.pom.dashboard.service.EmployeeKpoService;

@Service
public class EmployeeKpoServiceImpl implements EmployeeKpoService{
	
	
	
	@Resource
	private EmployeeKpoMapper employeeKpoMapper;

	@Override
	public List<EmployeeKpo> getEmployeeKpo(EmployeeKpo eo) {
		return employeeKpoMapper.selectEmployeeKpo(eo);
	}

}
