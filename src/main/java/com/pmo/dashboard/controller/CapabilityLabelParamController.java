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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.CapabilityLabelParam;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.CapabilityLabelParamService;

@Controller
@RequestMapping(value="/capability")
public class CapabilityLabelParamController {
	private static Logger logger = LoggerFactory.getLogger(CapabilityLabelParamController.class);
	private ObjectMapper objectMapper = new ObjectMapper();  
	
	@Resource
	private CapabilityLabelParamService capabilityLabelParamService;
	
	@RequestMapping("/listPage")
	public String listPage(HttpServletRequest request,Model model){
		User user = (User) request.getSession().getAttribute("loginUser");
		return "capability/capabilityList";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(int pageSize,int pageNumber,CapabilityLabelParam condition,HttpServletRequest request) throws JsonProcessingException{
		PageHelper.startPage(pageNumber,pageSize); 	
		User user = (User) request.getSession().getAttribute("loginUser");
		List<CapabilityLabelParam> data = capabilityLabelParamService.query(condition);
		PageInfo<OfflineOper> page = new PageInfo(data);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	
	@RequestMapping("/majorcateIds")
	@ResponseBody
	public String majorcateIds()  throws JsonProcessingException{
		List<CapabilityLabelParam> data = capabilityLabelParamService.majorcateIds() ;
		return objectMapper.writeValueAsString(data);
	}
	
	@RequestMapping("/maxSubCate")
	@ResponseBody
	public String maxSubCate(@RequestParam String majorcateId)  throws JsonProcessingException{
		return capabilityLabelParamService.maxSubCate(majorcateId) ;
	}
	
	@RequestMapping("/addPage")
	public String addPage()
    {
        return "capability/capabilityAdd";
    }
	
	@RequestMapping("/editPage")
	public String editPage(String id,Model model)
    {
		model.addAttribute("id", id);
        return "capability/capabilityEdit";
    }
	@RequestMapping("/queryById")
	@ResponseBody
	public Object queryById(String id)
    {
//		CapabilityLabelParam data = capabilityLabelParamService.select(id);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("date", data);
        return capabilityLabelParamService.select(id);
    }
	
	@RequestMapping(value= {"/add"})
	@ResponseBody
	public String add(CapabilityLabelParam capabilityLabelParam,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		capabilityLabelParam.setOperateId(user.getUserId());
		boolean rtn = capabilityLabelParamService.insert(capabilityLabelParam);
		return objectMapper.writeValueAsString( rtn? "0" : "-1");
	}
	
	@RequestMapping(value= {"/update"})
	@ResponseBody
	public String update(CapabilityLabelParam capabilityLabelParam,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		capabilityLabelParam.setOperateId(user.getUserId());
		boolean rtn = capabilityLabelParamService.update(capabilityLabelParam);
		return objectMapper.writeValueAsString( rtn? "0" : "-1");
	}
}
