package com.pmo.dashboard.entity;

public class PerformanceQueryCondition {
	
	private String eHr;
	private String staffName;
	private String role;
	private String skill;
	private String buName;
	private String du;
	private String startYear;
	private String startQuarter;
	private String endYear;
	private String endQuarter;
	public String geteHr() {
		return eHr;
	}
	public void seteHr(String eHr) {
		this.eHr = eHr;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
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
	public String getBuName() {
		return buName;
	}
	public void setBuName(String buName) {
		this.buName = buName;
	}
	public String getDu() {
		return du;
	}
	public void setDu(String du) {
		this.du = du;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getStartQuarter() {
		return startQuarter;
	}
	public void setStartQuarter(String startQuarter) {
		this.startQuarter = startQuarter;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getEndQuarter() {
		return endQuarter;
	}
	public void setEndQuarter(String endQuarter) {
		this.endQuarter = endQuarter;
	}
	@Override
	public String toString() {
		return "PerformanceQueryCondition [eHr=" + eHr + ", staffName=" + staffName + ", role=" + role + ", skill="
				+ skill + ", buName=" + buName + ", du=" + du + ", startYear=" + startYear + ", startQuarter="
				+ startQuarter + ", endYear=" + endYear + ", endQuarter=" + endQuarter + "]";
	}

	

}
