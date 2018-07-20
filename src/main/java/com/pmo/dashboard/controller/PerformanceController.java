package com.pmo.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/performance")
public class PerformanceController {
	
	
	@SuppressWarnings("unused")
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@RequestMapping("/performancemain")
	public String listPage(HttpServletRequest request,Model model){
		
		return "performance/performance";
	}

}
