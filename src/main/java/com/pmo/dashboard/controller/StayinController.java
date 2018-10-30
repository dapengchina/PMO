package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.pmo.dashboard.entity.AddDemand;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.StayinCandidate;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.DemandService;
import com.pom.dashboard.service.StayinService;

@Controller
@RequestMapping(value="/stayin")
public class StayinController
{
    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(StayinController.class);
    
    @Resource
    private StayinService StayinService;
    
    @Resource
	private CSDeptService csDeptService;
    
    @Resource
	private DemandService demandService;
    @RequestMapping("/stayin")
    public String stayin(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "/rm/stayin";
    }

    //查询列表
    @RequestMapping("/queryStayinList")
    @ResponseBody
    public Object queryStayinList(StayinCandidate candidate,HttpServletRequest request)
    {
    	 String pageState = candidate.getPageState();
    	 PageCondition page = new PageCondition();

    	 User user =  (User)request.getSession().getAttribute("loginUser");
    	 String userType = user.getUserType();
    	 String userId = user.getUserId();
    	 List<String>  csSubDeptNames = new ArrayList<String>();   
    	 String csSubDeptName = candidate.getCsSubDept();  
 	     List<CSDept> cSDepts= csDeptService.queryCSDeptByIds(user.getCsdeptId().split(","));
 	        
 	    if(cSDepts != null && !cSDepts.isEmpty()){        
 	       for (CSDept csDept : cSDepts) {
 	            csSubDeptNames.add(csDept.getCsSubDeptName());
 	       }
 	    }
 	   if(("".equals(candidate.getCsSubDept()) || candidate.getCsSubDept() == null) ){
           
           if("3".equals(userType)|| "4".equals(userType)|| "5".equals(userType)||"11".equals(userType)||"12".equals(userType)){
           	csSubDeptName = cSDepts.get(0).getCsSubDeptName(); 
           }
           
       }
 	   String  csSubdeptId=  csDeptService.changeCsSubDeptNameToId(csSubDeptName);
 	   candidate.setCsSubDept(csSubdeptId);
    	 if(null == userId || "".equals(userId))
     	{
     		return null;
     	}
		 int dataCount = StayinService.queryCandidateCount(candidate);
		 Integer pageSize = candidate.getPageSize();
		 
	     page.setDataCount(dataCount+"");
		 page.setPageCount((dataCount-1)/pageSize + 1 +"");
		 page.setPageDataCount(pageSize+"");
    	 if("".equals(pageState) || pageState == null ||"frist".equals(pageState)){
    		 page.setCurrentPage("1");
         }else if("next".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())+1+"");
         }else if("previous".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())-1+"");
         }else if("last".equals(pageState)){
        	 page.setCurrentPage(page.getPageCount());
         }
    	
    	 
    	candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage())-1)*pageSize+"");
    	candidate.setPageDataCount(pageSize+"");
    	int num = (Integer.valueOf(page.getCurrentPage())-1)*pageSize;
    	candidate.setNum(num);
        List<StayinCandidate> list = StayinService.queryStayinList(candidate);
        //加入session
        request.getSession().setAttribute("candidateList", list);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", list);
        result.put("pageInfo", page);
        result.put("csSubDeptNames", csSubDeptNames);
        result.put("user", user);
        result.put("csSubDeptName", csSubDeptName);
        return result;
    }
    
    
    @RequestMapping("/transformCandidateData")
    @ResponseBody
    public String transformCandidateData(StayinCandidate candidate,HttpServletRequest request)
    {
        String exportDataColumn = candidate.getExportDataColumn(); 
        if(exportDataColumn == null || "".equals(exportDataColumn))
        {
        	return "0";
        }
        if(exportDataColumn.lastIndexOf(",") == exportDataColumn.length()-1){
        	exportDataColumn = exportDataColumn.substring(0, exportDataColumn.length()-1);
        }
        candidate.setExportDataColumn(exportDataColumn);
        List<LinkedHashMap<String,String>> candidateDatalist = StayinService.queryExportData(candidate);
        request.getSession().setAttribute("candidate", candidate);
        request.getSession().setAttribute("candidateDatalist", candidateDatalist);
        return "1";
    }
    //查询模态窗口需求列表
    @RequestMapping("/queryDemandList")
    @ResponseBody
    public List<AddDemand> demandQuery(String candidateId,String demandId,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	//把传过来的保存到session中
    	 request.getSession().setAttribute("candidateId",candidateId);
    	 request.getSession().setAttribute("demandId", demandId);
    	 Demand demand = demandService.queryDemandById(demandId);
    	 
     	List<AddDemand> result = new ArrayList<>();
     	
     	if(demand!=null){
	    	String csSubdept = demand.getCsSubDept();
	    	 
	    	List<AddDemand> list = StayinService.queryDemand();
	    	for (AddDemand addDemand : list) {
				if(addDemand.getCsSubDept().equals(csSubdept)){
					result.add(addDemand);
				}
			}
     	}
     	
    	return result;
    }
    	
    //更改候选人对应的需求（选择之后）
    @RequestMapping("/UpdateDemand")
    @ResponseBody
    public Object updateDemand(final HttpServletRequest request,
            final HttpServletResponse response){
       /* String candidateId = request.getParameter("candidateId");*/
        String demandId =request.getParameter("demandId");
    	AddDemand d = new AddDemand();
    	d.setDemandId(demandId);
    	String candidateId = (String) request.getSession().getAttribute("candidateId");
    	d.setCandidateId(candidateId);
    	
    	boolean resultFlag = StayinService.updateDemand(d);
    	//变更之前的需求需设置为null
    	AddDemand d1 = new AddDemand();
    	String demandId2 = (String) request.getSession().getAttribute("demandId");
    	d1.setDemandId(demandId2);
    	boolean resultFlag2 = StayinService.updateDemandAfter(d1);
    	
    	boolean flag = resultFlag & resultFlag2;
    	return flag;
    
    }
    
    //更改候选人之后变更自己本省对应的需求为null
    
    //导出表格
    @RequestMapping("/exportExcel")
    @ResponseBody
    public HttpServletResponse exportExcel(HttpServletRequest request,HttpServletResponse response)
    {
    	StayinCandidate candidate = (StayinCandidate) request.getSession().getAttribute("candidate");
        if(candidate == null)
        {
        	return null;
        }
        @SuppressWarnings("unchecked")
        List<LinkedHashMap<String,String>> candidateDatalist = (List<LinkedHashMap<String,String>>) request.getSession().getAttribute("candidateDatalist");
        
        List<String> conditionList = Arrays.asList(candidate.getExportPageColumn().split(","));
        
        try {
        	  String tempfileName =  Constants.PATH+""+Utils.getUUID()+".xls";
              
              // 创建可写入的Excel工作簿           
              File file=new File(tempfileName);
              if (!file.exists()) {
                  file.createNewFile();
              }
              //写Excel
              StayinService.transferExportData(candidateDatalist,conditionList,file);
            //获取当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = df.format(new Date());
            String fileName = "PMO_Stayin_Info_"+date+".xls";

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(tempfileName));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
            //response.setContentType("application/octet-stream");
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            
           file.delete();         
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    
    
}
