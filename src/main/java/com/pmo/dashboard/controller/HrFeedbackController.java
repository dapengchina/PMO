package com.pmo.dashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidateInterview;
import com.pmo.dashboard.entity.HrFeedback;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CandidateService;
import com.pom.dashboard.service.HrFeedbackService;

@Controller
@RequestMapping(value="/hrfeedback")
public class HrFeedbackController
{
    @Resource
    private HrFeedbackService hrFeedbackservice;
    
    @Resource 
    private CandidateService candidateService;
    
    @RequestMapping("/hrFinalFeedBack")
    @ResponseBody
    public boolean candidateFeedback(String candidateId,String hrFeedBack, HttpServletRequest request){
    	HrFeedback hrFeedback =new HrFeedback();
    	hrFeedback.setFeedbackId(Utils.getUUID());
    	hrFeedback.setCandidateId(candidateId);
    	hrFeedback.setHrFeedback(hrFeedBack);
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String dateString = formatter.format(new Date());
    	hrFeedback.setFeedbacktime(dateString);
    	hrFeedback.setUserId(((User)request.getSession().getAttribute("loginUser")).getUserId());
		boolean result=hrFeedbackservice.updateCandidateInfo(hrFeedback);
		if(result){
			return true;
			
		}else{
			return false;
		}
		
    }
    @RequestMapping("/hrfeedbackQuery")
    @ResponseBody
    public List<HrFeedback>  hrfeedbackQuery(String candidateId, HttpServletRequest request){
    	List<HrFeedback> list=hrFeedbackservice.hrfeedbackQuery(candidateId);
    	
    	return hrFeedbackservice.hrfeedbackQuery(candidateId);
    }
    
    @RequestMapping("/confirmInterviewDate")
    @ResponseBody
    public Boolean confirmInterviewDate(final HttpServletRequest request){
		String confirmDateType = request.getParameter("confirmDateType");
		String mark = request.getParameter("newDate");
		String interviewId = request.getParameter("interviewId");
		String candidateId = request.getParameter("candidateId");
		CandidateInfo candidateInfo = candidateService.queryCandidateForId(candidateId);
		CandidateInterview candidateInterview = candidateService.queryCandidateInterviewById(interviewId);
		boolean confirmStatus = true;
		if (candidateInfo != null && candidateInfo.getInterviewStatus().equals("6")) {
			if (confirmDateType.equals("确认") ) {
				candidateInfo.setInterviewStatus("2");
				candidateInterview.setMark("");
				candidateInterview.setConfirmStatus("0");
				confirmStatus = candidateService.updateConfirmStatus(candidateInterview);
			} else if (confirmDateType.equals("取消")){
				candidateInfo.setInterviewStatus("7");
				candidateInterview.setMark(mark);
				candidateInterview.setConfirmStatus("1");
			}
		}
		
		candidateInfo.setCandidateStatus("0");
		
		boolean updateMark = candidateService.updateCandidateInterviewMark(candidateInterview);

		boolean updateStatus = candidateService.updateCandidateInterviewStatus(candidateInfo);
		
		return confirmStatus && updateMark && updateStatus;
    }
}
