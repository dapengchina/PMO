package com.pmo.dashboard.entity;

import java.util.List;

/**
 * 候选人推送实体类
 * @author tianzhao
 *
 */
public class CandidatePush {

	private String pushId;
	
	private String candidateId;
	
	private String csSubDeptId;
	
	private String pushUserId;
	
	private String pushStatus;
	
	private String pushDate;
	
	/** 一对多关联面试表 */
	private List<CandidateInterview> interviewList;
	
	private CandidateInfo candidateInfo;
	
	private CSDept csDept;
	
	private User user;

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getCsSubDeptId() {
		return csSubDeptId;
	}

	public void setCsSubDeptId(String csSubDeptId) {
		this.csSubDeptId = csSubDeptId;
	}

	public String getPushUserId() {
		return pushUserId;
	}

	public void setPushUserId(String pushUserId) {
		this.pushUserId = pushUserId;
	}

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public String getPushDate() {
		return pushDate;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

	public CandidateInfo getCandidateInfo() {
		return candidateInfo;
	}

	public void setCandidateInfo(CandidateInfo candidateInfo) {
		this.candidateInfo = candidateInfo;
	}

	public CSDept getCsDept() {
		return csDept;
	}

	public void setCsDept(CSDept csDept) {
		this.csDept = csDept;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CandidatePush() {
		super();
	}

	public List<CandidateInterview> getInterviewList() {
		return interviewList;
	}

	public void setInterviewList(List<CandidateInterview> interviewList) {
		this.interviewList = interviewList;
	}

}
