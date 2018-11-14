package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;


import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.vo.RmApprovalVo;

public interface EmployeeService
{
	String addEmployee(Employee employee);
    Employee queryEmployeeById(String employeeId);
    boolean updateEmployee(Employee employee);
    List<Employee> queryEmployeeList(EmployeePageCondition employeePageCondition);
    List<Employee> queryEmployeeByCsSubDeptId(String csSubDeptId);
    public List<Employee> selectByEhr(String eHr);
    public List<Employee> selectByLob(String lob);
    public List<Employee> selectByHSBCStaffID(String staffId);
	public List<Employee> getAllInterviewer();
	public List<Employee> getAllEmployee();
	public List<Employee> getEmployeeByLastUpdateTime(String lastUpdateTime);
	boolean importEmployeeProject(Employee employee);
	
	int updatePromoteInfo(Employee employee);
	
	String saveForOtherEmployee(Employee employee);
	
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
	public List<RmApprovalVo> rmApprovalList(RmApprovalVo rv);
}
