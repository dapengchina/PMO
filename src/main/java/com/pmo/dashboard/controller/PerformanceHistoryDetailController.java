package com.pmo.dashboard.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.dao.PerformanceEmpPBCMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeImpplan;
import com.pmo.dashboard.entity.EmployeeKeyevent;
import com.pmo.dashboard.entity.EmployeeKpo;
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.vo.EmployeePerforGoalVo;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeImpplanService;
import com.pom.dashboard.service.EmployeeInfoService;
import com.pom.dashboard.service.EmployeeKeyeventService;
import com.pom.dashboard.service.EmployeeKpoService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeperforgoalService;
import com.pom.dashboard.service.PerformanceEmpHistoryService;
import com.pom.dashboard.service.PerformanceManageEvaService;
import com.pom.dashboard.service.PerformanceMatrixService;
import com.pom.dashboard.service.PerformanceProgressService;
import com.pom.dashboard.service.PerformanceResultService;
import com.pom.dashboard.service.PerformanceService;
import com.pom.dashboard.service.UserService;

@Controller
@RequestMapping(value="/performance/history")
public class PerformanceHistoryDetailController {
	
	
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
    
    @Resource
    private PerformanceManageEvaService  manageEvaService;
    
    @Resource
    private PerformanceEmpHistoryService empHistoryService;
    
    @Resource
    private CSDeptService                csDeptService;
    
    @Resource
    private EmployeeInfoService          employeeInfoService;
    
    @Resource
    private PerformanceService           performanceService;
    
    @Resource
    private EmployeeService              employeeService;
    
    @Resource
    public PerformanceEmpPBCMapper       performanceEmpPBCMapper;
    
    @Resource
	private PerformanceProgressService progressService;
    
    @Resource
    private UserService userService;
	
	
	
	
	/**
     * 历史绩效-员工详情数据
     * @return
     * @throws JsonProcessingException 
     */
    @RequestMapping("/historyDetailData/{employeeid}/{year}/{quarter}")
    @ResponseBody
    public String historyDetailData(
    		HttpServletRequest request,
    		@PathVariable("employeeid") String employeeid,
    		@PathVariable("year") String year,
    		@PathVariable("quarter") String quarter
    		) throws JsonProcessingException{
    	/**
    	 * 根据季度判断开始日期和结束日期
    	 */
    	String startDate = null;
    	String endDate = null;
    	if(quarter.equals("1")){
    		startDate = year+"01-01";
    		endDate = year+"03-31";
    	}
        if(quarter.equals("2")){
    		startDate = year+"04-01";
    		endDate = year+"06-30";
    	}
        if(quarter.equals("3")){
    		startDate = year+"07-01";
    		endDate = year+"09-30";
    	}
        if(quarter.equals("4")){
    		startDate = year+"10-01";
    		endDate = year+"12-31";
    	}
    	
    	
    	//HttpSession session = request.getSession();
    	Employee emp = employeeService.queryEmployeeById(employeeid);
		Map<String,Object> map = new HashMap<String,Object>();
		//Ehr
		map.put("ehr", emp.geteHr());
		//EmployeeName
		map.put("staffname", emp.getStaffName());
		//查询中软部门信息
		CSDept csdept = cSDeptService.queryCSDeptById(emp.getCsSubDept());
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
		eo.setCurrentQuarterStartDate(startDate);
		eo.setCurrentQuarterEndDate(endDate);
		List<EmployeeKpo> kpoList = employeeKpoService.getEmployeeKpo(eo);
		
		//查询关键事件表
		EmployeeKeyevent ek = new EmployeeKeyevent();
		ek.setEmployeeid(employeeid);//员工ID
		ek.setCurrentQuarterStartDate(startDate);
		ek.setCurrentQuarterEndDate(endDate);
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
		el.setCurrentQuarterStartDate(startDate);
		el.setCurrentQuarterEndDate(endDate);
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
	    epg.setCurrentQuarterStartDate(startDate);
	    epg.setCurrentQuarterEndDate(endDate);
	    Employeeperforgoal reperfor = employeeperforgoalService.getEmpPerforgoal(epg);
		
		//查询绩效结果表，获取comments
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(emp.geteHr());
		pmb.setYear(year);//传进来的年
		pmb.setQuarter(quarter);//传进来的季度
		PresultVo pv = performanceResultService.getPerformance(pmb);
		
		//查询绩效目标流程表，获取绩效目标审批comments
		PerformanceEmpProcessBean pepb = new PerformanceEmpProcessBean();
		pepb.setEmployeeid(employeeid);
		pepb.setProcessid(SysConstant.PROCESS_TYPE1);
		pepb.setCurrentQuarterStartDate(startDate);
		pepb.setCurrentQuarterEndDate(endDate);
		List<PerformanceEmpProcessBean> processList = progressService.queryPerformanceProgressList(pepb);
				
		if(processList!=null && processList.size()>0){
			map.put("processcomments", processList.get(0).getRemark());
		}else{
			map.put("processcomments", "");
		}
	    
		map.put("comments", pv!=null?pv.getResult_Comments():"");
		map.put("finalResult", pv!=null?pv.getResult():"");
		map.put("directresult", pv!=null?pv.getDirect_Supervisor_Assessment_Result():"");
		map.put("selfassessment", reperfor!=null?reperfor.getSelfassessment():"");
		map.put("state", reperfor!=null?reperfor.getState():"");
		map.put("data", data1);
		map.put("plan", planList);
		
		return objectMapper.writeValueAsString(map);
    }

}
