package com.pmo.dashboard.entity;

public class PerformanceEmpProcessBean {
	String processid;
	String owner;
	String createdate;
	String state;
	String remark;
	String id;
	String employeeid;
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcessid() {
		return processid;
	}
	public void setProcessid(String processid) {
		this.processid = processid;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public PerformanceEmpProcessBean(String processid,String owner,String createdate,String state,String remark,String id,String employeeid) {
		super();
		this.processid = processid;
		this.owner = owner;
		this.createdate = createdate;
		this.state = state;
		this.remark = remark;
		this.id = id;
		this.employeeid = employeeid;
	}
	public PerformanceEmpProcessBean() {
		
	}
}
