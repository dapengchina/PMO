package com.pmo.dashboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.PerformanceManageTargetBean;
import com.pom.dashboard.service.PerformanceManageTargetService;

/**
 * Performance Management 绩效目标 页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value="/performanceManageTarget")

public class PerformanceManageTargetController {
	
	//private static Logger logger = LoggerFactory.getLogger(PerformanceEmpHistoryController.class);
	
	@Resource
	PerformanceManageTargetService performanceManageTargetService;
	
	@RequestMapping("/queryManageTargetApprovalList")
    @ResponseBody
	public Object queryManageTargetApprovalList(HttpServletRequest request, HttpServletResponse response) {
		List<PerformanceManageTargetBean> data = performanceManageTargetService.queryManageTargetApprovalList();
		return data;
	}
	
}
