package com.pmo.dashboard.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeImpplan;
import com.pmo.dashboard.entity.EmployeeKeyevent;
import com.pmo.dashboard.entity.EmployeeKpo;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.vo.EmployeePerforGoalVo;
import com.pmo.dashboard.util.DateUtils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeImpplanService;
import com.pom.dashboard.service.EmployeeKeyeventService;
import com.pom.dashboard.service.EmployeeKpoService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeperforgoalService;
import com.pom.dashboard.service.PerformanceMatrixService;

@Controller
@RequestMapping(value="/empPerforSelf")
public class EmployeePerforSelfController {
	
	
	
private ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource
	private EmployeeperforgoalService employeeperforgoalService;
	
	@Resource
	private PerformanceMatrixService performanceMatrixService;
	
	@Resource
	private EmployeeKpoService employeeKpoService;
	
	@Resource
	private EmployeeKeyeventService employeeKeyeventService;
	
	@Resource
	private EmployeeImpplanService employeeImpplanService;
	
	@Resource
	private CSDeptService cSDeptService;
	
	@Resource
	private EmployeeService employeeService;
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	
	
	@RequestMapping("/getEmployeePerforSelf")
    @ResponseBody
	public String getEmployeePerforSelf(final HttpServletRequest request, final HttpServletResponse response,Model model) throws JsonProcessingException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		String employeeid = user.getUserId();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("employeeid", employeeid);
		//查询中软部门信息
		CSDept csdept = cSDeptService.queryCSDeptById(user.getCsdeptId());
		//session.setAttribute("department", csdept!=null?csdept.getCsSubDeptName():"");
		map.put("department", csdept!=null?csdept.getCsSubDeptName():"");
		//查询职位信息
		Employee employee = employeeService.queryEmployeeById(employeeid);
		map.put("role", employee!=null?employee.getRole():"");
		//查询考核主管
		Employee employee2 = employeeService.queryEmployeeById(employee.getRmUserId());
		map.put("assessmentSupervisor", employee2!=null?employee2.getStaffName():"");
		
		//查询重点工作表
		EmployeeKpo eo = new EmployeeKpo();
		eo.setEmployeeid(employeeid);//员工ID
		eo.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		eo.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKpo> kpoList = employeeKpoService.getEmployeeKpo(eo);
		
		//查询关键事件表
		EmployeeKeyevent ek = new EmployeeKeyevent();
		ek.setEmployeeid(employeeid);//员工ID
		ek.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		ek.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKeyevent> keyeventList = employeeKeyeventService.getEmployeeKeyEvent(ek);
		
		/**
		 * 重点工作数据和关键事件数据整合
		 */
		List<EmployeePerforGoalVo> data1 = new ArrayList<EmployeePerforGoalVo>();
		if(kpoList!=null && kpoList.size()>0){
			for(int i=0;i<kpoList.size();i++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(kpoList.get(i).getId());
				perforgoal.setIndex(kpoList.get(i).getIndex());
				perforgoal.setKeyaction(kpoList.get(i).getKeyaction());
				perforgoal.setPhasegoal(kpoList.get(i).getPhasegoal());
				perforgoal.setWeightrate(kpoList.get(i).getWeightrate());
				perforgoal.setEmployeeid(kpoList.get(i).getEmployeeid());
				perforgoal.setDescription(kpoList.get(i).getDescription());
				perforgoal.setCreatedate(kpoList.get(i).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.PRIORITY_WORK);//重点工作
				
				data1.add(perforgoal);
			}
		}
		if(keyeventList!=null && keyeventList.size()>0){
			for(int j=0;j<keyeventList.size();j++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(keyeventList.get(j).getId());
				perforgoal.setIndex(keyeventList.get(j).getIndex());
				perforgoal.setKeyaction(keyeventList.get(j).getKeyaction());
				perforgoal.setPhasegoal(keyeventList.get(j).getPhasegoal());
				perforgoal.setWeightrate(keyeventList.get(j).getWeightrate());
				perforgoal.setEmployeeid(keyeventList.get(j).getEmployeeid());
				perforgoal.setDescription(keyeventList.get(j).getDescription());
				perforgoal.setCreatedate(keyeventList.get(j).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.KEY_EVENTS);//关键事件
				
				data1.add(perforgoal);
			}
		}
		
		//查询个人能力提升计划表
		EmployeeImpplan el = new EmployeeImpplan();
		el.setEmployeeid(employeeid);//员工ID
		el.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		el.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeImpplan> planList = employeeImpplanService.getEmployeeImpplan(el);
	    if(planList!=null && planList.size()>0){
	    	for(int k=0;k<planList.size();k++){
	    		if(planList.get(k).getDealine()!=null && !"".equals(planList.get(k).getDealine())){
	    			planList.get(k).setDealineString(sf.format(planList.get(k).getDealine()));
	    		}
		    }
	    }
		
		map.put("data", data1);
		map.put("plan", planList);
		
		return objectMapper.writeValueAsString(map);
	}
	

}
