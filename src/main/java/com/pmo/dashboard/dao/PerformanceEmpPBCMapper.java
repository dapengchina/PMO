package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.PerformanceEmpPBCBean;
import com.pmo.dashboard.entity.PerformanceEmpPBCPlanBean;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceEmpKPOBean;
import com.pmo.dashboard.entity.PerformanceEmpKeyEventBean;
public interface PerformanceEmpPBCMapper{

	 List<PerformanceEmpPBCBean> queryPerformanceEmpPBCList(String employeeid);
	 List<PerformanceEmpPBCPlanBean> queryPerformanceEmpPlanList(String employeeid);
	 List<Employeeperforgoal> queryPerformanceEmpState(String employeeid);
	 List<PerformanceEmpKPOBean>  queryPerformanceEmpKPOList(String employeeid);
	 List<PerformanceEmpKeyEventBean> queryPerformanceEmpEventList(String employeeid);
	 boolean savePerformanceEmpKPO(PerformanceEmpKPOBean performanceEmpKPO);
	 boolean savePerformanceEmpPlan(PerformanceEmpPBCPlanBean performanceEmpPBCPlanBean);
	 boolean savePerformanceEmpKeyEvent(PerformanceEmpKeyEventBean performanceEmpKeyEvent);
	 int deletePerformanceEmpKPO(String employeeid);
	 int deletePerformanceEmpKeyEvent(String employeeid);
	 int deletePerformanceEmpPlan(String employeeid);
	 int deletePerformanceEmpState(String employeeid);
	 boolean savePerformanceEmpState(Employeeperforgoal employeeperforgoal);
	 boolean savePerformanceEmpProcess(PerformanceEmpProcessBean performanceEmpProcessBean);
}