package com.pmo.dashboard.entity;

public class Demand
{

    private String demandId;
    
    private String engagementType;
    
    public String getEngagementType() {
		return engagementType;
	}

	public void setEngagementType(String engagementType) {
		this.engagementType = engagementType;
	}

	private String rr;
    
    private String jobCode;
    
    private String skill;
    
    private String requestor;
    
    private String position;
    
    private String hsbcSubDeptId;
    
    /** 关联的部门信息*/
    private HSBCDept hsbcDept;
    
    private String location;
    
    private String reqPublishedDate;
    
    private String ageing;
    
    private String profilesNo;
    
    private String interviewedNo;
    
    private String status;
    
    private String candidateName;
    
    private String candidateId;
    
    private String proposedJoiningDate;
    
    private String sowSigned;
    
    private String bgvCleared;
    
    private String reason;
    
    private String remark;
    
    private String csSubDept;
    

    
    private String plannedOnboardDate;
    
    private String doNumber;
    
    private String hrPriority;
    
    //gkf
    private String onboardDate;
    
    private String flag;

    private String completionDay;
    
    private String gbgf;
    
    private String hsbcSubDeptName;
    
    private String userType;
    
    private String hsbcDeptName;
    private String csDeptName;
    
    
    
    
    /**
     * 表外字段
     */
    private String gbgfid;
    private String hsbcdeptid;
    private String hsbcsubdeptid;
    
    
    
    

	public String getGbgf() {
		return gbgf;
	}

	public void setGbgf(String gbgf) {
		this.gbgf = gbgf;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getHsbcSubDeptName() {
		return hsbcSubDeptName;
	}

	public void setHsbcSubDeptName(String hsbcSubDeptName) {
		this.hsbcSubDeptName = hsbcSubDeptName;
	}

	public String getHsbcDeptName() {
		return hsbcDeptName;
	}

	public void setHsbcDeptName(String hsbcDeptName) {
		this.hsbcDeptName = hsbcDeptName;
	}

	public String getCsDeptName() {
		return csDeptName;
	}

	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setCsDeptName(String csDeptName) {
		this.csDeptName = csDeptName;
	}

	
    
    
    
    
	public String getGbgfid() {
		return gbgfid;
	}

	public void setGbgfid(String gbgfid) {
		this.gbgfid = gbgfid;
	}

	public String getHsbcdeptid() {
		return hsbcdeptid;
	}

	public void setHsbcdeptid(String hsbcdeptid) {
		this.hsbcdeptid = hsbcdeptid;
	}

	public String getHsbcsubdeptid() {
		return hsbcsubdeptid;
	}

	public void setHsbcsubdeptid(String hsbcsubdeptid) {
		this.hsbcsubdeptid = hsbcsubdeptid;
	}

	public String getCompletionDay() {
		return completionDay;
	}

	public void setCompletionDay(String completionDay) {
		this.completionDay = completionDay;
	}

	public String getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(String onboardDate) {
		this.onboardDate = onboardDate;
	}

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	public String getRr() {
		return rr;
	}

	public void setRr(String rr) {
		this.rr = rr;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHsbcSubDeptId() {
		return hsbcSubDeptId;
	}

	public void setHsbcSubDeptId(String hsbcSubDeptId) {
		this.hsbcSubDeptId = hsbcSubDeptId;
	}

	public HSBCDept getHsbcDept() {
		return hsbcDept;
	}

	public void setHsbcDept(HSBCDept hsbcDept) {
		this.hsbcDept = hsbcDept;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReqPublishedDate() {
		return reqPublishedDate;
	}

	public void setReqPublishedDate(String reqPublishedDate) {
		this.reqPublishedDate = reqPublishedDate;
	}

	public String getAgeing() {
		return ageing;
	}

	public void setAgeing(String ageing) {
		this.ageing = ageing;
	}

	public String getProfilesNo() {
		return profilesNo;
	}

	public void setProfilesNo(String profilesNo) {
		this.profilesNo = profilesNo;
	}

	public String getInterviewedNo() {
		return interviewedNo;
	}

	public void setInterviewedNo(String interviewedNo) {
		this.interviewedNo = interviewedNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getProposedJoiningDate() {
		return proposedJoiningDate;
	}

	public void setProposedJoiningDate(String proposedJoiningDate) {
		this.proposedJoiningDate = proposedJoiningDate;
	}

	public String getSowSigned() {
		return sowSigned;
	}

	public void setSowSigned(String sowSigned) {
		this.sowSigned = sowSigned;
	}

	public String getBgvCleared() {
		return bgvCleared;
	}

	public void setBgvCleared(String bgvCleared) {
		this.bgvCleared = bgvCleared;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCsSubDept() {
		return csSubDept;
	}

	public void setCsSubDept(String csSubDept) {
		this.csSubDept = csSubDept;
	}

	public String getPlannedOnboardDate() {
		return plannedOnboardDate;
	}

	public void setPlannedOnboardDate(String plannedOnboardDate) {
		this.plannedOnboardDate = plannedOnboardDate;
	}

	public String getDoNumber() {
		return doNumber;
	}

	public void setDoNumber(String doNumber) {
		this.doNumber = doNumber;
	}

	public String getHrPriority() {
		return hrPriority;
	}

	public void setHrPriority(String hrPriority) {
		this.hrPriority = hrPriority;
	}

	public Demand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demand(String demandId, String rr, String jobCode, String skill, String requestor, String position,
			String hsbcSubDeptId, HSBCDept hsbcDept, String location, String reqPublishedDate, String ageing,
			String profilesNo, String interviewedNo, String status, String candidateName, String proposedJoiningDate,
			String sowSigned, String bgvCleared, String reason, String remark, String csSubDept,
			String plannedOnboardDate, String doNumber, String hrPriority) {
		super();
		this.demandId = demandId;
		this.rr = rr;
		this.jobCode = jobCode;
		this.skill = skill;
		this.requestor = requestor;
		this.position = position;
		this.hsbcSubDeptId = hsbcSubDeptId;
		this.hsbcDept = hsbcDept;
		this.location = location;
		this.reqPublishedDate = reqPublishedDate;
		this.ageing = ageing;
		this.profilesNo = profilesNo;
		this.interviewedNo = interviewedNo;
		this.status = status;
		this.candidateName = candidateName;
		this.proposedJoiningDate = proposedJoiningDate;
		this.sowSigned = sowSigned;
		this.bgvCleared = bgvCleared;
		this.reason = reason;
		this.remark = remark;
		this.csSubDept = csSubDept;
		this.plannedOnboardDate = plannedOnboardDate;
		this.doNumber = doNumber;
		this.hrPriority = hrPriority;
	}

}
