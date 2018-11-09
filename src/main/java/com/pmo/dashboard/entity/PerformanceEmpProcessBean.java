package com.pmo.dashboard.entity;

import java.util.Date;

public class PerformanceEmpProcessBean {
	
	
	
	private String id;

    private String employeeid;

    private String processid;

    private String owner;

    private Date createdate;

    private String state;

    private String remark;
    
    
    /**
     * 表外字段
     * @return
     */
    private String processname;
    
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

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
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

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid == null ? null : processid.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
