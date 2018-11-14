package com.pmo.dashboard.entity;

import java.util.Date;

public class EmployeeKeyevent {
	
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
     */
    private String currentQuarterStartDate;
    private String currentQuarterEndDate;
    
    
    

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

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
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
        this.weightrate = weightrate == null ? null : weightrate.trim();
    }

    public String getPhasegoal() {
        return phasegoal;
    }

    public void setPhasegoal(String phasegoal) {
        this.phasegoal = phasegoal == null ? null : phasegoal.trim();
    }

    public String getKeyaction() {
        return keyaction;
    }

    public void setKeyaction(String keyaction) {
        this.keyaction = keyaction == null ? null : keyaction.trim();
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
        this.description = description == null ? null : description.trim();
    }
}