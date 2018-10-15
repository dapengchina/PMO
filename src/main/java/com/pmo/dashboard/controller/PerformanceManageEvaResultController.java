package com.pmo.dashboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pmo.dashboard.entity.PerformanceManageResultHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pom.dashboard.service.PerformanceManageEvaService;

/**
 * Performance Management 绩效结果  页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value="/performanceManageResultHistory")
public class PerformanceManageEvaResultController {
	private static Logger logger = LoggerFactory.getLogger(PerformanceManageEvaResultController.class);

	@Resource
	private PerformanceManageEvaService manageEvaService;
	
	@RequestMapping("/queryManageResultHistoryQueryList")
    @ResponseBody
	public Object queryManageResultHistoryQueryList(PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
		logger.debug("query condition:" + condition);
		List<PerformanceManageResultHistoryBean> data = manageEvaService.queryManageResultHistoryQueryList(condition);	
		return data;
	}
	
}
