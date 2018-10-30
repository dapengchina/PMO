package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidateInterview;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.HSBCDeptService;
import com.pom.dashboard.service.InterviewService;

@Controller
@RequestMapping(value = "/interview")
public class InterviewController {

	@Resource
	private InterviewService interviewService;
	
	@Resource
    private HSBCDeptService hsbcDeptService;
	

	@RequestMapping("/interviewRecordInfo")
	public String interviewRecordInfo(HttpServletRequest request, HttpServletResponse response) {
		return "candidate/interviewRecordInfo";
	}

	@RequestMapping("/getCandidateList")
	@ResponseBody
	public Object getCandidateList(CandidateInfo candidate, HttpServletRequest request) {

		String pageState = candidate.getPageState();
		PageCondition page = new PageCondition();

		int dataCount = interviewService.getCandidateListCount(candidate);
		page.setDataCount(dataCount + "");
		page.setPageCount((dataCount - 1) / candidate.getPageRecNum() + 1 + "");
		if(candidate.getPageRecNum()!=null){
			page.setPageDataCount(candidate.getPageRecNum() + "");
		}else{
			page.setPageDataCount(Constants.PAGE_DATA_COUNT + "");
		}
		if ("".equals(pageState) || pageState == null || "frist".equals(pageState)) {
			page.setCurrentPage("1");
		} else if ("next".equals(pageState)) {
			page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage()) + 1 + "");
		} else if ("previous".equals(pageState)) {
			page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage()) - 1 + "");
		} else if ("last".equals(pageState)) {
			page.setCurrentPage(page.getPageCount());
		}
        if(candidate.getPageRecNum()!=null){
        	candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage()) - 1) * candidate.getPageRecNum() + "");
    		candidate.setPageDataCount(candidate.getPageRecNum() + "");
        }else{
        	candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage()) - 1) * Constants.PAGE_DATA_COUNT + "");
    		candidate.setPageDataCount(Constants.PAGE_DATA_COUNT + "");
        }
		List<CandidateInfo> list = interviewService.getCandidateList(candidate);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
		result.put("pageInfo", page);
		return result;
	}

	@RequestMapping("/interviewRecord")
	public String interviewRecord(HttpServletRequest request, HttpServletResponse response) {
		String candidateId = request.getParameter("candidateId");
		request.setAttribute("candidateId", candidateId);
		return "candidate/interviewRecord";
	}

	@RequestMapping("/getInterviewRecordByCandId")
	@ResponseBody
	public Object getInterviewRecordByCandId(String candidateId) {
		Map<String, List<CandidateInfo>> map = interviewService.getInterviewRecordByCandId(candidateId);
		return map;
	}

	@RequestMapping(value = "/lockCandidate")
	@ResponseBody
	public boolean lockCandidate(String candidateId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		String userId = user.getUserId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("candidateId", candidateId);

		return interviewService.lockCandidate(params);
	}

	@RequestMapping(value = "/getBillRate")
	@ResponseBody
	public String getBillRate(Employee employee, HttpServletRequest request) {
		return interviewService.getBillRate(employee);
	}

	@RequestMapping("/employeeDetailInfo")
	public String employeeDetailInfo(HttpServletRequest request, HttpServletResponse response,String type,Model model) {
		String employeeId = request.getParameter("employeeId");
		String typepage = request.getParameter("batchinfo");
		request.setAttribute("employeeId", employeeId);
		model.addAttribute("typepage", typepage);
		if("1".equals(type)) {
			return "employee/employeeDetailInfo";
		}else if("2".equals(type)) {
			return "employee/employeeDetailInfo2";
		}else {
			return "employee/employeeDetailInfo3";
		}
		
	}

	@RequestMapping("/queryEmployeeById")
	@ResponseBody
	public Employee queryEmployeeDetailById(String employeeId,HttpServletRequest request) {
		 User user = (User) request.getSession().getAttribute("loginUser");
		 String userType = user.getUserType();

		 Employee employee = interviewService.queryEmployeeById(employeeId);
		 if("6".equals(userType)||"7".equals(userType)||"8".equals(userType)||"10".equals(userType)||"11".equals(userType)||"12".equals(userType)){
			 employee.setBillRate("****");
		 }
		 
		 //获取GBGF
		 if(employee.getGbGf()!=null && !"".equals(employee.getGbGf())){
			 HSBCDept hsbcDept = new HSBCDept();
			 hsbcDept.setId(employee.getGbGf());
			 List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);
			 if(hsbcDeptList!=null && hsbcDeptList.size()>0){
				 employee.setGbGf(hsbcDeptList.get(0).getName());
			 }
		 }
		 
		 //获取HSBCDept
		 String temp[] = null;
		 if(employee.getHsbcSubDept()!=null && !"".equals(employee.getHsbcSubDept())){
			 temp = employee.getHsbcSubDept().split(",");
			 HSBCDept hsbcDept = new HSBCDept();
			 if(temp.length>0){
				 hsbcDept.setId(temp[0]);
				 List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);
				 if(hsbcDeptList!=null && hsbcDeptList.size()>0){
					 employee.setHsbcDeptName(hsbcDeptList.get(0).getName());
				 }
			 }
		 }
		 
		 //获取HSBCSubDept
		 if(temp!=null){
			 if(temp.length==2){
				 if(temp[1]!=null && !"".equals(temp[1])){
					 HSBCDept hsbcDept = new HSBCDept();
					 hsbcDept.setId(temp[1]);
					 List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);
					 if(hsbcDeptList!=null && hsbcDeptList.size()>0){
						 employee.setHsbcDeptSubName(hsbcDeptList.get(0).getName());
					 }
				 }
			 }
		 }
		 return employee;
	}
	
	
	@RequestMapping("/queryHSBCSubDeptById")
	@ResponseBody
	public HSBCDept queryHSBCSubDeptById(String hsbcSubDeptId) {

		HSBCDept hSBCDept = interviewService.queryHSBCSubDeptById(hsbcSubDeptId);
		return hSBCDept;
	}
	
	@RequestMapping("/getNewInterviewRecord")
	@ResponseBody
	public CandidateInterview getNewInterviewRecord(HttpServletRequest request, HttpServletResponse response) {
		String candidateId = request.getParameter("candidateId");
		String csSubdeptName = request.getParameter("csSubdeptName");
		CandidateInterview candidateInterview = interviewService.getNewInterviewRecord(candidateId, csSubdeptName);
		return candidateInterview;
	}
}
