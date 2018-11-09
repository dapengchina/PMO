package com.pmo.dashboard.entity;

import java.util.Date;

public class EmployeeImpplan {
	
    private String id;

    private String employeeid;

    private String keyability;

    private String action;

    private String supportor;

    private Date dealine;
    
    private String dealineString;

    private Date createdate;
    
    
    /**
     * 表外字段
     * @return
     */
    private String currentQuarterStartDate;
    private String currentQuarterEndDate;
    
    
    
    

    public String getDealineString() {
		return dealineString;
	}

	public void setDealineString(String dealineString) {
		this.dealineString = dealineString;
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

    public String getKeyability() {
        return keyability;
    }

    public void setKeyability(String keyability) {
        this.keyability = keyability == null ? null : keyability.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getSupportor() {
        return supportor;
    }

    public void setSupportor(String supportor) {
        this.supportor = supportor == null ? null : supportor.trim();
    }

    public Date getDealine() {
        return dealine;
    }

    public void setDealine(Date dealine) {
        this.dealine = dealine;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}