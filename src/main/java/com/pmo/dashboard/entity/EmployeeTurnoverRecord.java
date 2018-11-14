package com.pmo.dashboard.entity;

import java.sql.Timestamp;
import java.util.Date;

public class EmployeeTurnoverRecord {
    private String id;

    private String employeeid;

    private String ehr;

    private String staffname;

    private String staffid;

    private String lob;

    private String olddepartment;
    
    private String olddepartmentName;

    private String newdepartment;
    
    private String newdepartmentName;

    private Date approvaldate;

    private Date applicationdate;

    private String applicant;
    
    private String applicantName;

    private String approvalid;
    
    private String approvalName;

    private String state;
    
    private String stateName;

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

    public String getEhr() {
        return ehr;
    }

    public void setEhr(String ehr) {
        this.ehr = ehr == null ? null : ehr.trim();
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

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob == null ? null : lob.trim();
    }

    public String getOlddepartment() {
        return olddepartment;
    }
    
    public String getOlddepartmentName(){
    	return olddepartmentName;
    }

    public void setOlddepartment(String olddepartment) {
        this.olddepartment = olddepartment == null ? null : olddepartment.trim();
    }
    public void setOlddepartmentName(String olddepartmentName){
    	this.olddepartmentName = olddepartmentName == null? null :olddepartmentName.trim();
    }

    public String getNewdepartment() {
        return newdepartment;
    }

    public void setNewdepartment(String newdepartment) {
        this.newdepartment = newdepartment == null ? null : newdepartment.trim();
    }

    public String getNewdepartmentName() {
        return newdepartmentName;
    }

    public void setNewdepartmentName(String newdepartmentName) {
        this.newdepartmentName = newdepartmentName == null ? null : newdepartmentName.trim();
    }
    public Date getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(Date approvaldate) {
        this.approvaldate = approvaldate;
    }

    public Date getApplicationdate() {
        return applicationdate;
    }

    public void setApplicationdate(Date applicationdate) {
        this.applicationdate = applicationdate;
    }

    public String getApplicant() {
        return applicant;
    }
    
    public String getApplicantName(){
    	return applicantName;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }
    
    public void setApplicantName(String applicantName){
    	this.applicantName = applicantName == null ? null : applicantName.trim();
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName == null ? null : approvalName.trim();
    }
    
    public String getApprovalid() {
        return approvalid;
    }

    public void setApprovalid(String approvalid) {
        this.approvalid = approvalid == null ? null : approvalid.trim();
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    
    public String getStateNmae() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName == null ? null : stateName.trim();
    }
    
    public EmployeeTurnoverRecord(){
    	super();
    }
    
    public EmployeeTurnoverRecord(String eHr, String lob, String staffid, String staffname, String olddepartment, String newdepartment, String applicant,String approvalid, String state){
    	super();
    	
    	this.ehr = eHr;
    	this.lob = lob;
    	this.staffid = staffid;
    	this.staffname = staffname;
    	this.olddepartment = olddepartment;
        this.newdepartment = newdepartment;
        this.applicant = applicant;
        this.state = state;
        this.approvalid = approvalid;
    }
    
    public EmployeeTurnoverRecord(String id,String employid, String eHr,  String staffName,
            String hsbcStaffId, String lob, String olddepartment, String newdepartment, 
            Timestamp applicationDate, Timestamp approvaldate, String applicant, String state
    		)
    {
        super();
        this.id = id;
        this.employeeid = employid;
        this.ehr = eHr;
        this.staffname = staffName;
        this.staffid = hsbcStaffId;
        this.lob = lob;
        this.olddepartment = olddepartment;
        this.newdepartment = newdepartment;
        this.applicationdate = applicationDate;
        this.approvaldate = approvaldate;
        this.applicant = applicant;
        this.state = state;   
    }
    
}