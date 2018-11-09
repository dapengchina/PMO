package com.pmo.dashboard.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.EmployeeImpplan;
import com.pmo.dashboard.entity.EmployeeKeyevent;
import com.pmo.dashboard.entity.EmployeeKpo;
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.vo.EmployeePerforGoalVo;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pmo.dashboard.util.DateUtils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeImpplanService;
import com.pom.dashboard.service.EmployeeKeyeventService;
import com.pom.dashboard.service.EmployeeKpoService;
import com.pom.dashboard.service.EmployeeperforgoalService;
import com.pom.dashboard.service.PerformanceMatrixService;
import com.pom.dashboard.service.PerformanceResultService;

@Controller
@RequestMapping(value="/performance/result")
public class PerformanceResultController {
	
	
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
	
	private SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private PerformanceResultService performanceResultService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy");
	
	private SimpleDateFormat sf2 = new SimpleDateFormat("MM");
	
	
	/**
	 * Employee-绩效结果-当期绩效数据
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/getCurrentYearQuarter")
	@ResponseBody
    public String getCurrentYearQuarter(final HttpServletRequest request, final HttpServletResponse response) throws JsonProcessingException{
		Map<String,Object> result = new HashMap<String,Object>();
		List<PresultVo> list = new ArrayList<PresultVo>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(user.getUserName());
		pmb.setYear(sf.format(new Date()));
		pmb.setQuarter(String.valueOf(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))));
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(1,500); 
		PresultVo pv = performanceResultService.getPerformance(pmb);
		list.add(pv);
		result.put("total", 1);
		result.put("rows", list);
		return objectMapper.writeValueAsString(result);
	}
	
	/**
	 * Employee-绩效结果-当期绩效详情页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getDetail")
    public String getDetail(final HttpServletRequest request, final HttpServletResponse response){
		return "performance/employee/performanceEmpDetail";
	}
	
	/**
	 * Employee-绩效结果-当期绩效详情页面数据
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/getDetailData")
	@ResponseBody
    public String getDetailData(final HttpServletRequest request, final HttpServletResponse response) throws JsonProcessingException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		String employeeid = user.getUserId();
		Map<String,Object> map = new HashMap<String,Object>();
		//查询中软部门信息
		CSDept csdept = cSDeptService.queryCSDeptById(user.getCsdeptId());
		session.setAttribute("department", csdept!=null?csdept.getCsSubDeptName():"");
		
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
	    			planList.get(k).setDealineString(sf3.format(planList.get(k).getDealine()));
	    		}
		    }
	    }
	    
	    //查询员工绩效总表，获取自评信息
	    Employeeperforgoal epg = new Employeeperforgoal();
	    epg.setEmployeeid(employeeid);
	    epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
	    epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
	    Employeeperforgoal reperfor = employeeperforgoalService.getEmpPerforgoal(epg);
		
		//查询绩效结果表，获取comments
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(user.getUserName());
		pmb.setYear(sf.format(new Date()));
		pmb.setQuarter(String.valueOf(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))));
		PresultVo pv = performanceResultService.getPerformance(pmb);
	    
		map.put("comments", pv!=null?pv.getResult_Comments():"");
		map.put("selfassessment", reperfor!=null?reperfor.getSelfassessment():"");
		map.put("data", data1);
		map.put("plan", planList);
		
		return objectMapper.writeValueAsString(map);
	
	}
	
	/**
	 * Employee-绩效结果-历史绩效数据
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/getPerforEmployeeHistory")
	@ResponseBody
    public String getPerforEmployeeHistory(Integer pageNumber,Integer pageSize,HttpServletRequest request) throws JsonProcessingException{
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(user.getUserName());
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize); 
		List<PresultVo> list = performanceResultService.getPerformanceList(pmb);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		PageInfo<PresultVo> page = new PageInfo(list);
		result.put("total", page.getTotal());
		result.put("rows", list);
		return objectMapper.writeValueAsString(result);
	}
}
