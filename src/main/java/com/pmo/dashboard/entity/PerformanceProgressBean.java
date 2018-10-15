package com.pmo.dashboard.entity;

public class PerformanceProgressBean {
	//流程名称 
	String progressName;
	//审核人
	String auditor;	
	//状态
	String status;	
	String comments;

	
	public PerformanceProgressBean(String progressName, String auditor, String status, String comments) {
		super();
		this.progressName = progressName;
		this.auditor = auditor;
		this.status = status;
		this.comments = comments;
	}
	public String getProgressName() {
		return progressName;
	}
	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "PerformanceProgressBean [progressName=" + progressName + ", auditor=" + auditor + ", status=" + status
				+ ", comments=" + comments + "]";
	}
	
	
	
}
