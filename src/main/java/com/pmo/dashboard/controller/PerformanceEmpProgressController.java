package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pmo.dashboard.entity.ProcessType;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.DateUtils;
import com.pom.dashboard.service.PerformanceProgressService;
import com.pom.dashboard.service.ProcessTypeService;

/**
 * Performance Progress 页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value="/performanceEmpProgress")
public class PerformanceEmpProgressController {
	
	
	@Resource
	private PerformanceProgressService progressService;
	
	@Resource
	private ProcessTypeService processTypeService;
	
	private ObjectMapper objectMapper = new ObjectMapper();  

	@RequestMapping("/queryPerformanceEmpProgressList")
    @ResponseBody
	public String queryPerformanceEmpProgressList(HttpServletRequest request) throws JsonProcessingException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		String employeeid = user.getUserId();
		PerformanceEmpProcessBean pb = new PerformanceEmpProcessBean();
		pb.setEmployeeid(employeeid);
		pb.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		pb.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
	
		Map<String,Object> result = new HashMap<String,Object>();
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(1,500); 
		List<PerformanceEmpProcessBean> list = progressService.queryPerformanceProgressList(pb);
		if(list!=null && list.size()>0){
			ProcessType pt = new ProcessType();
			for(int i=0;i<list.size();i++){
				pt.setId(list.get(i).getProcessid());
				ProcessType rept = processTypeService.getProType(pt);
				if(rept!=null){
					list.get(i).setProcessname(rept.getProcess());
				}
			}
		}
		PageInfo<PerformanceEmpProcessBean> page = new PageInfo<PerformanceEmpProcessBean>(list);
		result.put("total", page.getTotal());
		result.put("rows", list);
		return objectMapper.writeValueAsString(result);
	}
	

}
