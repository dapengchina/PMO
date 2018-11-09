package com.pmo.dashboard.dao;

import com.pmo.dashboard.entity.Employeeperforgoal;

public interface EmployeeperforgoalMapper {
	
	
	   public Employeeperforgoal selectEmpPerforgoal(Employeeperforgoal epg);
	   
	   public int updateState(Employeeperforgoal epg);
	   
	   public int insert(Employeeperforgoal epg);

}
