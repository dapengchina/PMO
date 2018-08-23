package com.pmo.dashboard.controller;

import java.util.List;

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

@Controller
@RequestMapping(value="/performance")
public class PerformanceController {
	
	private static Logger logger = LoggerFactory.getLogger(PerformanceController.class);
	
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
	
}
