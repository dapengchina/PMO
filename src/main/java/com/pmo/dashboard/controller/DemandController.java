package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.QueryModel;
import com.pmo.dashboard.entity.StayinCandidate;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.CandidateService;
import com.pom.dashboard.service.DemandService;
import com.pom.dashboard.service.HSBCDeptService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 招聘需求的controller
 * 
 * @author tianzhao
 * @version1.0 2017-8-25 14:54:57
 */
@Controller
@RequestMapping(value="/demand")
public class DemandController {
	
	@Resource
	DemandService demandService;
	
	@Resource
    private CandidateService candidateService;
	
	@Resource
	HSBCDeptService hsbcDeptService;
	
	@Resource
	CSDeptService csDeptService;
	
	private ObjectMapper objectMapper = new ObjectMapper();  

	private static Logger logger = LoggerFactory.getLogger(DemandController.class);
	
	@RequestMapping("/demandInfo")
	public String demandInfo(){
		return "/demand/demandQuery";
	}
	
	//gkf
	@RequestMapping("/demandList")
	public String demandListForCandidate(){
		return "/demand/demandListForCandidate";
	}
	
	/**
	 * 背调维护 批量修改
	 * gkf
	 */
	@RequestMapping("/updateBackForCandidate")
	@ResponseBody
	public  boolean  updateBackForCandidate(String candidateIds,String bgvCleared) {
		String [] cadidateIdsArr = candidateIds.split(",");
		Demand demand = new Demand();
		boolean result = false;
		for(String candidateId : cadidateIdsArr) {
			demand.setCandidateId(candidateId);
			demand.setBgvCleared(bgvCleared);
			result = demandService.updateBackForCandidate(demand);
			if(!result) {
				break;
			}
		}
		return result;
	}
	/**
	 * 加载Department信息
	 * @return
	 */
	@RequestMapping("/loadDepartment")
	@ResponseBody
	public List<HSBCDept> loadDepartment(){
		//List<HSBCDept> list = hsbcDeptService.queryHSBCDeptName();
		return null;
	}
	
	/**
	 * 加载SubDepartment信息
	 * @param hsbcDeptName
	 * @return
	 */
	@RequestMapping("/loadSubDepartment")
	@ResponseBody
	public List<HSBCDept> loadSubDepartment(String hsbcDeptName){
		//List<HSBCDept> list = hsbcDeptService.queryHSBCSubDeptNameByDeptName(hsbcDeptName);
		return null;
	}
	
	/**
	 * 加载交付部信息
	 * @param csBuName
	 * @return
	 */
	@RequestMapping("/loadScSubDeptName")
	@ResponseBody
	public List<CSDept> loadScSubDeptName(String csBuName){
		List<CSDept> list = csDeptService.queryCSSubDeptNameByCsBuName(csBuName);
		return list;
	}
	
