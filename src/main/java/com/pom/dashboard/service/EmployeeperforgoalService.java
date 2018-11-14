package com.pom.dashboard.service;

import java.text.ParseException;

import org.json.JSONArray;

import com.pmo.dashboard.entity.Employeeperforgoal;

public interface EmployeeperforgoalService {
	
	
	
	   public Employeeperforgoal getEmpPerforgoal(Employeeperforgoal epg);
	   
	   public int saveEmpPerforgoal(String employeeid,JSONArray data1,JSONArray data2,JSONArray data3,String state) throws ParseException;

	   public int update(Employeeperforgoal epg);
}
