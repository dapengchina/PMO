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

import com.pmo.dashboard.entity.PerformanceProgressBean;
import com.pom.dashboard.service.PerformanceProgressService;

/**
 * Performance Progress 页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value="/performanceEmpProgress")
public class PerformanceEmpProgressController {
	private static Logger logger = LoggerFactory.getLogger(PerformanceEmpProgressController.class);
	
	@Resource
	private PerformanceProgressService progressService;

	@RequestMapping("/queryPerformanceEmpProgressList")
    @ResponseBody
	public Object queryPerformanceEmpProgressList(final HttpServletRequest request, final HttpServletResponse response){
        
		Map<String,Object> result = new HashMap<String,Object>();
		List<PerformanceProgressBean> list = progressService.queryPerformanceProgressList();
		result.put("data", list);
		result.put("pageInfo", null);
		return result;
	}
	

}
