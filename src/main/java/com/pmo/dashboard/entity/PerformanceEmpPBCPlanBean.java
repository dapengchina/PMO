package com.pmo.dashboard.entity;

public class PerformanceEmpPBCPlanBean{
	String keyability;
	String action;
	String supportor;
	String dealine;
	String id;
	String createdate;
	String employeeid;
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getkeyability() {
		return keyability;
	}
	public void setkeyability(String keyability) {
		this.keyability = keyability;
	}
	public String getaction() {
		return action;
	}
	public void setaction(String action) {
		this.action = action;
	}
	public String getsupportor() {
		return supportor;
	}
	public void setsupportor(String supportor) {
		this.supportor = supportor;
	}
	public String getdealine() {
		return dealine;
	}
	public void setdealine(String dealine) {
		this.dealine = dealine;
	}
	public PerformanceEmpPBCPlanBean(String id,String keyability,String action,String supportor,String dealine,String createdate,String employeeid) {
		super();
		this.keyability=keyability;
		this.action=action;
		this.supportor=supportor;
		this.dealine=dealine;
		this.id = id;
		this.createdate = createdate;
		this.employeeid = employeeid;
		
	}
	public PerformanceEmpPBCPlanBean() {
		
	}
	public String toString() {
		return "PerformanceEmpPBCPlanBean [keyability=" + keyability + ", action=" + action + ", supportor=" + supportor
				+ ", dealine=" + dealine + "]";
	}
}