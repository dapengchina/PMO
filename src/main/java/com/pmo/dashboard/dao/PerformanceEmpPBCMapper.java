package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
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
	 
	 /**
	  * 根据id查询绩效目标总表
	 * @author: xuexuan
	 * 2018年10月30日 上午11:29:03
	 * @param id
	 * @return 
	 */
	public Map<String,Object> queryById(String employeeId);
	
	/**
     * 更新绩效目标流程表
    * @author: xuexuan
    * 2018年10月30日 上午11:29:03
    * @return 
    */
	public void updateByEmployeeId(@Param("employeeid") String employeeid, @Param("state") String state, @Param("remark") String remark);
}