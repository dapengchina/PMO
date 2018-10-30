package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.CandidateInterview;
import com.pmo.dashboard.entity.CandidatePush;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.User;

/**
 * RM的候选人的sevice类
 * @author tianzhao
 *
 */
public interface RmCandidateService {

	public List<CandidatePush> queryPushedCandidate(User user, String status,PageCondition pageCondition);

	public void addInterview(CandidateInterview candidateInterview,String pushId);
	
	public void addNextInterview(CandidateInterview candidateInterview,String pushId);
	public boolean updateCSinfo(String pushId);
	public void interviewBack(String pushId);
	public CandidateInterview getIntervieInfo(String candidateId);
	public boolean updateInterview(CandidateInterview candidateInterview);
	public CandidateInterview getConfirminfo(String candidateId);
	
	List<CandidatePush> getPushUser(String pushid);
	
}
