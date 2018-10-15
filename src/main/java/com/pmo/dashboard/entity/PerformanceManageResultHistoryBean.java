package com.pmo.dashboard.entity;

public class PerformanceManageResultHistoryBean {
	private String ehr;
	private String empName;
	private String bu;
	private String du;
	private String beginDate;
	private String endDate;
	private String rm;
	private String result;
	private String comments;
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
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}
	public String getDu() {
		return du;
	}
	public void setDu(String du) {
		this.du = du;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public PerformanceManageResultHistoryBean(String ehr, String empName, String bu, String du, String beginDate,
			String endDate, String rm, String result, String comments) {
		super();
		this.ehr = ehr;
		this.empName = empName;
		this.bu = bu;
		this.du = du;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.rm = rm;
		this.result = result;
		this.comments = comments;
	}
	public PerformanceManageResultHistoryBean() {
		super();
	}
	@Override
	public String toString() {
		return "PerformanceManageResultHistoryBean [ehr=" + ehr + ", empName=" + empName + ", bu=" + bu + ", du=" + du
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + ", rm=" + rm + ", result=" + result
				+ ", comments=" + comments + "]";
	}
	
	
	
}
