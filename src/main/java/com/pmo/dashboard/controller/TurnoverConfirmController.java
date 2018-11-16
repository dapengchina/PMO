package com.pmo.dashboard.controller;

import java.util.List;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.EmployeeTurnoverRecord;
import com.pmo.dashboard.entity.OfflineOper;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeTurnoverService;
import com.pom.dashboard.service.UserService;

@Controller
@RequestMapping(value = "/employee")
public class TurnoverConfirmController{
	private static Logger logger = LoggerFactory
            .getLogger(EmployeeController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource
	private EmployeeTurnoverService employeeTurnoverService;
	
	@Resource 
	private EmployeeService employeeService;
	
	@Resource
	private CSDeptService csDeptService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/turnoverConfirm")
	public String turnoverConfirm (final HttpServletRequest request,
            final HttpServletResponse response)
	{
		String employeeId = request.getParameter("employeeId");
        request.setAttribute("employeeId", employeeId);
        return "employee/turnoverConfirm";
	}
	
	@RequestMapping("/queryTurnoverList")
	@ResponseBody
	public String queryTurnoverList(int pageNumber, int pageSize, final HttpServletRequest request,
            final HttpServletResponse response)
        throws JsonProcessingException, UnsupportedEncodingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		String ehr = request.getParameter("eHr");
		String staffId = request.getParameter("staffId");
		String lob = request.getParameter("lob");
		String staffname = request.getParameter("staffName");
		String olddepartment = request.getParameter("oldDepartment");
		String newDepartment = request.getParameter("newDepartment");
		String state = request.getParameter("state");
		List<String>  csSubDeptNames = new ArrayList<String>();  
		List<CSDept> cSDepts = null;
        if(user.getCsdeptId() != null && user.getCsdeptId() != ""){
        	cSDepts= csDeptService.queryCSDeptByIds(user.getCsdeptId().split(","));
        	for (CSDept csDept : cSDepts) {
        		csSubDeptNames.add(csDept.getCsSubDeptName());
			}
        }
		if( "3".equals(user.getUserType()) && newDepartment.length() == 0){
			newDepartment = cSDepts.get(0).getCsSubDeptId();
		}
		EmployeeTurnoverRecord record = new EmployeeTurnoverRecord(ehr, lob, staffId, staffname, olddepartment,newDepartment,null,null,null,state);
		List<EmployeeTurnoverRecord> data = employeeTurnoverService.queryList(record);
		for(int i=0;i<data.size();i++){
			CSDept csdept = csDeptService.queryCSDeptById(data.get(i).getNewdepartment());
			data.get(i).setNewdepartmentName(csdept.getCsSubDeptName());	
			CSDept oldcsdept = csDeptService.queryCSDeptById(data.get(i).getOlddepartment());
			data.get(i).setOlddepartmentName(oldcsdept.getCsSubDeptName());
			User u = userService.queryUserById(data.get(i).getApplicant());
			data.get(i).setApplicantName(u.getNickname());
			if(data.get(i).getApprovalid() != null){
				User ur = userService.queryUserById(data.get(i).getApprovalid());
				data.get(i).setApprovalName(ur.getNickname());				
			}
			if(data.get(i).getNewRM() != null && data.get(i).getNewRM().length() > 0){
				User newRMName = userService.queryUserById(data.get(i).getNewRM());
				data.get(i).setNewRMName(newRMName.getNickname());
			}
			
			if("0".equals(data.get(i).getState())){
				data.get(i).setStateName("Wait Approve");
			}else if("1".equals(data.get(i).getState())){
				data.get(i).setStateName("Approved");
			}else if("2".equals(data.get(i).getState())){
				data.get(i).setStateName("Refused");;
			}
		}
		PageHelper.startPage(pageNumber, pageSize);
		PageInfo<OfflineOper> page = new PageInfo(data);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotal());
		map.put("rows", data);
		map.put("user", user);
		map.put("csSubDeptNames", csSubDeptNames);
		System.err.println(objectMapper.writeValueAsString(map));
		return objectMapper.writeValueAsString(map);
	}
	
	@RequestMapping("/updateList")
	@ResponseBody
	public Boolean updateList(final HttpServletRequest request,
        final HttpServletResponse response){
		String id = request.getParameter("id");
		EmployeeTurnoverRecord employeeTurnover = employeeTurnoverService.queryById(id);
		String employeeid = employeeTurnover.getEmployeeid();
		String newDepartment = employeeTurnover.getNewdepartment();
		String newRM = employeeTurnover.getNewRM();
		String state = request.getParameter("state");
		User user = (User)request.getSession().getAttribute("loginUser");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        Timestamp approvaldate = null;
        try {
        	approvaldate = new Timestamp(sdf.parse(sdf.format(curDate)).getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
        String approvalid = user.getUserId();
		EmployeeTurnoverRecord record = new EmployeeTurnoverRecord();
		record.setId(id);
		record.setState(state);
		record.setApprovaldate(approvaldate);
		record.setApprovalid(approvalid);
		boolean result = employeeTurnoverService.update(record);
		record.getNewdepartment();
		Employee employeeInfo = new Employee();
		employeeInfo.setEmployeeId(employeeid);
		if("1".equals(state) && result){
			employeeInfo.setCsSubDept(newDepartment);
			employeeInfo.setRmUserId(newRM);
			employeeService.updateForOtherEmployee(employeeInfo);
		}
		return result;
	}
}
