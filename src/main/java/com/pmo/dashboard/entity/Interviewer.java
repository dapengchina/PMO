package com.pmo.dashboard.entity;

public class Interviewer  extends PageCondition
{
    
    private String employeeId; 
    //员工姓名
    private String staffName;
    //员工所在地
    private String staffRegion;
    //面试官工作经验
    private String experienceYearas;
    //面试官角色
    private String role;
    //面试官专长
    private String skill;
    //面试官状态
    private String status;
   //员工工资号（唯一的）
    private String ehr;
    //面试官汇丰的id
    private String hsbcStaffId;
    //lob工号
    private String lob;
   //交付部ID
    private String csSubDeptId;
    
    private String experienceStart;
    private String experienceEnd;
    
    
	public String getExperienceStart() {
		return experienceStart;
	}


	public void setExperienceStart(String experienceStart) {
		this.experienceStart = experienceStart;
	}


	public String getExperienceEnd() {
		return experienceEnd;
	}


	public void setExperienceEnd(String experienceEnd) {
		this.experienceEnd = experienceEnd;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public String getStaffName() {
		return staffName;
	}


	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getStaffRegion() {
		return staffRegion;
	}


	public void setStaffRegion(String staffRegion) {
		this.staffRegion = staffRegion;
	}


	public String getExperienceYearas() {
		return experienceYearas;
	}


	public void setExperienceYearas(String experienceYearas) {
		this.experienceYearas = experienceYearas;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getSkill() {
		return skill;
	}


	public void setSkill(String skill) {
		this.skill = skill;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getEhr() {
		return ehr;
	}


	public void setEhr(String ehr) {
		this.ehr = ehr;
	}


	public String getHsbcStaffId() {
		return hsbcStaffId;
	}


	public void setHsbcStaffId(String hsbcStaffId) {
		this.hsbcStaffId = hsbcStaffId;
	}


	public String getLob() {
		return lob;
	}


	public void setLob(String lob) {
		this.lob = lob;
	}


	public String getCsSubDeptId() {
		return csSubDeptId;
	}


	public void setCsSubDeptId(String csSubDeptId) {
		this.csSubDeptId = csSubDeptId;
	}


	public Interviewer(String employeeId, String staffName, String staffRegion, String experienceYearas, String role,
		String skill, String status, String ehr, String hsbcStaffId, String lob, String csSubDeptId) {
	super();
	this.employeeId = employeeId;
	this.staffName = staffName;
	this.staffRegion = staffRegion;
	this.experienceYearas = experienceYearas;
	this.role = role;
	this.skill = skill;
	this.status = status;
	this.ehr = ehr;
	this.hsbcStaffId = hsbcStaffId;
	this.lob = lob;
	this.csSubDeptId = csSubDeptId;
}


	public Interviewer()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

}
