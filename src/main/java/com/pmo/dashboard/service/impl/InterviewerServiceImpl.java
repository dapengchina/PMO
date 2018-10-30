package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.InterviewerMapper;
import com.pmo.dashboard.entity.Interviewer;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.InterviewerService;

@Service
@Transactional
public class InterviewerServiceImpl implements InterviewerService
{

    @Resource
    private InterviewerMapper interviewerMapper;
    
	
	


	@Override
	public List<Interviewer> queryAllInterviewer(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Interviewer> list = interviewerMapper.queryAllInterviewer(map);
		return list;
	}

    //查询根据条件的面试官信息
	@Override
	public List<Interviewer> queryInterviewerList(Interviewer interviewer,User user)
	{
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("interviewer", interviewer);
		int num;
		//计算当前页起始号
		if(interviewer.getPageRecNum()!=null){
			System.out.println("页数======================"+interviewer.getCurrPage());
			num = (interviewer.getCurrPage() - 1)*interviewer.getPageRecNum();
			map.put("num", num);
			interviewer.setPageSize(interviewer.getPageRecNum());
		}else{
			num = (interviewer.getCurrPage() - 1)*interviewer.getPageSize();
			map.put("num", num);
		}
		
		
		List<Interviewer> list = null;
		//String employeeId = interviewer.getEmployeeId();
		//User user = this.selectUser(employeeId);
		String userType = null;
		if(user!=null){
			userType = user.getUserType();
			//userType = "1";
		}
		//设置总页数
		int queryInterviewerCount = 0;
		if("1".equals(userType)){
			list = interviewerMapper.queryInterviewerListByBu(map);
			queryInterviewerCount = interviewerMapper.queryInterviewerCountByBu(map);
		}else if("3".equals(userType) || "5".equals(userType) ||
				"11".equals(userType) || "12".equals(userType) ||
				"13".equals(userType) || "14".equals(userType)){
			list = interviewerMapper.queryInterviewerListByDept(map);
			queryInterviewerCount = interviewerMapper.queryInterviewerCountByDept(map);
		}else if("0".equals(userType)){
			list = interviewerMapper.queryInterviewerList(map);
			queryInterviewerCount = interviewerMapper.queryInterviewerCount(map);
		}
		if(interviewer.getPageRecNum()!=null){
			interviewer.setTotalPage((int) Math.ceil(queryInterviewerCount*1.0 / interviewer.getPageRecNum()));
		}else{
			interviewer.setTotalPage((int) Math.ceil(queryInterviewerCount*1.0 / interviewer.getPageSize()));
		}
		return list;
	}

	@Override
	public boolean interviewerRenDing(Interviewer interviewer) 
	{
		
		boolean flag1 = interviewerMapper.interviewerRenDing(interviewer);
		
		return flag1;
	
		/*if(flag1)
		{
			return interviewerMapper.insertInterviewerToUser(interviewer);
		}	
		return false;*/
	}

	@Override
	public boolean insertInterviewerToUser(Interviewer interviewer) {
		// TODO Auto-generated method stub
		if(interviewerMapper.insertInterviewerToUser(interviewer)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean update(Interviewer interviewer) {
		// 调用dao层
		if(interviewerMapper.update(interviewer)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Interviewer selectInterviewer(String employeeId) {
		Interviewer interviewer = interviewerMapper.select(employeeId);
		return interviewer;
	}

	@Override
	public User selectUser(String employeeId) {
		User user =interviewerMapper.selectUser(employeeId);
		return user;
	}
    
     

	}

