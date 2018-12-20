package com.pmo.dashboard.controller;



import java.text.ParseException;
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

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pmo.dashboard.entity.Performancematrix;
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
@RequestMapping(value="/empPerforGoal")
public class EmployeePerforGoalController {
	
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource
	private PerformanceProgressService performanceProgressService;
	
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
	
	@Resource
	private UserService userService;
	
	@Resource
	private PerformanceResultService performanceResultService;
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
    private SimpleDateFormat sf3 = new SimpleDateFormat("yyyy");
	
	private SimpleDateFormat sf2 = new SimpleDateFormat("MM");
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getEmployeePerformance")
    @ResponseBody
	public String getEmployeePerformance(final HttpServletRequest request, final HttpServletResponse response,Model model) throws JsonProcessingException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		String employeeid = user.getUserId();
		Map<String,Object> map = new HashMap<String,Object>();
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
		/**
		 * 查询员工绩效目标设定总表(查询当年当季度的数据)
		 */
		Employeeperforgoal epg = new Employeeperforgoal();
		//获取当年当季度的开始日期并赋值
		epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		//获取当年当季度的结束日期并赋值
		epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		//员工ID
		epg.setEmployeeid(employeeid);
		Employeeperforgoal employperforgoal = employeeperforgoalService.getEmpPerforgoal(epg);

		/**
		 * 如果查不到员工当年当季度的数据，
		 * 则表示员工还没有设定绩效目标
		 */
		if(employperforgoal==null){
			Performancematrix pm = new Performancematrix();
			pm.setDepartment(user.getCsdeptId());//查询员工所在交付部的绩效模板参数数据
			List<Performancematrix> basePerforList = performanceMatrixService.getBasePerforTemplate(pm);
		    //部门汉字转换
			if(basePerforList!=null && basePerforList.size()>0){
		    	for(int i=0;i<basePerforList.size();i++){
		    		basePerforList.get(i).setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
		    	}
		    }
			map.put("data", basePerforList);
			map.put("plan", new ArrayList());
			map.put("state", SysConstant.PERFOR_DRAFT_STATE);
		}else{
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
			map.put("state", employperforgoal.getState());
		}
		return objectMapper.writeValueAsString(map);
	}
	
	
	@RequestMapping("/saveEmployeePerformance")
    @ResponseBody
	public String saveEmployeePerformance(final HttpServletRequest request, final HttpServletResponse response) throws JsonProcessingException{
		HttpSession session = request.getSession(); 
		User user = (User) session.getAttribute("loginUser");
		String employeeid = user.getUserId();
		String data1[] = request.getParameterValues("data1");//重点工作数据
		String data2[] = request.getParameterValues("data2");//关键事件数据
		String data3[] = request.getParameterValues("data3");//个人能力提升计划数据
		String state = request.getParameter("data4");//状态
		
		JSONArray jsonArray1 = new JSONArray(data1[0]);//重点工作数据
		JSONArray jsonArray2 = new JSONArray(data2[0]);//关键事件数据
		JSONArray jsonArray3 = new JSONArray(data3[0]);//个人能力提升计划数据
		
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			employeeperforgoalService.saveEmpPerforgoal(employeeid,jsonArray1, jsonArray2, jsonArray3,state);
			
			/**
			 * 保存到绩效结果表
			 */
			Employee em = employeeService.queryEmployeeById(employeeid);
			//查询中软部门信息
			CSDept csdept = cSDeptService.queryCSDeptById(em.getCsSubDept());
			User u = userService.queryUserById(em.getRmUserId());
			
			PresultVo pv = new PresultVo();
			pv.setResultId(Utils.getUUID());//主键
			pv.seteHr(user.getUserName());
			pv.setYear(sf3.format(new Date()));//当年
			pv.setQuarter(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))+"");//当季度
			pv.setBu(csdept!=null?csdept.getCsBuName():"");//事业部名称
			pv.setDu(csdept!=null?csdept.getCsSubDeptName():"");//交付部名称
			pv.setRm(u.getNickname());//RM名称
			pv.setRole(em.getRole());
			pv.setSkill(em.getSkill());
			pv.setLocation(em.getStaffLocation());
			pv.setBackbone(em.getBackbone());//是否是业务先锋
			pv.setAssessed(em.getAssessed());//是否参评
			pv.setDirectSupervisor(u.getNickname());//直接主管
			pv.setFinalize(SysConstant.ISNOTFINAL);//是否是最终结果
			pv.setState(SysConstant.PRESULT_PENDING_RM);//待RM审批
			performanceResultService.save(pv);
			
			map.put("msg", "保存成功");
		    map.put("code", "1");
		    return objectMapper.writeValueAsString(map);
		}catch(ParseException e) {
			map.put("msg", "保存失败");
		    map.put("code", "0");
		    return objectMapper.writeValueAsString(map);
		}
	}

	/**
	 * Management-绩效目标-审批-不通过
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/reject/{employeeid}")
    @ResponseBody
	public String reject(final HttpServletRequest request, final HttpServletResponse response,@PathVariable("employeeid") String employeeid) throws JsonProcessingException{
		String comments = request.getParameter("comments");
		Employeeperforgoal epg = new Employeeperforgoal();
		epg.setEmployeeid(employeeid);
		epg.setState(SysConstant.APPROVAL_NOPASS);
		epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		int i = employeeperforgoalService.update(epg);
		
		PerformanceEmpProcessBean pb = new PerformanceEmpProcessBean();
		pb.setEmployeeid(employeeid);
		pb.setState(SysConstant.PERFORMANCE_NOPASS);
		pb.setRemark(comments);
		pb.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		pb.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		performanceProgressService.updateState(pb);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(i>0){
			map.put("msg", "审批成功");
			map.put("code", "1");
		}else{
			map.put("msg", "审批失败");
			map.put("code", "0");
		}
		return objectMapper.writeValueAsString(map);
	}
	
	/**
	 * Management-绩效目标-审批-通过
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/approval/{employeeid}")
    @ResponseBody
	public String approval(final HttpServletRequest request, final HttpServletResponse response,@PathVariable("employeeid") String employeeid) throws JsonProcessingException{
		String comments = request.getParameter("comments");
		Employeeperforgoal epg = new Employeeperforgoal();
		epg.setEmployeeid(employeeid);
		epg.setState(SysConstant.APPROVAL_PASS);
		epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		int i = employeeperforgoalService.update(epg);
		
		PerformanceEmpProcessBean pb = new PerformanceEmpProcessBean();
		pb.setEmployeeid(employeeid);
		pb.setState(SysConstant.PERFORMANCE_PASS);
		pb.setRemark(comments);
		pb.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		pb.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		performanceProgressService.updateState(pb);
		
		/**
		 * 绩效目标审批通过-流程到-待员工自评
		 */
		PerformanceEmpProcessBean pb2 = new PerformanceEmpProcessBean();
		pb2.setId(Utils.getUUID());
		pb2.setEmployeeid(employeeid);
		pb2.setProcessid(SysConstant.PROCESS_TYPE2);
		pb2.setOwner("");
		pb2.setCreatedate(new Date());
		pb2.setState(SysConstant.PERFORMANCE_STATE1);
		performanceProgressService.saveProcess(pb2);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(i>0){
			map.put("msg", "审批成功");
			map.put("code", "1");
		}else{
			map.put("msg", "审批失败");
			map.put("code", "0");
		}
		return objectMapper.writeValueAsString(map);
	}
}
