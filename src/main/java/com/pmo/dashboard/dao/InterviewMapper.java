package com.pmo.dashboard.dao;

import java.util.Map;

import com.pmo.dashboard.entity.CandidateInterview;

public interface InterviewMapper {

	public void addInterview(CandidateInterview candidateInterview);
	
	public CandidateInterview queryNewInterviewByCandidateId(Map<String, Object> params);
	public CandidateInterview getIntervieInfo(String candidateId);
	public boolean updateInterview(CandidateInterview candidateInterview);
	public CandidateInterview getConfirminfo(String candidateId);
	
	public CandidateInterview getInteviewer(Map<String,Object> map);
}
