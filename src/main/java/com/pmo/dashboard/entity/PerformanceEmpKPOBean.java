package com.pmo.dashboard.entity;

public class PerformanceEmpKPOBean{
	String index;
	String description;
	String weightrate;
	String phasegoal;
	String keyaction;
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
	public String getindex() {
		return index;
	}
	public void setindex(String index) {
		this.index = index;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWeightrate() {
		return weightrate;
	}
	public void setWeightrate(String weightrate) {
		this.weightrate = weightrate;
	}
	public String getPhasegoal() {
		return phasegoal;
	}
	public void setPhasegoal(String phasegoal) {
		this.phasegoal = phasegoal;
	}
	public String getKeyaction() {
		return keyaction;
	}
	public void setKeyaction(String keyaction) {
		this.keyaction = keyaction;
	}
	public  PerformanceEmpKPOBean(String id,String index,String description,String weightrate,String phasegoal,String keyaction,String createdate,String employeeid){
		super();
		this.id=id;
		this.index=index;
		this.description = description;
		this.weightrate = weightrate;
		this.phasegoal = phasegoal;
		this.keyaction = keyaction;
		this.createdate = createdate;
		this.employeeid = employeeid;
	}
	public  PerformanceEmpKPOBean() {}
}