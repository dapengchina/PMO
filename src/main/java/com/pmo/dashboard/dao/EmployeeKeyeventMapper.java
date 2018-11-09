package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeKeyevent;

public interface EmployeeKeyeventMapper {
	
	
	
	   public List<EmployeeKeyevent> selectEmployeeKeyEvent(EmployeeKeyevent ek);
	   
	   public int insert(EmployeeKeyevent ek);
	   
	   public int delete(EmployeeKeyevent ek);

}
