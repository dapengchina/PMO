package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.Promote;
import com.pom.dashboard.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public boolean addEmployee(Employee employee)
    {
        if(employeeMapper.addEmployee(employee)>0){
            return true;
        }
        return false;
    }

    @Override
    public Employee queryEmployeeById(String employeeId)
    {
        Employee employee =  employeeMapper.queryEmployeeById(employeeId);
        return employee;
    }
    
    @Override
    public List<Promote> queryPromoteByEmployeeId(String employeeId)
    {
    	List<Promote> promote =  employeeMapper.queryPromoteByEmployeeId(employeeId);
        return promote;
    }

    @Override
    public boolean updateEmployee(Employee employee)
    {
        if(employeeMapper.updateEmployee(employee)>0){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean updatePromote(Promote promote)
    {
        if(employeeMapper.updatePromote(promote)>0){
            return true;
        }
        return false;
    }
    

    @Override
    public List<Employee> queryEmployeeList(EmployeePageCondition employeePageCondition)
    {
        List<Employee> list = employeeMapper.queryEmployeeList(employeePageCondition);
        return list;
    }

	@Override
	public List<Employee> queryEmployeeByCsSubDeptId(String csSubDeptId) {
		//面试官状态
		String intervierwStatus = "1";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("csSubDeptId", csSubDeptId);
		params.put("intervierwStatus", intervierwStatus);
		List<Employee> list = employeeMapper.queryEmployeeByCsSubDeptId(params);
		return list;
	}

	@Override
	public List<Employee> selectByEhr(String eHr) {
		return employeeMapper.selectByEhr(eHr);
	}

	@Override
	public List<Employee> selectByLob(String lob) {
		return employeeMapper.selectByLob(lob);
	}

	@Override
	public List<Employee> selectByHSBCStaffID(String staffId) {
		return employeeMapper.selectByHSBCStaffID(staffId);
	}
	
	@Override
	public List<Employee> getAllInterviewer() {
		return employeeMapper.getAllInterviewer();
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeMapper.queryAllEmployee();
	}

	@Override
	public List<Employee> getEmployeeByLastUpdateTime(String lastUpdateTime) {
		return employeeMapper.getEmployeeByLastUpdateTime(lastUpdateTime);
	}
	
//	@Override
    public boolean importEmployeeProject(Employee employee)
    {
        if(employeeMapper.importEmployeeProject(employee)>0){
            return true;
        }
        return false;
    }
}
