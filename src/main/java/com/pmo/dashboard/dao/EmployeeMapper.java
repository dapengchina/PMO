package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;

public interface EmployeeMapper
{
    int addEmployee(Employee employee);
    Employee queryEmployeeById(String employeeId);
    int updateEmployee(Employee employee);
    List<Employee> queryEmployeeList(EmployeePageCondition employeePageCondition);
    List<Employee> queryEmployeeByCsSubDeptId(Map<String, Object> params);
    List<Employee> selectByEhr(String eHr);
    List<Employee> selectByLob(String lob);
    List<Employee> selectByHSBCStaffID(String staffId);
	String getBillRate(Employee employee);
	List<Employee> getAllInterviewer();
	List<Employee> queryAllEmployee();
	List<Employee> getEmployeeByLastUpdateTime(String lastUpdateTime);
	int importEmployeeProject(Employee employee);
	
	int updatePromoteInfo(Employee employee);
	
	int saveForOtherEmployee(Employee employee);
	
	int updateForOtherEmployee(Employee employee);
	
	/**
	 * 绩效目标审批列表
	 * @author: xuexuan
	 * 2018年10月29日 下午5:01:34
	 * @param rmUserId
	 * @param submit
	 * @param backbone
	 * @param state
	 * @return 
	 * List<Map<String,Object>>
	 */
	public List<Map<String,Object>> rmApprovalList(@Param("rmUserId") String rmUserId,@Param("submit") String submit, @Param("backbone") String backbone, @Param("state") String[] state);
}
