package com.pmo.dashboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserAuthority;
import com.pom.dashboard.service.UserAuthorityService;

@Controller
@RequestMapping(value="/performance")
public class PerformanceController {
	
	private static Logger logger = LoggerFactory.getLogger(PerformanceController.class);
	
    @Resource
	private UserAuthorityService userAuthorityService;
    
	@SuppressWarnings("unused")
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@RequestMapping("/listPage")
	public String listPage(HttpServletRequest request,Model model){		
		return "performance/performanceListPage";
	}


	@RequestMapping("/performanceEmpPBC")
	public String getTMemployee(final HttpServletRequest request, Model model){
		return "performance/performanceEmpPBC";
	}
	
	
    @RequestMapping("/performanceLeft")
    public ModelAndView left(final HttpServletRequest request,HttpSession session,
            final HttpServletResponse response)
    { 	
    	ModelAndView v =new ModelAndView();    	
    	v.setViewName("performance/performanceLeft");
    	
    	User u=(User)session.getAttribute("loginUser");
    	List<UserAuthority> list= userAuthorityService.queryUserAuthority(u.getUserType());    	
  	   	v.addObject("list", list);
    	
        return v;
    }
    
	@RequestMapping("/performanceEmpEvaSelf")
	public String getPerformanceEmpEvaSelf(final HttpServletRequest request, Model model){
		return "performance/performanceEmpEvaSelf";
	}

	@RequestMapping("/performanceEmpEvaProgress")
	public String getPerformanceEmpEvaProgress(final HttpServletRequest request, Model model){
		return "performance/performanceEmpEvaProgress";
	}

	@RequestMapping("/performanceEmpEvaCurrentPeriod")
	public String getPerformanceEmpEvaCurrentPeriod(final HttpServletRequest request, Model model){
		return "performance/performanceEmpEvaCurrentPeriod";
	}
	
	@RequestMapping("/performanceEmpEvaCurrentPeriodDetail")
	public String getPerformanceEmpEvaCurrentPeriodDetail(final HttpServletRequest request, Model model){
		return "performance/performanceEmpEvaCurrentPeriodDetail";
	}
	
	@RequestMapping("/performanceEmpEvaHistoryQuery")
	public String getPerformanceEmpEvaHistoryQuery(final HttpServletRequest request, Model model){
		return "performance/performanceEmpEvaHistoryQuery";
	}

	@RequestMapping("/performanceManageTargetApproval")
	public String getPerformanceManageTargetApproval(final HttpServletRequest request, Model model){
		return "performance/performanceManageTargetApproval";
	}

	@RequestMapping("/performanceManageTargetApprovalFilter")
	public String getPerformanceManageTargetApprovalFilter(final HttpServletRequest request, Model model){
		return "performance/performanceManageTargetApprovalFilter";
	}

	@RequestMapping("/performanceManageTargetApprovalDetail")
	public String getPerformanceManageTargetApprovalDetail(final HttpServletRequest request, Model model){
		return "performance/performanceManageTargetApprovalDetail";
	}

	@RequestMapping("/performanceManageEvaFirstDetail")
	public String getPerformanceManageEvaFirstDetail(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaFirstDetail";
	}

	@RequestMapping("/performanceManageEvaFirst")
	public String getPerformanceManageEvaFirst(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaFirst";
	}
	
	@RequestMapping("/performanceManageEvaFirstDetailComments")
	public String getPerformanceManageEvaFirstDetailComments(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaFirstDetailComments";
	}

	@RequestMapping("/performanceManageEvaSecondDU")
	public String getPerformanceManageEvaSecondDU(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaSecondDU";
	}

	@RequestMapping("/performanceManageEvaSecondQuery")
	public String getPerformanceManageEvaSecondQuery(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaSecondQuery";
	}

	@RequestMapping("/performanceManageEvaSecondBU")
	public String getPerformanceManageEvaSecondBU(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaSecondBU";
	}

	@RequestMapping("/performanceManageEvaSecondQueryDU")
	public String getPerformanceManageEvaSecondQueryDU(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaSecondQueryDU";
	}

	@RequestMapping("/performanceManageEvaFinal")
	public String getPerformanceManageEvaFinal(final HttpServletRequest request, Model model){
		return "performance/performanceManageEvaFinal";
	}
	
	@RequestMapping("/performanceManageResultHistoryQuery")
	public String getPerformanceManageResultHistoryQuery(final HttpServletRequest request, Model model){
		return "performance/performanceManageResultHistoryQuery";
	}

	@RequestMapping("/performanceManageResultHistory")
	public String getPerformanceManageResultHistory(final HttpServletRequest request, Model model){
		return "performance/performanceManageResultHistory";
	}

	@RequestMapping("/performanceManageTemplateDownload")
	public String getPerformanceManageTemplateDownload(final HttpServletRequest request, Model model){
		return "performance/performanceManageTemplateDownload";
	}
	
	
	
	
	
	
	
	
	
	
}
