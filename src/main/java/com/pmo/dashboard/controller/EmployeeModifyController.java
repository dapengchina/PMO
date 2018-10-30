package com.pmo.dashboard.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.TransferDept;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.EmployeeLogService;
import com.pom.dashboard.service.EmployeeModifyService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.HSBCDeptService;

@Controller
@RequestMapping(value="/employee")
public class EmployeeModifyController {
	@Resource
	private EmployeeModifyService employeeModifyService;
	
	@Resource
    private HSBCDeptService hsbcDeptService;
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private EmployeeLogService  employeeLogService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/propertiesModify")
    @ResponseBody
    public boolean updateProperties(HttpServletRequest request,TransferDept transferDept){   
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		try{
 	        	User user = (User)request.getSession().getAttribute("loginUser");
 	        	//修改前的employee
 	        	Employee em = employeeService.queryEmployeeById(staffs[i]);
 	        	EmployeeLog log = getEmployeeLog(transferDept,user,"1");
 	    		StringBuffer[] sbForResult=checkFieldChange(em,transferDept);
 	    		if(sbForResult[0].length()>0){
 					employeeLogService.save(log);
 	    		}
 				
 	    		if(!employeeModifyService.modifyProperties(transferDept)){
 	    			result = false;
 	    		}
 	        }catch(Exception e){
 	        	e.printStackTrace();
 	        }
    	}
        return result;
    }
	@SuppressWarnings("unchecked")
	@RequestMapping("/rolesModify")
    @ResponseBody
    public boolean updateRoles(HttpServletRequest request,TransferDept transferDept){ 
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		try{
 	        	User user = (User)request.getSession().getAttribute("loginUser");
 	        	//修改前的employee
 	        	Employee em = employeeService.queryEmployeeById(staffs[i]);
 	        	EmployeeLog log = getEmployeeLog(transferDept,user,"1");
 	    		StringBuffer[] sbForResult=checkFieldChange(em,transferDept);
 	    		if(sbForResult[0].length()>0){
 					employeeLogService.save(log);
 	    		}
 				
 	    		if(!employeeModifyService.modifyRoles(transferDept)){
 	    			result = false;
 	    		}
 	        }catch(Exception e){
 	        	e.printStackTrace();
 	        }
    	}
        return result;
    }
	@SuppressWarnings("unchecked")
	@RequestMapping("/deptModify")
    @ResponseBody
    public boolean updateDept(HttpServletRequest request,TransferDept transferDept){   
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		try{
 	        	User user = (User)request.getSession().getAttribute("loginUser");
 	        	//修改前的employee
 	        	Employee em = employeeService.queryEmployeeById(staffs[i]);
 	        	EmployeeLog log = getEmployeeLog(transferDept,user,"1");
 	    		StringBuffer[] sbForResult=checkFieldChange(em,transferDept);
 	    		if(sbForResult[0].length()>0){
 					employeeLogService.save(log);
 	    		}
 				
 	    		if(!employeeModifyService.modifyDept(transferDept)){
 	    			result = false;
 	    		}
 	        }catch(Exception e){
 	        	e.printStackTrace();
 	        }
    	}
        return result;
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/updateRM")
    @ResponseBody
    public boolean updateRM(HttpServletRequest request,TransferDept transferDept) {
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		try{
 	        	User user = (User)request.getSession().getAttribute("loginUser");
 	        	//修改前的employee
 	        	Employee em = employeeService.queryEmployeeById(staffs[i]);
 	        	EmployeeLog log = getEmployeeLog(transferDept,user,"1");
 	    		StringBuffer[] sbForResult=checkFieldChange(em,transferDept);
 	    		if(sbForResult[0].length()>0){
 					employeeLogService.save(log);
 	    		}
 				
 	    		if(!employeeModifyService.modifyRM(transferDept)){
 	    			result = false;
 	    		}
 	        }catch(Exception e){
 	        	e.printStackTrace();
 	        }
    		
    	}
    	return result;
    }
    
    private EmployeeLog getEmployeeLog(TransferDept transferDept,User user,String type){
    	//修改前
    	Employee em = employeeService.queryEmployeeById(transferDept.getEmployeeId());
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    	Timestamp time = null;
    	try {
			time = new Timestamp(sdf.parse(sdf.format(new Date())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	EmployeeLog log = new EmployeeLog();
    	StringBuffer[] finalchangeInfo = null;
    	
        if(type.equals("1")){
        	finalchangeInfo = checkFieldChange(em, transferDept);
    	}
    	if(em!=null && transferDept !=null){
        	
            if(type.equals("1")){
            	log.setChangeInformation(finalchangeInfo[0].toString());
        	}
        	log.setEmployeeId(transferDept.getEmployeeId());
        	log.setLogId(Utils.getUUID());
            if(type.equals("1")){
            	log.setLogType("1");
        	}
            if(finalchangeInfo!=null){
            	log.setProjectStatus(finalchangeInfo[1].toString());
            	log.setContractStatus(finalchangeInfo[2].toString());
            	log.setLevelStatus(finalchangeInfo[3].toString());
            }
        	log.setOperationPerson(user.getUserId());
        	log.setUpdateDate(time);
    	}
    	return log;
    }
    
    private StringBuffer[] checkFieldChange(Employee before,TransferDept transferDept){
    	StringBuffer[] finalchangeInfo = new StringBuffer[4];
    	StringBuffer changeInfo = new StringBuffer();
    	String projectStatus = "";
    	String contractStatus = "";
    	String levelStatus = "";
    	if(before!=null && transferDept!=null){
        	
        	//HSBC Project Name
            if(transferDept.getProjectName()!=null && !transferDept.getProjectName().equals(before.getProjectName())){
            	changeInfo.append("ProjectName由["+before.getProjectName()+"]变更为["+transferDept.getProjectName()+"];   ");
            	changeInfo.append("----");
            	projectStatus="0";
            }
          //SOW#
            if(transferDept.getSowNum()!=null && !transferDept.getSowNum().equals(before.getSow())){
            	changeInfo.append("Sow由["+before.getSow()+"]变更为["+transferDept.getSowNum()+"];   ");
            	changeInfo.append("----");
            	contractStatus="0";
            }
            
          //SOW# Expired Date
            if(transferDept.getSowExpiredDate()!=null && !transferDept.getSowExpiredDate().equals(before.getSowExpiredDate())){
            	changeInfo.append("SowExpiredDate由["+before.getSowExpiredDate()+"]变更为["+transferDept.getSowExpiredDate()+"];   ");
            	changeInfo.append("----");
            	contractStatus="0";
            }
            
          //Engagement Type
            if(transferDept.getEngagementType()!=null && !transferDept.getEngagementType().equals(before.getEngagementType())){
            	changeInfo.append("EngagementType由["+before.getEngagementType()+"]变更为["+transferDept.getEngagementType()+"];   ");
            	changeInfo.append("----");
            }
            
            
          //MSA Role
            if(transferDept.getMsaRole()!=null && !transferDept.getMsaRole().equals(before.getRole())){
            	changeInfo.append("Role由["+before.getRole()+"]变更为["+transferDept.getMsaRole()+"];   ");
            	changeInfo.append("----");
            	levelStatus="0";
            }
        	//Skills/Technology
            if(transferDept.getSkill()!=null && !transferDept.getSkill().equals(before.getSkill())){
            	changeInfo.append("Skill由["+before.getSkill()+"]变更为["+transferDept.getSkill()+"];   ");
            	changeInfo.append("----");
            	levelStatus="0";
            }
            //Staff Region
            if(transferDept.getStaffRegion()!=null && !transferDept.getStaffRegion().equals(before.getStaffRegion())){
            	changeInfo.append("StaffRegion由["+before.getStaffRegion()+"]变更为["+transferDept.getStaffRegion()+"];   ");
            	changeInfo.append("----");
            }
            
        	//Bill Rate
            if(transferDept.getBillRate()!=null && !transferDept.getBillRate().equals(before.getBillRate())){
            	changeInfo.append("BillRate由["+before.getBillRate()+"]变更为["+transferDept.getBillRate()+"];   ");
            	changeInfo.append("----");
            	levelStatus="0";
            }
          
            
            //HSBC Sub Dept
            if(transferDept.getHsbcSubDept()!=null && !transferDept.getHsbcSubDept().equals(before.getHsbcSubDept())){
            	HSBCDept old = null;//hsbcDeptService.queryDemandHSBCSubDeptById(before.getHsbcSubDept());
            	HSBCDept news = null;//hsbcDeptService.queryDemandHSBCSubDeptById(transferDept.getHsbcSubDept());
            	String oldname="";
            	String newsname="";
            	if(old!=null){
            		//oldname=old.getHsbcSubDeptName();
            	}
            	if(news!=null){
            		//newsname=news.getHsbcSubDeptName();
            	}
            	changeInfo.append("HsbcSubDept由["+oldname+"]变更为["+newsname+"];   ");
            	changeInfo.append("----");
            	projectStatus="0";
            }
            //HSBC Project Manager
            if(transferDept.getHsbcManager()!=null && !transferDept.getHsbcManager().equals(before.getProjectManager())){
            	changeInfo.append("ProjectManager由["+before.getProjectManager()+"]变更为["+transferDept.getHsbcManager()+"];   ");
            	changeInfo.append("----");
            	projectStatus="0";
            }
            //rm
            if(transferDept.getRmUserId()!=null && !transferDept.getRmUserId().equals(before.getRmUserId())) {
            	changeInfo.append("RM由["+before.getNickname()+"]变更为["+transferDept.getNickName()+"];   ");
            	changeInfo.append("----");
            	
            }
   
    	}
    	finalchangeInfo[0]=changeInfo;
    	finalchangeInfo[1]=new StringBuffer(projectStatus);
    	finalchangeInfo[2]=new StringBuffer(contractStatus);
    	finalchangeInfo[3]=new StringBuffer(levelStatus);
    	
    	return finalchangeInfo;
    }
}