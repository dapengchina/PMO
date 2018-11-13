package com.pmo.dashboard.entity.vo;

public class RmApprovalVo {
	
	
	private String ehr;
	   
	private String employeeName;
	   
	private String msaRole;
	   
	private String skill;
	
	private String state;
	
	private String employeeId;
	
	private String rmUserID;
	   
	private boolean ifbackone;
	   
	private String stateName;
	   
	   
	
	

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getRmUserID() {
		return rmUserID;
	}

	public void setRmUserID(String rmUserID) {
		this.rmUserID = rmUserID;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEhr() {
		return ehr;
	}

	public void setEhr(String ehr) {
		this.ehr = ehr;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMsaRole() {
		return msaRole;
	}

	public void setMsaRole(String msaRole) {
		this.msaRole = msaRole;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public boolean isIfbackone() {
		return ifbackone;
	}

	public void setIfbackone(boolean ifbackone) {
		this.ifbackone = ifbackone;
	}

}
