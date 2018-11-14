package com.pmo.dashboard.entity;

import java.util.Date;

public class Employeeperforgoal{
	
	
	private String id;

    private String employeeid;

    private String staffname;

    private String staffid;

    private String ehr;

    private String department;

    private String position;

    private String assessmensupervisor;

    private String employeekpoid;

    private String employeeekeyeventid;

    private String employeeimpplanid;

    private Date createdate;

    private String state;

    private String selfassessment;
    
    /**
     * 表外字段
     * @return
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

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname == null ? null : staffname.trim();
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    public String getEhr() {
        return ehr;
    }

    public void setEhr(String ehr) {
        this.ehr = ehr == null ? null : ehr.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getAssessmensupervisor() {
        return assessmensupervisor;
    }

    public void setAssessmensupervisor(String assessmensupervisor) {
        this.assessmensupervisor = assessmensupervisor == null ? null : assessmensupervisor.trim();
    }

    public String getEmployeekpoid() {
        return employeekpoid;
    }

    public void setEmployeekpoid(String employeekpoid) {
        this.employeekpoid = employeekpoid == null ? null : employeekpoid.trim();
    }

    public String getEmployeeekeyeventid() {
        return employeeekeyeventid;
    }

    public void setEmployeeekeyeventid(String employeeekeyeventid) {
        this.employeeekeyeventid = employeeekeyeventid == null ? null : employeeekeyeventid.trim();
    }

    public String getEmployeeimpplanid() {
        return employeeimpplanid;
    }

    public void setEmployeeimpplanid(String employeeimpplanid) {
        this.employeeimpplanid = employeeimpplanid == null ? null : employeeimpplanid.trim();
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

    public String getSelfassessment() {
        return selfassessment;
    }

    public void setSelfassessment(String selfassessment) {
        this.selfassessment = selfassessment == null ? null : selfassessment.trim();
    }
}