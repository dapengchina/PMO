package com.pmo.dashboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.User;

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
	

}
