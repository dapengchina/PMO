package com.pmo.dashboard.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.Interviewer;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.InterviewerService;
import com.pom.dashboard.service.UserService;

@Controller
@RequestMapping(value="/interviewer")
public class InterviewerController
{
   
	private static Logger logger = LoggerFactory.getLogger(InterviewerController.class);
    
    @Resource
    private InterviewerService interviewerService;
    
    @Resource
    private UserService userService;
   
    @RequestMapping("/rm")
    public String getCandidate(final HttpServletRequest request,
            final HttpServletResponse response)
    {
    	return "rm/rm";
    }
    /*
     * 按照条件查询面试官和分页功能
     * */
 
    @RequestMapping("/queryInterviewerList")
    @ResponseBody
    public Object interviewerQuery(Interviewer interviewer, HttpServletRequest request)
    {
    	if("2年以下".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceEnd("2");
    	}else if("3-5年".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceStart("3");
    		interviewer.setExperienceEnd("5");
    	}else if("6-10年".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceStart("6");
    		interviewer.setExperienceEnd("10");
    	}else if("11年以上".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceStart("11");
    	}
    	if(interviewer.getCurrPage() == null || "".equals(interviewer.getCurrPage()))
    	{
    		interviewer.setCurrPage(1);
		}
    	User user = (User)request.getSession().getAttribute("loginUser");
    	interviewer.setEmployeeId(user.getUserId());
    	List<Interviewer> list = interviewerService.queryInterviewerList(interviewer,user);
    	if(null == list){
    		list = new ArrayList<Interviewer>();
    	}
    	for(int i=0;i<list.size();i++){
    	    
    	    //处理null ""
    	    if(list.get(i).getExperienceYearas()==null){
    	        list.get(i).setExperienceYearas("");
    	        continue;
    	    }
    	    
    		//获取毕业时间
    		String graduate_date=list.get(i).getExperienceYearas();
    		//将毕业时间转换成毫秒
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		String todayDate = df.format(new Date());
    		//将当前时间转换成毫秒
    		long nt;
    		long gt;
    		int year;
			try {
				gt = df.parse(graduate_date).getTime();
				nt = df.parse(todayDate).getTime();
				long cha = nt-gt;
			    year = (int) Math.floor(cha/ 1000 / 60 / 60 / 24/ 30 /12);
			 // 设置工作年限
	    		list.get(i).setExperienceYearas(String.valueOf(year));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		
    	}
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("list", list);
    	result.put("pageCondition", interviewer);
    	return result;
    }
    
    //更改面试官状态指定是否为面试官
    @RequestMapping("/interviewerRenDing")
    @ResponseBody
    public boolean updateInterviewer(final HttpServletRequest request,
            final HttpServletResponse response){
    	String employeeId = request.getParameter("employeeId");
    	String status = request.getParameter("status");
    	
    	Interviewer interviewers = interviewerService.selectInterviewer(employeeId);
    	
    	Interviewer interviewer = new Interviewer();
    	interviewer.setEmployeeId(employeeId);
    	User u= null;
    	boolean resultFlag = false;
    	
    	//取消面试官资格
    	if("1".equals(status)){
    		boolean flag = true;
    		User user = userService.checkUser(interviewers.getEhr());
    		if(user!=null) {
    			if("10".equals(user.getUserType())) {
    				user.setLoginStatus("1");//不可登录
    				flag = userService.update(user);
    			}
    		}
    		interviewer.setStatus("0");
    		resultFlag = interviewerService.update(interviewer);
    		
    		return resultFlag & flag;
    		
    	//授权面试官资格	
    	}else{
    		boolean flag0 = true;
    		boolean flag1 = true;
    		boolean flag2 = true;
    		//判断授权人是否在用户表
    		User user = userService.checkUser(interviewers.getEhr());
    		if(user!=null) {
    			//存在
    			user.setLoginStatus("0");//可登录
    			flag0 = userService.update(user);
	       		
    		}else {
    			//不存在
    			 u = new User();
	       		 u.setUserId(Utils.getUUID());
	       		 u.setUserName(interviewers.getEhr());
	       		 u.setNickname(interviewers.getStaffName());
	       		 u.setPassword("123");
	       		 u.setUserType("10");
	       		 u.setCsdeptId(interviewers.getCsSubDeptId());
	       		 u.setLoginStatus("0");//可登录
	       		 flag1 = userService.addUser(u);
    		}
    		interviewer.setStatus("1");//面试官
    		flag2 =  interviewerService.update(interviewer);
    		boolean flag = flag0 & flag1 & flag2;
    		return flag;
    	}

    }
  
}
