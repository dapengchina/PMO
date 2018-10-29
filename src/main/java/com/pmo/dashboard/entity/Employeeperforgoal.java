package com.pmo.dashboard.entity;

public class Employeeperforgoal{
	String id;
	String employeeid;
	String staffname;
	String staffid;
	String ehr;
	String department;
	String position;
	String assessmensupervisor;
	String employeekpoid;
	String employeeekeyeventid;
	String employeeimpplanid;
	String createdate;
	String state;
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
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getEhr() {
		return ehr;
	}
	public void setEhr(String ehr) {
		this.ehr = ehr;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAssessmensupervisor() {
		return assessmensupervisor;
	}
	public void setAssessmensupervisor(String assessmensupervisor) {
		this.assessmensupervisor = assessmensupervisor;
	}
	public String getEmployeekpoid() {
		return employeekpoid;
	}
	public void setEmployeekpoid(String employeekpoid) {
		this.employeekpoid = employeekpoid;
	}
	public String getEmployeeekeyeventid() {
		return employeeekeyeventid;
	}
	public void setEmployeeekeyeventid(String employeeekeyeventid) {
		this.employeeekeyeventid = employeeekeyeventid;
	}
	public String getEmployeeimpplanid() {
		return employeeimpplanid;
	}
	public void setEmployeeimpplanid(String employeeimpplanid) {
		this.employeeimpplanid = employeeimpplanid;
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
	public Employeeperforgoal(String id, String employeeid, String staffname,String staffid, String ehr, String department,String position, String assessmensupervisor,String employeekpoid,String employeeekeyeventid,String employeeimpplanid,String createdate,String state) {
		super();
		this.id = id;
		this.employeeid = employeeid;
		this.staffid = staffid;
		this.staffname =staffname;
		this.ehr = ehr;
		this.department = department;
		this.position = position;
		this.assessmensupervisor = assessmensupervisor;
		this.employeeekeyeventid = employeeekeyeventid;
		this.employeeimpplanid = employeeimpplanid;
		this.employeekpoid = employeekpoid;
		this.createdate = createdate;
		this.state = state;
	}
	public Employeeperforgoal() {
	}
	
}