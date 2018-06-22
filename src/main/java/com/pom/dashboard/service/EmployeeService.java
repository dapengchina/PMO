package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;

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
}
