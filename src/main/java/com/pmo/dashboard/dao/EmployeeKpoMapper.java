package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeKpo;

public interface EmployeeKpoMapper {
	
	
	
	public List<EmployeeKpo> selectEmployeeKpo(EmployeeKpo eo);
	
	public int insert(EmployeeKpo eo);
	
	public int delete(EmployeeKpo eo);

}
