package com.pmo.dashboard.entity;

/**
 * 候选人面试实体类
 * @author tianzhao
 *
 */
public class CandidateInterview {

	private String interviewId;
	
	private String candidateId;
	
	private String interviewerId;
	
	private String feedback;
	
	private String interviewStatus;
	
	private String interviewDate;
	
	private String cssubDept;
	
	private String interviewType;
	
	private String fatherInterviewId;
	
	private String interviewSerial;
	
	private String projectName;
	
	private String mark;
	
	private String confirmStatus;

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(String interviewerId) {
		this.interviewerId = interviewerId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getCssubDept() {
		return cssubDept;
	}

	public void setCssubDept(String cssubDept) {
		this.cssubDept = cssubDept;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	public String getFatherInterviewId() {
		return fatherInterviewId;
	}

	public void setFatherInterviewId(String fatherInterviewId) {
		this.fatherInterviewId = fatherInterviewId;
	}

	public String getInterviewSerial() {
		return interviewSerial;
	}

	public void setInterviewSerial(String interviewSerial) {
		this.interviewSerial = interviewSerial;
	}
	
}
