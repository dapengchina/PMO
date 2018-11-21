package com.pmo.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.pmo.dashboard.entity.Employeeperforgoal;
//import com.pmo.dashboard.entity.PerformanceEmpKPOBean;
//import com.pmo.dashboard.entity.PerformanceEmpKeyEventBean;
//import com.pmo.dashboard.entity.PerformanceEmpPBC2Bean;
//import com.pmo.dashboard.entity.PerformanceEmpPBCBean;
//import com.pmo.dashboard.entity.PerformanceEmpPBCPlanBean;
//import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
//import com.pmo.dashboard.entity.User;
//import com.pmo.dashboard.util.Utils;
//import com.pom.dashboard.service.PerformanceEmpPBCService;


@Controller
@RequestMapping(value="/performanceEmpPBC")
public class PerformanceEmpPBCController {
	
	
//	@Resource
//	private PerformanceEmpPBCService performanceEmpPBCService;

//	@RequestMapping("/queryPerformanceEmpFirstList")
//    @ResponseBody
//	public Object queryPerformanceEmpPBC1List(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		Map<String,Object> result = new HashMap<String,Object>();
//		List<PerformanceEmpPBCBean> list = performanceEmpPBCService.queryPerformanceEmpPBCList(employeeid);
//		result.put("data", list);
//		result.put("pageInfo", null);
//		return result;
//	}
//	@RequestMapping("/queryPerformanceEmpList2")
//    @ResponseBody
//	public Object queryPerformanceEmpPBC2List(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		Map<String,Object> result = new HashMap<String,Object>();
//		List<PerformanceEmpPBC2Bean> list = performanceEmpPBCService.queryPerformanceEmpPBC2List(employeeid);
//		result.put("data", list);
//		result.put("pageInfo", null);
//		return result;
//	}
//	@RequestMapping("/queryPerformanceEmpList3")
//    @ResponseBody
//	public Object queryPerformanceEmpPlanList(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		Map<String,Object> result = new HashMap<String,Object>();
//		List<PerformanceEmpPBCPlanBean> list = performanceEmpPBCService.queryPerformanceEmpPlanList(employeeid);
//		result.put("data", list);
//		result.put("pageInfo", null);
//		return result;
//	}
//	@RequestMapping("/queryPerformanceEmpState")
//    @ResponseBody
//	public Object queryPerformanceProcess(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		Map<String,Object> result = new HashMap<String,Object>();
//		List<Employeeperforgoal> list = performanceEmpPBCService.queryPerformanceEmpState(employeeid);
//		result.put("data", list);
//		result.put("pageInfo", null);
//		return result;
//	}
//	@RequestMapping("/savePerformanceEmpProcess")
//	@ResponseBody
//	public boolean savePerformanceEmpProcess(final HttpServletRequest request, final HttpServletResponse response) {
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		String id = Utils.getUUID();
//		String processid = Utils.getUUID();
//		String owner = "Pim";
//		Date date = new Date();
//		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String createdate = dateFormat.format(date);
//		String state = "待审批";
//		String remark = "";
//		PerformanceEmpProcessBean performanceEmpProcessBean = new PerformanceEmpProcessBean(processid, owner, createdate, state, remark, processid, employeeid);
//		boolean a = performanceEmpPBCService.savePerformanceEmpProcess(performanceEmpProcessBean);
//		return a;
//		return false;
//		
//	}
//	@RequestMapping("/deletePerformanceEmpState")
//    @ResponseBody
//    public int deletePerformanceEmpState(final HttpServletRequest request, final HttpServletResponse response) {
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		int d = performanceEmpPBCService.deletePerformanceEmpState(employeeid);
//		
//		return d;
//		
//	}
    
//	@RequestMapping("/savePerformanceEmpState")
//    @ResponseBody
//	public boolean  savePerformanceEmpState(final HttpServletRequest request, final HttpServletResponse response){
//        String id = Utils.getUUID();
//        HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//        String staffname = "";
//        String staffid = "";
//        String ehr = "";
//        String department = "";
//        String assessmensupervisor = "";
//        String employeekpoid = Utils.getUUID();
//        String employeeekeyeventid = Utils.getUUID();
//        String employeeimpplanid = Utils.getUUID();
//        String position = "";
//		Date date = new Date();
//		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String createdate = dateFormat.format(date);
//		String state = request.getParameter("data");
//		//Employeeperforgoal employeeperforgoal = new Employeeperforgoal(id,employeeid,staffname,staffid,ehr,department,position,assessmensupervisor,employeekpoid,employeeekeyeventid,employeeimpplanid,createdate,state);
//		//boolean a = performanceEmpPBCService.savePerformanceEmpState(employeeperforgoal) ;
//		return false;
//	}
//	@RequestMapping("/queryPerformanceEmpKPOList")
//    @ResponseBody
//	public Object queryPerformanceEmpKPOList(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		Map<String,Object> result = new HashMap<String,Object>();
//		List<PerformanceEmpKPOBean> list = performanceEmpPBCService.queryPerformanceEmpKPOList(employeeid);
//		result.put("data", list);
//		result.put("pageInfo", null);
//		return result;
//	}
//	@RequestMapping("/queryPerformanceEmpEventList")
//    @ResponseBody
//	public Object queryPerformanceEmpEventList(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		Map<String,Object> result = new HashMap<String,Object>();
//		List<PerformanceEmpKeyEventBean> list = performanceEmpPBCService.queryPerformanceEmpEventList(employeeid);
//		result.put("data", list);
//		result.put("pageInfo", null);
//		return result;
//	}
//	@RequestMapping("/savePerformanceEmpKPO")
//	@ResponseBody
//	public boolean savePerformanceEmpKPO(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		String data[] = request.getParameterValues("table1");
//		JSONArray jsonArray = new JSONArray(data[0]);
//		for( int i=0;i<jsonArray.length();i++) {
//		JSONObject job = jsonArray.getJSONObject(i);
//		String id =  Utils.getUUID();
//		String index = (String)job.get("index");
//		String description = (String)job.get("description");
//		String weightrate = (String)job.get("weightrate");
//		String phasegoal = (String)job.get("phasegoal");
//		String keyaction = (String)job.get("keyaction");
//		Date date = new Date();
//		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String createdate = dateFormat.format(date);
//		PerformanceEmpKPOBean performanceEmpKPO = new PerformanceEmpKPOBean(id,index,description,weightrate,phasegoal,keyaction,createdate,employeeid);
//		boolean x = performanceEmpPBCService.savePerformanceEmpKPO(performanceEmpKPO);
//		}		
//		return true;
//         
//		
//	}
//	@RequestMapping("/savePerformanceEmpKeyEvent")
//	@ResponseBody
//	public boolean savePerformanceEmpKeyEvent(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		String data[] = request.getParameterValues("table2");
//		JSONArray jsonArray = new JSONArray(data[0]);
//		for( int i=0;i<jsonArray.length();i++) {
//		JSONObject job = jsonArray.getJSONObject(i);
//		String id =  Utils.getUUID();
//		String index = (String)job.get("index");
//		String description = (String)job.get("description");
//		String weightrate = (String)job.get("weightrate");
//		String phasegoal = (String)job.get("phasegoal");
//		String keyaction = (String)job.get("keyaction");
//		Date date = new Date();
//		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String createdate = dateFormat.format(date);
//		PerformanceEmpKeyEventBean performanceEmpKeyEvent = new PerformanceEmpKeyEventBean(id,index,description,weightrate,phasegoal,keyaction,createdate,employeeid);
//		boolean x = performanceEmpPBCService.savePerformanceEmpKeyEvent(performanceEmpKeyEvent);
//		}		
//		return true;
//         
//		
//	}
//	@RequestMapping("/savePerformanceEmpPlan")
//	@ResponseBody
//	public boolean savePerformanceEmpPlan(final HttpServletRequest request, final HttpServletResponse response){
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		String data[] = request.getParameterValues("table3");
//		JSONArray jsonArray = new JSONArray(data[0]);
//		System.out.println("前台传送数据==========="+jsonArray.length());
//		for( int i=0;i<jsonArray.length();i++) {
//		JSONObject job = jsonArray.getJSONObject(i);
//		String id =  Utils.getUUID();
//		String keyability = (String)job.get("keyability");
//		String action = (String)job.get("action");
//		String supportor = (String)job.get("supportor");
//		String dealine = (String)job.get("dealine");
//		Date date = new Date();
//		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String createdate = dateFormat.format(date);
//		PerformanceEmpPBCPlanBean performanceEmpPlan = new PerformanceEmpPBCPlanBean(id,keyability,action,supportor,dealine,createdate,employeeid);
//		boolean x = performanceEmpPBCService.savePerformanceEmpPlan(performanceEmpPlan);
//		}		
//		return true;
//         
//		
//	}
//	@RequestMapping("/deletePerformanceEmpKPO")
//	@ResponseBody
//	public int deletePerformanceEmpKPO(final HttpServletRequest request, final HttpServletResponse response) {
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		int d = performanceEmpPBCService.deletePerformanceEmpKPO(employeeid) ;
//		return d;
//		
//	}
//	@RequestMapping("/deletePerformanceEmpKeyEvent")
//	@ResponseBody
//	public int deletePerformanceEmpkeyEvent(final HttpServletRequest request, final HttpServletResponse response) {
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		int d = performanceEmpPBCService.deletePerformanceEmpKeyEvent(employeeid) ;
//		return d;
//		
//	}
//	@RequestMapping("/deletePerformanceEmpPlan")
//	@ResponseBody
//	public int deletePerformanceEmpPlan(final HttpServletRequest request, final HttpServletResponse response) {
//		HttpSession session = request.getSession(); 
//		User user = (User) session.getAttribute("loginUser");
//		String employeeid = user.getUserId();
//		int d = performanceEmpPBCService.deletePerformanceEmpPlan(employeeid) ;
//		return d;
//		
//	}

	


}
