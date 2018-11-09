package com.pmo.dashboard.entity.vo;

import java.util.Date;

public class EmployeePerforGoalVo {
	
	
	private String id;

    private String employeeid;

    private Integer index;

    private String weightrate;

    private String phasegoal;

    private String keyaction;

    private Date createdate;

    private String description;
    
    
    /**
     * 表外字段
     * @return
     */
    private String currentQuarterStartDate;
    private String currentQuarterEndDate;
    private String type;
    private String department;
    
    
    
    
    
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
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
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrentQuarterStartDate() {
		return currentQuarterStartDate;
	}
	public void setCurrentQuarterStartDate(String currentQuarterStartDate) {
		this.currentQuarterStartDate = currentQuarterStartDate;
	}
	public String getCurrentQuarterEndDate() {
		return currentQuarterEndDate;
	}
	public void setCurrentQuarterEndDate(String currentQuarterEndDate) {
		this.currentQuarterEndDate = currentQuarterEndDate;
	}
    
    
    
    

}
