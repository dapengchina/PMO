package com.pmo.dashboard.entity;

public class PerformanceManageTargetBean {

	private String ehr;
	private String empName;
	private String role;
	private String skill;
	private String isSubmitted;
	private String pioneer;
	private String approveStatus;
	public String getEhr() {
		return ehr;
	}
	public void setEhr(String ehr) {
		this.ehr = ehr;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getIsSubmitted() {
		return isSubmitted;
	}
	public void setIsSubmitted(String isSubmitted) {
		this.isSubmitted = isSubmitted;
	}
	public String getPioneer() {
		return pioneer;
	}
	public void setPioneer(String pioneer) {
		this.pioneer = pioneer;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public PerformanceManageTargetBean(String ehr, String empName, String role, String skill, String isSubmitted,
			String pioneer, String approveStatus) {
		super();
		this.ehr = ehr;
		this.empName = empName;
		this.role = role;
		this.skill = skill;
		this.isSubmitted = isSubmitted;
		this.pioneer = pioneer;
		this.approveStatus = approveStatus;
	}
	public PerformanceManageTargetBean() {
		super();
	}
	@Override
	public String toString() {
		return "PerformanceManageTargetBean [ehr=" + ehr + ", empName=" + empName + ", role=" + role + ", skill="
				+ skill + ", isSubmitted=" + isSubmitted + ", pioneer=" + pioneer + ", approveStatus=" + approveStatus
				+ "]";
	}
	

}
