package com.pmo.dashboard.controller;

//import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.pom.dashboard.service.PerformanceManagementService;

@Controller
@RequestMapping(value="/performanceManagement")
public class PerformanceManagementController {
	
	//private static Logger logger = LoggerFactory.getLogger(PerformanceManagementController.class);
	
//	@Resource
//    private PerformanceManagementService managementService;
	
//	@RequestMapping("/home")
//    public String performance(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/homexa";
//    }
	
//	@RequestMapping("/homeType")
//    public String homeType(final HttpServletRequest request,
//            final HttpServletResponse response,String status)
//    {
//    	if("1".equals(status)){
//    		 return "/performance/home/home1";
//    	}else if("2".equals(status)){
//    		 return "/performance/home/home2";
//    	}else if("3".equals(status)){
//    		 return "/performance/home/hrbphome";
//    	}else if("4".equals(status)){
//   		     return "/performance/home/lobhome";
//   	    }
//    	return "/performance/home/homexa";
//    }
	
//	@RequestMapping("/leftHRBP")
//    public String top(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/leftHRBP";
//    }
	
	
//	@RequestMapping("/approval")
//    public String approval(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/approval";
//    }
	
//	@RequestMapping("/template")
//    public String template(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/template";
//    }
		
//	@RequestMapping("/dataUpload")
//    public String dataUpload(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/dataUpload";
//    }
		
//	@RequestMapping("/lastPerformance")
//    public String lastPerformance(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/lastPerformance";
//    }
	
//	@RequestMapping("/perecords")
//    public String perecords(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/perecords";
//    }
	
//	@RequestMapping("/approvalPage")
//    public String approvalPage(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/approvalPage";
//    }
		
//	@RequestMapping("/historyDetail")
//    public String historyDetail(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/historyDetail";
//    }
	
//	@RequestMapping("/searchHistroyBtn")
//    public String searchHistroyBtn(final HttpServletRequest request,
//            final HttpServletResponse response)
//    {
//        return "/performance/home/searchHistroyBtn";
//    }
}
