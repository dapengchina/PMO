package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeImpplan;

public interface EmployeeImpplanMapper {
	
	
	
	
	public List<EmployeeImpplan> selectEmployeeImpplan(EmployeeImpplan ep);
	
	public int insert(EmployeeImpplan ep);
	
	public int delete(EmployeeImpplan ep);

}