	/**
	 * 获取需求信息
	 * @throws JsonProcessingException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getDemand/{id}")
	@ResponseBody
	public String getDemand(int pageSize,int pageNumber,String rr,String jobcode,String skill,@PathVariable("id") String id,HttpServletRequest request) throws JsonProcessingException{
		//获取当前登录用户
		User user = (User) request.getSession().getAttribute("loginUser");
		//获取用户类型
		String usertype = user.getUserType();
		//如果不是管理员,则查询对应交付部，open的需求
		String[] temp = null;
		List tempList = null;
		if(!usertype.equals("0")){
			if(user.getCsdeptId()!=null && !"".equals(user.getCsdeptId())){
				if(user.getCsdeptId().indexOf(",")!=-1){
					temp=user.getCsdeptId().split(",");
				}else{
					temp = new String[1];
					temp[0] = user.getCsdeptId();
				}
			}
		}
		if(temp!=null){
			tempList = new ArrayList();
			for(int k=0;k<temp.length;k++){
				tempList.add(temp[k]);
			}
		}
		
		QueryModel qm = new QueryModel();
		qm.setCsdeptids(tempList);
		qm.setStatus("Open");
		qm.setRr(rr);
		qm.setJobcode(jobcode);
		if(id.equals("1")){
			qm.setType("Time&Material");
		}
        if(id.equals("2")){
        	qm.setType("Fixed Price");
		}
        if(id.equals("3")){
        	qm.setType("Support");
        }
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize);  
		List<Demand> list = demandService.getDemand(qm);
		/**
		 * 处理交付部名称
		 */
		for(int i=0;i<list.size();i++){
			CSDept csdept = csDeptService.queryCSDeptById(list.get(i).getCsSubDept());
			if(csdept!=null){
				list.get(i).setCsDeptName(csdept.getCsSubDeptName());
			}
		}
		Map map = new HashMap();
		PageInfo<Demand> page = new PageInfo(list);
		map.put("total",page.getTotal());
		map.put("rows", list);
		return objectMapper.writeValueAsString(map);
	}
	
	
	
	/**
	 * 按条件查询招聘需求和分页功能
	 * @param demand
	 * @param pageCondition
	 * @return
	 */
	@RequestMapping("/queryDemandList")
	@ResponseBody
	public Object demandQuery(String csBuName,Demand demand,PageCondition pageCondition,HttpServletRequest request){
		if("".equals(pageCondition.getCurrPage()) || pageCondition.getCurrPage() == null){
			pageCondition.setCurrPage(1);
			request.getSession().setAttribute("demandCondition", demand);
			request.getSession().setAttribute("demandBUCondition", csBuName);
		}
		
		
		if(!("".equals(pageCondition.getCurrPage()) || pageCondition.getCurrPage() == null)){
			csBuName = (String) request.getSession().getAttribute("demandBUCondition");
			demand = (Demand) request.getSession().getAttribute("demandCondition");
		}
		//String csBuName = request.getParameter("csBuName");
		  User user = (User) request.getSession().getAttribute("loginUser");
		  
	        
	    List<String>  csSubDeptNames = new ArrayList<String>();   
	    String csSubDeptName = demand.getCsSubDept();
	        
	    List<CSDept> cSDepts= csDeptService.queryCSDeptByIds(user.getCsdeptId().split(","));
	        
	    if(cSDepts != null && !cSDepts.isEmpty()){        
	       for (CSDept csDept : cSDepts) {
	            csSubDeptNames.add(csDept.getCsSubDeptName());
	       }
	    }
		  
		  
	     String userType = user.getUserType();
	     
	     String[] csBuNames = null;
			if (user.getBu() != null && user.getBu() != "") {
	    		csBuNames = user.getBu().split(",");
	    	}
	     
		 if(("".equals(demand.getCsSubDept()) || demand.getCsSubDept() == null) &&
	                ("".equals(csBuName) || csBuName == null)){
	            
	            if("1".equals(userType)|| "2".equals(userType)|| "3".equals(userType)|| "4".equals(userType)||"5".equals(userType)||"11".equals(userType)||"12".equals(userType)){
	                csBuName = csBuNames[0];
	            }
	            
	            if("3".equals(userType)|| "4".equals(userType)|| "5".equals(userType)||"11".equals(userType)||"12".equals(userType)){
	            	csSubDeptName = cSDepts.get(0).getCsSubDeptName(); 
	            }
	            
	        }
		 
		String  csSubdeptId=  csDeptService.changeCsSubDeptNameToId(csSubDeptName);
		String storedcs=demand.getCsSubDept();
		demand.setCsSubDept(csSubdeptId);
		
		Integer pageSize = 10;
		
		if(request.getParameter("pageRecordsNum")!=null){
		
 		 pageSize = Integer.parseInt(request.getParameter("pageRecordsNum"));
 		 
		}
		
		pageCondition.setPageSize(pageSize);
		
		List<Demand> list = demandService.queryDemandList(demand,pageCondition,csBuName,request);
		
		Map<String,Object> result = new HashMap<String,Object>();
		demand.setCsSubDept(storedcs);
		result.put("csBuName", csBuName);
		result.put("csSubDeptName", csSubDeptName);
		result.put("list", list);
	    result.put("user", user);
		result.put("pageCondition", pageCondition);
		result.put("csSubDeptNames", csSubDeptNames);
        result.put("csBuNames", csBuNames);
		return result;
	}
	
	/**
	 * 导出查询到的需求信息
	 * @param condition
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/exportExcel")
	public HttpServletResponse exportExcel(String condition,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> params = (Map<String, Object>)request.getSession().getAttribute("demandParams");
		List<Demand> demandList = demandService.queryAllDemand(params);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			
			String fileName =  Constants.PATH+""+Utils.getUUID()+".xls";
			// 创建可写入的Excel工作簿
			
			File file = new File(fileName);
		    File fileDir =new File(Constants.PATH);
		    if(!fileDir.exists()||!fileDir.isDirectory())
			    {
		    	fileDir.mkdir();
			    }
			if(!file.exists()){
			
				file.createNewFile();
			}
			//以fileName为文件名来创建一个Workbook
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			// 创建工作表
			WritableSheet ws = wwb.createSheet("Demand Tracker", 0);
			//List<Demand> list = demandService.queryAllDemand(demand,csBuName);
			//List<Demand> demandList = (List<Demand>)request.getSession().getAttribute("demandList");
			String[] array = condition.split(",");
			for (int i = 0;i < array.length;i++) {
				Label label = new Label(i, 0, array[i]);
				ws.addCell(label);
			}
			for (int i =0 ; i < demandList.size(); i++) {
				int j = 0;
				if(condition.indexOf("RR #")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getRr());
					ws.addCell(lab);
				}
				System.out.println("=="+demandList.get(i).getRr());
				if(condition.indexOf("Job Code")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getJobCode());
					ws.addCell(lab);
				}
				if(condition.indexOf("Tech/Skill")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getSkill());
					ws.addCell(lab);
				}
				if(condition.indexOf("Requestor")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getRequestor());
					ws.addCell(lab);
				}
				if(condition.indexOf("Position")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getPosition());
					ws.addCell(lab);
				}
				if(condition.indexOf("Department")!= -1){
				    Label lab = null;
				    if(demandList.get(i).getHsbcDeptName() == null){
				        lab = new Label(j++, i+1, "");
				    }else{
				        lab = new Label(j++, i+1, demandList.get(i).getHsbcDeptName());
				    }
					ws.addCell(lab);
				}
				if(condition.indexOf("Sub - Department")!= -1){
				    Label lab = null;
                    if(demandList.get(i).getHsbcSubDeptName() == null){
                        lab = new Label(j++, i+1, "");
                    }else{
                        lab = new Label(j++, i+1, demandList.get(i).getHsbcSubDeptName());
                    }
					ws.addCell(lab);
				}
				if(condition.indexOf("Location")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getLocation());
					ws.addCell(lab);
				}
				if(condition.indexOf("Req published Date")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getReqPublishedDate());
					ws.addCell(lab);
				}
				if(condition.indexOf("Ageing")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getAgeing());
					ws.addCell(lab);
				}
				if(condition.indexOf("No. of Profiles Sent to HSBC")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getProfilesNo());
					ws.addCell(lab);
				}
				if(condition.indexOf("No of Profiles Interviewed")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getInterviewedNo());
					ws.addCell(lab);
				}
				if(condition.indexOf("Status")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getStatus());
					ws.addCell(lab);
				}
				if(condition.indexOf("Candidate Name")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getCandidateName());
					ws.addCell(lab);
				}
				if(condition.indexOf("Proposed Date of Joining")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getProposedJoiningDate());
					ws.addCell(lab);
				}
				if(condition.indexOf("SOW signed")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getSowSigned());
					ws.addCell(lab);
				}
				if(condition.indexOf("BGV Cleared")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getBgvCleared());
					ws.addCell(lab);
				}
				
				if(condition.indexOf("Reason for Abort / Delay")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getReason());
					ws.addCell(lab);
				}
				if(condition.indexOf("Remark")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getRemark());
					ws.addCell(lab);
				}
				if(condition.indexOf("CsDept")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getCsSubDept());
					ws.addCell(lab);
				}
				if(condition.indexOf("Planned Onboard date")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getPlannedOnboardDate());
					ws.addCell(lab);
				}
				if(condition.indexOf("DO number")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getDoNumber());
					ws.addCell(lab);
				}
				if(condition.indexOf("HR Priority")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getHrPriority());
					ws.addCell(lab);
				}
				if(condition.indexOf("Completion Day")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getCompletionDay());
					ws.addCell(lab);
				}
				if(condition.indexOf("Onboard Date")!= -1){
					Label lab = new Label(j++, i+1, demandList.get(i).getOnboardDate());
					ws.addCell(lab);
				}
			}
			//写入数据
			wwb.write();
			//关闭文件
			wwb.close();
			
			String filename = "GSV Engagement Dashboard_"+date+".xls";

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(fileName));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" +filename);
            //response.setContentType("application/octet-stream");
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            
           file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 需求详情页面信息
	 * @return
	 */
	@RequestMapping("/demandDetail")
	public String demandDetail(String demandId,String engagementType,String statusa,Model model,HttpServletRequest request){
		
	    //Demand demand = demandService.queryDemandById(demandId);
		try{
			@SuppressWarnings("unchecked")
			List<Demand> list = (List<Demand>) request.getSession().getAttribute("demandList");
			long day = 0;
			for (Demand demand : list) {
				if(demand.getDemandId().equals(demandId)){
					String temp[] = null;
					//获取GBGF
					if(demand.getHsbcSubDeptId()!=null && !"".equals(demand.getHsbcSubDeptId())){
						temp = demand.getHsbcSubDeptId().split(",");
						HSBCDept hsbcDept = new HSBCDept();
						hsbcDept.setId(temp[0]);
						List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);
						if(hsbcDeptList!=null && hsbcDeptList.size()>0){
							demand.setGbgf(hsbcDeptList.get(0).getName());
						}
					}
					//获取HSBCDept
					if(temp!=null && temp.length>1){
						HSBCDept hsbcDept = new HSBCDept();
						hsbcDept.setId(temp[1]);
						List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);
						if(hsbcDeptList!=null && hsbcDeptList.size()>0){
							demand.setHsbcDeptName(hsbcDeptList.get(0).getName());
						}
					}
					//获取HSBCSubDept
					if(temp!=null && temp.length>2){
						HSBCDept hsbcDept = new HSBCDept();
						hsbcDept.setId(temp[2]);
						List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);
						if(hsbcDeptList!=null && hsbcDeptList.size()>0){
							demand.setHsbcSubDeptName(hsbcDeptList.get(0).getName());
						}
					}
					
					
					//ageing计算
					if(demand.getStatus().equals("Onboard")){
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						if(demand.getOnboardDate()!=null && demand.getReqPublishedDate()!=null){
							Date d1 = sf.parse(demand.getOnboardDate());
							Date d2 = sf.parse(demand.getReqPublishedDate());
							day=(d1.getTime()-d2.getTime())/(24*60*60*1000);
							demand.setAgeing(String.valueOf(day));
						}
						
						//计算发送简历和面试人数
						if(demand.getCandidateId()!=null){
							int i = candidateService.getCount(demand.getCandidateId());
							demand.setProfilesNo(String.valueOf(i));
							demand.setInterviewedNo(String.valueOf(i));
						}
					}
					
					
					model.addAttribute("demand", demand);
					if("1".equals(engagementType)){
						return "/demand/demandDetailfp";
					}else if("2".equals(engagementType)){
						return "/demand/demandDetailSupport";
					}else{
						return "/demand/demandDetailtm";
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "/demand/demandDetailtm";
	}
	
	/**
	 * 员工入场需求页面信息展示
	 * gkf
	 * @return
	 */
	@RequestMapping("/demandOnboard")
	public String demandOnboard(String candidateId,Model model,HttpServletRequest request,String type){
		
		Employee em = new Employee();
		@SuppressWarnings("unchecked")
		List<StayinCandidate> list = (List<StayinCandidate>) request.getSession().getAttribute("candidateList");
		Demand demand = demandService.queryDemandByCandidateId(candidateId);
    	//拿到需求和待入职的人员信息  gkf
    	if(list!=null &&list.size()>0&&candidateId!=null) {
    		for(StayinCandidate candidate :list ) {
    			if(candidate.getCandidateId().equals(candidateId)) {
    				em.setStaffName(candidate.getCandidateName());
    				//em.setBillRate();
    				em.setGraduationDate(candidate.getGraduateDate());
    				em.setCsSubDept(candidate.getCsSubDept());
    				em.setEntryDate(candidate.getEntyDate());
    				em.setEmail(candidate.getEmail());
    				
    				if(demand!=null) {
    					HSBCDept hSBCDept = null; //hsbcDeptService.queryDemandHSBCSubDeptById(demand.getHsbcSubDeptId());
    					if(hSBCDept!=null) {
//    						if(hSBCDept.getHsbcSubDeptName()==null||"".equals(hSBCDept.getHsbcSubDeptName())) {
//    							hSBCDept.setHsbcSubDeptName(hSBCDept.getHsbcDeptName());
//    						}
    					}
    					demand.setHsbcDept(hSBCDept);
    					demand.setProposedJoiningDate(candidate.getArrivalDate());
    					em.setStaffRegion(demand.getLocation());
    					em.setStaffLocation("China");
    					em.setSow(demand.getDoNumber());
    					em.setRole(demand.getPosition());
    					em.setSkill(demand.getSkill());
    					em.setResourceStatus("Active");
    				}
    				model.addAttribute("employee", em);
    			    model.addAttribute("demand", demand);
    				break;
    			}
    		}
    	}
		
		if("2".equals(type)) {
			return "/demand/demandOnboard2";
		}else if("3".equals(type)) {
			return "/demand/demandOnboard3";
		}else {
			return "/demand/demandOnboard";
		}
		
	}
	
	/**
	 * gkf 
	 * 员工入场后相应需求信息修改
	 * @param requset
	 * @param demand
	 * @return
	 */
	@RequestMapping("/updateDemandOnBoardById")
	@ResponseBody
	public boolean updateDemandOnBoardById(Demand demand ,HttpServletRequest request) {
		request.getSession().setAttribute("onboardCandidateId", demand.getCandidateId());
		return demandService.updateDemandOnBoardById(demand);
	}
	
	/**
	 * add by jama 
	 * 查询全部交付部
	 * @param csBuName
	 * @return
	 */
	@RequestMapping("/loadAllScSubDeptName")
	@ResponseBody
	public List<CSDept> loadAllScSubDeptName(){
		List<CSDept> list = csDeptService.queryAllCSSubDeptName();
		return list;
	}
	
	/**
	 * 编辑不全需求信息
	 * add by jama
	 * @param demandId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/demandDetailUpdate")
	public String demandDetailUpdate(String demandId,Model model,String userType,HttpServletRequest request,String engagementType){
		try{
			List<Demand> list = (List<Demand>) request.getSession().getAttribute("demandList");
			long day = 0;
			HSBCDept hsbcdept = new HSBCDept();
			for (Demand demand : list) {
				if(demand.getDemandId().equals(demandId)){
					String[] temp = null;
					
					if(demand.getHsbcSubDeptId()!=null && !"".equals(demand.getHsbcSubDeptId())){
						temp = demand.getHsbcSubDeptId().split(",");
						//处理GBGF
						hsbcdept.setId(temp[0]);
						List<HSBCDept> hsbcdeptlist = hsbcDeptService.queryById(hsbcdept);
						if(hsbcdeptlist!=null && hsbcdeptlist.size()>0){
							demand.setGbgf(hsbcdeptlist.get(0).getName());
							demand.setGbgfid(hsbcdeptlist.get(0).getId());
						}
						//处理HSBCDept
						if(temp.length>1){
							hsbcdept.setId(temp[1]);
							List<HSBCDept> hsbcdeptlist2 = hsbcDeptService.queryById(hsbcdept);
							if(hsbcdeptlist2!=null && hsbcdeptlist2.size()>0){
								demand.setHsbcDeptName(hsbcdeptlist2.get(0).getName());
								demand.setHsbcdeptid(hsbcdeptlist2.get(0).getId());
							}
						}
						//处理HSBCSubDept
						if(temp.length>2){
							hsbcdept.setId(temp[2]);
							List<HSBCDept> hsbcdeptlist3 = hsbcDeptService.queryById(hsbcdept);
							if(hsbcdeptlist3!=null && hsbcdeptlist3.size()>0){
								demand.setHsbcSubDeptName(hsbcdeptlist3.get(0).getName());
								demand.setHsbcsubdeptid(hsbcdeptlist3.get(0).getId());
							}
						}
					}
					//HSBCDept hSBCDept = null; //hsbcDeptService.queryDemandHSBCSubDeptById(demand.getHsbcSubDeptId());
					//if(hSBCDept!=null) {
//						if(hSBCDept.getHsbcSubDeptName()==null||"".equals(hSBCDept.getHsbcSubDeptName())) {
//							hSBCDept.setHsbcSubDeptName(hSBCDept.getHsbcDeptName());
//						}
					//}
					//demand.setHsbcDept(hSBCDept);
					demand.setUserType(userType);
					
					//ageing计算
					if(demand.getStatus().equals("Onboard")){
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						if(demand.getOnboardDate()!=null && demand.getReqPublishedDate()!=null){
							Date d1 = sf.parse(demand.getOnboardDate());
							Date d2 = sf.parse(demand.getReqPublishedDate());
							day=(d1.getTime()-d2.getTime())/(24*60*60*1000);
							demand.setAgeing(String.valueOf(day));
						}
						
						//计算发送简历和面试人数
						if(demand.getCandidateId()!=null){
							int i = candidateService.getCount(demand.getCandidateId());
							demand.setProfilesNo(String.valueOf(i));
							demand.setInterviewedNo(String.valueOf(i));
						}
					}
					
					model.addAttribute("demand", demand);
					if("1".equals(engagementType)){
						return "/demand/demandDetailEditFixedprice";
					}else if("2".equals(engagementType)){
						return "/demand/demandDetailEditSupport";
					}else{
						return "/demand/demandDetailEdit";
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/demand/demandDetailEdit";
	}
}
