package com.pmo.dashboard.controller;

import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.EmployeeTurnoverRecord;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeTurnoverService;

@Controller
@RequestMapping(value="/employee")
public class EmployeeTurnoverController {
	private static Logger logger = LoggerFactory
            .getLogger(EmployeeController.class);
	
	@Resource
	EmployeeService employeeService;
	
	@Resource
	CSDeptService csDeptService;
	
	@Resource
	EmployeeTurnoverService employeeTurnoverService;
	
	@RequestMapping("/employeeTurnover")
    public String employeeInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/employeeTurnover";
    }
	
	@RequestMapping("/editTurnover")
	public String editTurnover(final HttpServletRequest request,
	            final HttpServletResponse response)
	    {
			String employeeId = request.getParameter("employeeId");
	        request.setAttribute("employeeId", employeeId);
	        return "employee/editTurnover";
	    }
	
	@RequestMapping("/turnoverRecord")
    public String turnoverRecord(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/employeeTurnoverRecord";
    }
	@RequestMapping("/loadScSubDeptName")
	@ResponseBody
	public List<CSDept> loadScSubDeptName(String csBuName){
		List<CSDept> list = csDeptService.queryCSSubDeptNameByCsBuName(csBuName);
		return list;
	}
	
	@RequestMapping("/addTurnover")
	@ResponseBody
	 public Boolean addTurnover(final HttpServletRequest request,
	            final HttpServletResponse response)
	{
		String id = Utils.getUUID();
		String employeeID = request.getParameter("employeeId");
		Employee employee = employeeService.queryEmployeeById(employeeID);
        String ehr = employee.geteHr();
        String lob = employee.getLob();
        String hsbcStaffId = employee.getHsbcStaffId();
        String staffName = employee.getStaffName();
        String oldDepartMent = employee.getCsSubDept();
        String newdepartment = request.getParameter("newdepartment"); 
        String state = "0";
        User user = (User)request.getSession().getAttribute("loginUser");
        String applicant = user.getUserId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        Timestamp applicationDate = null;
        try {
        	applicationDate = new Timestamp(sdf.parse(sdf.format(curDate)).getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
        EmployeeTurnoverRecord condition = new EmployeeTurnoverRecord(ehr,lob,null,null,oldDepartMent,null,null,null,state);
        EmployeeTurnoverRecord turnover = new EmployeeTurnoverRecord(id, employeeID, ehr, staffName, hsbcStaffId, lob, oldDepartMent, newdepartment,
        		applicationDate,null,applicant,state);
        List<EmployeeTurnoverRecord> list = employeeTurnoverService.queryList(condition);
        if(list.isEmpty()){
        	return employeeTurnoverService.insert(turnover);
        }else{
        	String oldId = list.get(0).getId();
        	EmployeeTurnoverRecord update = new EmployeeTurnoverRecord(oldId,null,null,null,null,null,null,newdepartment,applicationDate,null,applicant,null);
        	return employeeTurnoverService.update(update);
        } 
	}
};