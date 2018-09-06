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
 * Performance Employee History 页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value="/performanceEmpHistory")
public class PerformanceEmpHistoryController {
	
	private static Logger logger = LoggerFactory.getLogger(PerformanceEmpHistoryController.class);
	
	@Resource
	private PerformanceEmpHistoryService empHistoryService;

	@RequestMapping("/queryPerformanceEmpHistoryList")
    @ResponseBody
	public String queryPerformanceEmpHistoryList(int pageSize, int pageNumber, PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
		logger.debug("PerformanceEmpHistoryController:" + condition);
		
		PageHelper.startPage(pageNumber, pageSize);
		List<PerformanceEmpHistoryBean> data = empHistoryService.queryPerformanceEmpHistoryList(condition);
        PageInfo<PerformanceEmpHistoryBean> page = new PageInfo<>(data);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotal());
		map.put("rows", data);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String rtn = objectMapper.writeValueAsString(map);

		return rtn;
	}

	
	
}
