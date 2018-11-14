package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeTurnoverRecord;

public interface EmployeeTurnoverService{

		boolean insert(EmployeeTurnoverRecord record);

		List<EmployeeTurnoverRecord> queryList(EmployeeTurnoverRecord record);

	    boolean update(EmployeeTurnoverRecord record);
	    
	    EmployeeTurnoverRecord queryById(String id);
	
};