package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.pmo.dashboard.dao.PerformanceEmpPBCMapper;
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceEmpKPOBean;
import com.pmo.dashboard.entity.PerformanceEmpKeyEventBean;
import com.pmo.dashboard.entity.PerformanceEmpPBC2Bean;
import com.pmo.dashboard.entity.PerformanceEmpPBCBean;
import com.pmo.dashboard.entity.PerformanceEmpPBCPlanBean;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pom.dashboard.service.PerformanceEmpPBCService;

@Service
public class PerformanceEmpPBCServiceImpl implements PerformanceEmpPBCService{
	
@Resource public PerformanceEmpPBCMapper performanceEmpPBCMapper;

	public List<PerformanceEmpPBCBean> queryPerformanceEmpPBCList(String employeeid) {
		List<PerformanceEmpPBCBean> list = performanceEmpPBCMapper.queryPerformanceEmpPBCList(employeeid);
		
		
		return list;
	}




	@Override
	public List<PerformanceEmpPBCPlanBean> queryPerformanceEmpPlanList(String employeeid) {
		List<PerformanceEmpPBCPlanBean> list =performanceEmpPBCMapper.queryPerformanceEmpPlanList(employeeid);// TODO Auto-generated method stub
		return list;
	}




	@Override
	public List<PerformanceEmpPBC2Bean> queryPerformanceEmpPBC2List(String employeeid) {
		
		return null;
	}




	@Override
	public List<Employeeperforgoal> queryPerformanceEmpState(String employeeid) {
		List<Employeeperforgoal> list = performanceEmpPBCMapper.queryPerformanceEmpState(employeeid);
		return list;
	}




	@Override
	public List<PerformanceEmpKPOBean> queryPerformanceEmpKPOList(String employeeid) {
		List<PerformanceEmpKPOBean> list = performanceEmpPBCMapper.queryPerformanceEmpKPOList(employeeid);
		return list;
	}




	@Override
	public List<PerformanceEmpKeyEventBean> queryPerformanceEmpEventList(String employeeid) {
		List<PerformanceEmpKeyEventBean> list = performanceEmpPBCMapper.queryPerformanceEmpEventList(employeeid);
		return list;
	}

@Override
	public int deletePerformanceEmpKPO(String employeeid) {
		if (performanceEmpPBCMapper.deletePerformanceEmpKPO(employeeid) > 0) {
			return 1;
		}
		return 2;
	}
	public int deletePerformanceEmpKeyEvent(String employeeid) {
		if (performanceEmpPBCMapper.deletePerformanceEmpKeyEvent(employeeid) > 0) {
			return 1;
		}
		return 2;
	}




	@Override
	public boolean savePerformanceEmpKPO(PerformanceEmpKPOBean performanceEmpKPO) {
		// TODO Auto-generated method stub
		boolean x = performanceEmpPBCMapper.savePerformanceEmpKPO(performanceEmpKPO);
		return x;
	}
	

	@Override
	public boolean savePerformanceEmpKeyEvent(PerformanceEmpKeyEventBean performanceEmpKeyEvent) {
		boolean x = performanceEmpPBCMapper.savePerformanceEmpKeyEvent(performanceEmpKeyEvent);
		return x;
	}




	@Override
	public boolean savePerformanceEmpPlan(PerformanceEmpPBCPlanBean performanceEmpPBCPlanBean) {
		boolean x = performanceEmpPBCMapper.savePerformanceEmpPlan(performanceEmpPBCPlanBean);
		return x;
	}




	@Override
	public int deletePerformanceEmpPlan(String employeeid) {
		if (performanceEmpPBCMapper.deletePerformanceEmpPlan(employeeid) > 0) {
			return 1;
		}
		return 2;
	}




	public boolean savePerformanceEmpProcess(PerformanceEmpProcessBean performanceEmpProcessBean) {
		boolean x = performanceEmpPBCMapper.savePerformanceEmpProcess(performanceEmpProcessBean);
		return x;
			
	}

@Override
	public boolean savePerformanceEmpState(Employeeperforgoal employeeperforgoal) {
		boolean x = performanceEmpPBCMapper.savePerformanceEmpState(employeeperforgoal);
		return x;
	}




	@Override
	public int deletePerformanceEmpState(String employeeid) {
		if (performanceEmpPBCMapper.deletePerformanceEmpState(employeeid) > 0) {
			return 1;
		}
		return 2;
	}










	
	
}