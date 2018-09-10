package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pom.dashboard.service.PerformanceEmpHistoryService;

/**
 * Performance Employee 绩效结果  页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value="/performanceEmpResult")
public class PerformanceEmpHistoryController {
	
	private static Logger logger = LoggerFactory.getLogger(PerformanceEmpHistoryController.class);
	
	@Resource
	private PerformanceEmpHistoryService empHistoryService;

	@RequestMapping("/queryPerformanceEmpHistoryList")
    @ResponseBody
	public String queryPerformanceEmpHistoryList(Integer pageSize, Integer pageNumber, PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
		logger.debug("PerformanceEmpHistoryController:" + condition);
		
		List<PerformanceEmpHistoryBean> data = empHistoryService.queryPerformanceEmpHistoryList(condition);
		Map<String,Object> map = new HashMap<String,Object>();

		PageHelper.startPage(pageNumber, pageSize);
		PageInfo<PerformanceEmpHistoryBean> page = new PageInfo<>(data);		
		map.put("total", page.getTotal());
		map.put("rows", data);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String rtn = objectMapper.writeValueAsString(map);
		logger.debug("rtn:" + rtn);
		return rtn;
	}

	@RequestMapping("/queryEmpCurrentPeriodList")
    @ResponseBody
	public Object queryPerformanceEmpHistoryList(HttpServletRequest request, HttpServletResponse response) {
		List<PerformanceEmpHistoryBean> data = empHistoryService.queryPerformanceEmpHistoryList(null);
		return data;
	}
	
}
