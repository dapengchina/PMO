package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.EmployeeSkill;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.EmployeeSkillService;

@Controller
@RequestMapping(value="/skill")
public class EmployeeSkillController {
	private static Logger logger = LoggerFactory.getLogger(EmployeeSkillController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource
	private EmployeeSkillService employeeSkillService;
	
	@RequestMapping("/listPage")
	public String listPage(HttpServletRequest request,Model model){
		User user = (User) request.getSession().getAttribute("loginUser");
		return "skill/employeeSkillList";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(int pageSize,int pageNumber,EmployeeSkill condition,HttpServletRequest request) throws JsonProcessingException{
		PageHelper.startPage(pageNumber,pageSize); 	
		User user = (User) request.getSession().getAttribute("loginUser");
		List<EmployeeSkill> data = employeeSkillService.query(condition);
		PageInfo<OfflineOper> page = new PageInfo(data);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	
	@RequestMapping("/queryById")
	@ResponseBody
	public Object queryById(String id)
    {
        return employeeSkillService.select(id);
    }
	
	@RequestMapping("/skills")
	@ResponseBody
	public Object skill(String id)
    {
        return employeeSkillService.skills();
    }
	
	@RequestMapping("/detail/{eHr}")
	@ResponseBody
	public String detail(@PathVariable String eHr)  throws JsonProcessingException
    {
        return objectMapper.writeValueAsString(employeeSkillService.detail(eHr));
    }
	
	@RequestMapping("/toEdit/{eHr}")
	@ResponseBody
	public String toEdit(@PathVariable String eHr)  throws JsonProcessingException
    {
        return objectMapper.writeValueAsString(employeeSkillService.toEdit(eHr));
    }
	
	@RequestMapping(value= {"/save"})
	@ResponseBody
	public String save(EmployeeSkill skill,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		boolean rtn ;
		if("0".equals( skill.getStatus())) {
			rtn = employeeSkillService.insert(skill);
		}else {
			rtn = employeeSkillService.update(skill);
		}
		return objectMapper.writeValueAsString( rtn? "1" : "0");
	}
	
	@RequestMapping(value= {"/delete/{id}"})
	@ResponseBody
	public String delete(@PathVariable String id,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		boolean rtn ;
		rtn = employeeSkillService.delete(id);
		return objectMapper.writeValueAsString( rtn? "1" : "0");
	}
	
	@RequestMapping("/toBatch")
	@ResponseBody
	public String toBatch()  throws JsonProcessingException
    {
        return objectMapper.writeValueAsString(employeeSkillService.toBatch());
    }
	
	@RequestMapping(value= {"/batch"})
	@ResponseBody
	public String batch(EmployeeSkill skill,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		boolean rtn ;
		rtn = employeeSkillService.batch(skill);
		return objectMapper.writeValueAsString( rtn? "1" : "0");
	}
}
