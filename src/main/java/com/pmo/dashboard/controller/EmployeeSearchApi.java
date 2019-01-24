package com.pmo.dashboard.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.api.reqmodel.EmployeeModel;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.QueryModel;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.DemandService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.HSBCDeptService;
import com.pom.dashboard.service.UserService;


@Controller
@RequestMapping(value="api/employee")
public class EmployeeSearchApi {
	
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private DemandService demandService;
	
	@Resource
	private CSDeptService csdeptService;
	
	@Resource
	private HSBCDeptService hsbcdeptService;
	
	@Resource
	private UserService userService;
	
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	@RequestMapping(value="/search/{ehr}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")  
	@ResponseBody
	public String search(@PathVariable("ehr") String ehr) throws ParseException, JsonProcessingException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<Employee> employee = employeeService.findByEhr(ehr);
        if(employee==null || employee.size()==0){
        	map.put("msg", "Record Not Found");
        	map.put("code", "0");
        	
        	return objectMapper.writeValueAsString(map);
        }
		
		
		//处理中软项目开始日期
        if(employee.get(0).getChsoftiProStartdate()!=null && !"".equals(employee.get(0).getChsoftiProStartdate())){
        	employee.get(0).setChsoftiProStartdate(sf.format(sf.parse(employee.get(0).getChsoftiProStartdate())));
        }
        //根据employeeid获取需求
        QueryModel qm = new QueryModel();
        qm.setEmployeeid(employee.get(0).getEmployeeId());
        List<Demand> list = demandService.getDemand(qm);
        if(list!=null && list.size()>0){
        	employee.get(0).setDemandrr(list.get(0).getRr());
        	employee.get(0).setDemandskill(list.get(0).getSkill());
        }
        
        //部门汉字转换
        CSDept csdept = null;
        if(employee.get(0).getCsSubDept()!=null && !"".equals(employee.get(0).getCsSubDept())){
        	csdept = csdeptService.queryCSDeptById(employee.get(0).getCsSubDept());
    		employee.get(0).setCsSubDeptName(csdept.getCsSubDeptName());
        }
        
        //汇丰部门转换汉字
        if(employee.get(0).getGbGf()!=null && !"".equals(employee.get(0).getGbGf())){
        	HSBCDept hsbcdept = new HSBCDept();
        	hsbcdept.setId(employee.get(0).getGbGf());
        	List<HSBCDept> rehsbcdept = hsbcdeptService.queryById(hsbcdept);
        	employee.get(0).setGbGf(rehsbcdept.get(0).getName());
        }
        
        if(employee.get(0).getHsbcSubDept()!=null && !"".equals(employee.get(0).getHsbcSubDept())){
        	String[] temp = employee.get(0).getHsbcSubDept().split(",");
        	HSBCDept hsbcdept2 = new HSBCDept();
        	String sub = "";
        	for(int i=0;i<temp.length;i++){
        		hsbcdept2.setId(temp[i]);
        		List<HSBCDept> rehsbcdept = hsbcdeptService.queryById(hsbcdept2);
        		sub += rehsbcdept.get(0).getName()+"/";
        	}
        	employee.get(0).setHsbcDeptSubName(sub);
        }
        
        //Rm 转换
        if(employee.get(0).getRmUserId()!=null && !"".equals(employee.get(0).getRmUserId())){
        	User u = userService.queryUserById(employee.get(0).getRmUserId());
        	employee.get(0).setRmUserId(u.getNickname());
        }
        
        //返回model转换
        EmployeeModel em = new EmployeeModel();
        em.setCsSubDeptName(employee.get(0).getCsSubDeptName());
        em.seteHr(employee.get(0).geteHr());
        em.setEmail(employee.get(0).getEmail());
        em.setEmployeeType(employee.get(0).getEmployeeType());
        em.setEngagementType(employee.get(0).getEngagementType());
        em.setEntryDate(employee.get(0).getEntryDate());
        em.setGbGf(employee.get(0).getGbGf());
        em.setGraduationDate(employee.get(0).getGraduationDate());
        em.setHsbcDOJ(employee.get(0).getHsbcDOJ());
        em.setHsbcStaffId(employee.get(0).getHsbcStaffId());
        em.setLn(employee.get(0).getLn());
        em.setLob(employee.get(0).getLob());
        em.setLocationType(employee.get(0).getLocationType());
        em.setOnshoreOrOffshore(employee.get(0).getOnshoreOrOffshore());
        em.setResourceStatus(employee.get(0).getResourceStatus());
        em.setRm(employee.get(0).getRmUserId());
        em.setRole(employee.get(0).getRole());
        em.setSkill(employee.get(0).getSkill());
        em.setStaffCategory(employee.get(0).getStaffCategory());
        em.setStaffLocation(employee.get(0).getStaffLocation());
        em.setStaffName(employee.get(0).getStaffName());
        em.setStaffRegion(employee.get(0).getStaffRegion());
        em.setTerminatedDate(employee.get(0).getTerminatedDate());
        em.setTerminationReason(employee.get(0).getTerminationReason());
        em.setCsBuName(csdept.getCsBuName());
        
        return objectMapper.writeValueAsString(em);
	}

}
