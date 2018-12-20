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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeImpplan;
import com.pmo.dashboard.entity.EmployeeKeyevent;
import com.pmo.dashboard.entity.EmployeeKpo;
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.vo.EmployeePerforGoalVo;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pmo.dashboard.util.DateUtils;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeImpplanService;
import com.pom.dashboard.service.EmployeeKeyeventService;
import com.pom.dashboard.service.EmployeeKpoService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeperforgoalService;
import com.pom.dashboard.service.PerformanceMatrixService;
import com.pom.dashboard.service.PerformanceProgressService;
import com.pom.dashboard.service.PerformanceResultService;
import com.pom.dashboard.service.UserService;

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
	
	@Resource
	private EmployeeService employeeService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy");
	
	private SimpleDateFormat sf2 = new SimpleDateFormat("MM");
	
	//private SimpleDateFormat sf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private UserService userService;
	
	@Resource
	private PerformanceProgressService progressService;
	
	@Resource
	private PerformanceProgressService performanceProgressService;
	
	
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
		//员工Ehr
		pmb.setEhr(user.getUserName());
		//当年
		pmb.setYear(sf.format(new Date()));
		//当季度
		pmb.setQuarter(String.valueOf(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))));
		//最终结果
		pmb.setFinalize(SysConstant.ISFINAL);
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(1,500); 
		PresultVo pv = performanceResultService.getPerformance(pmb);
		if(pv!=null){
			list.add(pv);
		}
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
	 * Employee-绩效结果-历史绩效-员工详情页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getHistoryDetail/{employeeid}/{quarter}/{year}")
    public String getHistoryDetail(
    		final HttpServletRequest request, 
    		final HttpServletResponse response,
    		@PathVariable("employeeid") String employeeid,
    		@PathVariable("quarter") String quarter,
    		@PathVariable("year") String year,
    		Model model
    		){
		model.addAttribute("employeeid", employeeid);//员工ID
    	model.addAttribute("year", year);//年
    	model.addAttribute("quarter", quarter);//季度
		return "performance/employee/performanceEmpHistoryDetail";
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
		
		//查询绩效目标流程表，获取绩效目标审批comments
		PerformanceEmpProcessBean pepb = new PerformanceEmpProcessBean();
		pepb.setEmployeeid(employeeid);
		pepb.setProcessid(SysConstant.PROCESS_TYPE1);
		pepb.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		pepb.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<PerformanceEmpProcessBean> processList = progressService.queryPerformanceProgressList(pepb);
								
		if(processList!=null && processList.size()>0){
			map.put("processcomments", processList.get(0).getRemark());
		}else{
			map.put("processcomments", "");
		}
	    
		map.put("comments", pv!=null?pv.getResult_Comments():"");
		map.put("directresult", pv!=null?pv.getDirect_Supervisor_Assessment_Result():"");
		map.put("finalResult", pv!=null?pv.getResult():"");
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
		User user = (User) request.getSession().getAttribute("loginUser");
        List<String>  csSubDeptNames = new ArrayList<String>();   
        
        List<CSDept> cSDepts = null;
        
        if(user.getCsdeptId() != null && user.getCsdeptId() != ""){
        	cSDepts= cSDeptService.queryCSDeptByIds(user.getCsdeptId().split(","));
        	for (CSDept csDept : cSDepts) {
                csSubDeptNames.add(csDept.getCsSubDeptName());
            }
        }
        
        /**
         * 获取传过来的搜索条件
         */
        String msaRole = request.getParameter("msaRole");
        String skill = request.getParameter("skill");
        String bu = request.getParameter("bu");
        String du = request.getParameter("du");
        String startYear = request.getParameter("startYear");
        String endYear = request.getParameter("endYear");
        String sq = request.getParameter("sq");
        String eq = request.getParameter("eq");
		
		Map<String,Object> result = new HashMap<String,Object>();
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(user.getUserName());
		pmb.setFinalize(SysConstant.ISFINAL);//最终结果
		if(msaRole!=null && !"".equals(msaRole) && !msaRole.equals("null")){
			pmb.setRole(msaRole);
		}
        if(skill!=null && !"".equals(skill) && !skill.equals("null")){
			pmb.setSkill(skill);
		}
        if(bu!=null && !"".equals(bu) && !bu.equals("null")){
			pmb.setBu(bu);
		}
        if(du!=null && !"".equals(du) && !du.equals("null")){
			pmb.setDu(du);
		}
        if(startYear!=null && !"".equals(startYear) && !startYear.equals("null")){
        	pmb.setStartYear(startYear);
        }
        if(endYear!=null && !"".equals(endYear) && !endYear.equals("null")){
        	pmb.setEndYear(endYear);
        }
        if(sq!=null && !"".equals(sq) && !sq.equals("null")){
        	pmb.setStartQuarter(sq);
        }
        if(eq!=null && !"".equals(eq) && !eq.equals("null")){
        	pmb.setEndQuarter(eq);
        }
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize); 
		List<PresultVo> list = performanceResultService.getPerformanceList(pmb);
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).setEmployeeid(user.getUserId());
			}
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		PageInfo<PresultVo> page = new PageInfo(list);
		result.put("total", page.getTotal());
		result.put("rows", list);
		
		result.put("user", user);
		result.put("csSubDeptNames", csSubDeptNames);
		return objectMapper.writeValueAsString(result);
	}

    
	/**
	 * Employee-绩效考评-员工自评
	 * @param request
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/saveSelfEvaluation")
	@ResponseBody
    public String saveSelfEvaluation(HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			String selfevaluation = request.getParameter("selfevaluation");
			String employeeid = request.getParameter("employeeid");
			Employee em = employeeService.queryEmployeeById(employeeid);
			//查询中软部门信息
			CSDept csdept = cSDeptService.queryCSDeptById(em.getCsSubDept());
			User u = userService.queryUserById(em.getRmUserId());
			
//			PresultVo pv = new PresultVo();
//			pv.setResultId(Utils.getUUID());//主键
//			pv.seteHr(user.getUserName());
//			pv.setYear(sf.format(new Date()));//当年
//			pv.setQuarter(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))+"");//当季度
//			pv.setBu(csdept!=null?csdept.getCsBuName():"");//事业部名称
//			pv.setDu(csdept!=null?csdept.getCsSubDeptName():"");//交付部名称
//			pv.setRm(u.getNickname());//RM名称
//			pv.setRole(em.getRole());
//			pv.setSkill(em.getSkill());
//			pv.setLocation(em.getStaffLocation());
//			pv.setBackbone(em.getBackbone());//是否是业务先锋
//			pv.setAssessed(em.getAssessed());//是否参评
//			pv.setDirectSupervisor(u.getNickname());//直接主管
//			pv.setFinalize(SysConstant.ISNOTFINAL);//是否是最终结果
//			pv.setState(SysConstant.PRESULT_PENDING_RM);//待RM审批
//			performanceResultService.save(pv);
			
			//修改自评信息
			Employeeperforgoal epg = new Employeeperforgoal();
			epg.setSelfassessment(selfevaluation);
			epg.setEmployeeid(employeeid);
			epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
			epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
			employeeperforgoalService.update(epg);
			
			
			PerformanceEmpProcessBean pb = new PerformanceEmpProcessBean();
			pb.setEmployeeid(employeeid);
			pb.setState(SysConstant.PERFORMANCE_STATE12);
			pb.setProcessid(SysConstant.PROCESS_TYPE2);
			pb.setRemark(selfevaluation);
			pb.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
			pb.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
			performanceProgressService.updateState(pb);
			
//			/**
//			 * 员工自评完成-流程到-待RM初评
//			 */
//			PerformanceEmpProcessBean pb2 = new PerformanceEmpProcessBean();
//			pb2.setId(Utils.getUUID());
//			pb2.setEmployeeid(employeeid);
//			pb2.setProcessid(SysConstant.PROCESS_TYPE3);
//			pb2.setOwner(u.getNickname());
//			pb2.setCreatedate(new Date());
//			pb2.setState(SysConstant.PERFORMANCE_STATE2);
//			performanceProgressService.saveProcess(pb2);
			
			map.put("msg", "自评成功");
			map.put("code", "1");
		}catch(Exception e){
			map.put("msg", "自评失败");
			map.put("code", "0");
		}
    	return objectMapper.writeValueAsString(map);
    }
}
