package com.pmo.dashboard.entity;

public class OfflineOperCondition
{
	private String staffName;
	private String eHr;
	private String employeeId;
	private String projectName;
	private String proNumber;
	private String csdeptid;
	private String rmId;
	private String[] ids;
	private String year;
	private String month;
	
//	private String chsoftiProNumber;
//    private String chsoftiProName;
    private String csSubDept;
	
//	public String getChsoftiProNumber() {
//		return chsoftiProNumber;
//	}
//	public void setChsoftiProNumber(String chsoftiProNumber) {
//		this.chsoftiProNumber = chsoftiProNumber;
//	}
//	public String getChsoftiProName() {
//		return chsoftiProName;
//	}
//	public void setChsoftiProName(String chsoftiProName) {
//		this.chsoftiProName = chsoftiProName;
//	}
	public String getCsSubDept() {
		return csSubDept;
	}
	public void setCsSubDept(String csSubDept) {
		this.csSubDept = csSubDept;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getRmId() {
		return rmId;
	}
	public void setRmId(String rmId) {
		this.rmId = rmId;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String geteHr() {
		return eHr;
	}
	public void seteHr(String eHr) {
		this.eHr = eHr;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProNumber() {
		return proNumber;
	}
	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}
	public String getCsdeptid() {
		return csdeptid;
	}
	public void setCsdeptid(String csdeptid) {
		this.csdeptid = csdeptid;
	}
	
}