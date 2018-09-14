package com.pmo.dashboard.entity;

public class PerformanceManageEvaBean {
	//EHR编号
	private String ehr;
	//LOB工号
	private String lobNo;
	//姓名
	private String name;
	//入职时间
	private String hireDate;
	//职务
	private String position;
	//业务线
	private String serviceLine;
	//BU
	private String bu;
	//交付部
	private String du;
	//归属地
	private String location;
	//是否骨干
	private String keymember;
	//是否参评
	private String participate;
	//直接主管
	private String manager;
	//客户反馈
	private String customerFeedback;
	//初评(依据客户反馈)
	private String initialEvalution;
	//直接主管初评结果
	private String pmEvalution;
	//部门集体评议结果
	private String duEvalution;
	//集体评议主管
	private String duEvaManager;
	//A/C/D人员绩效事实
	private String achievement;
	//是否绩效跳变
	private String jump;
	//备注
	private String comments;
	//上季度绩效
	private String previous1Quarter;
	//上上季度绩效
	private String previous2Quarter;
	//上上上季度绩效
	private String previous3Quarter;
	
	private String year;
	private String quarter;
	private String result;
	private String resultComments;
	private String rm;
	private String role;
	private String skill;
	
	
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
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultComments() {
		return resultComments;
	}
	public void setResultComments(String resultComments) {
		this.resultComments = resultComments;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getEhr() {
		return ehr;
	}
	public void setEhr(String ehr) {
		this.ehr = ehr;
	}
	public String getLobNo() {
		return lobNo;
	}
	public void setLobNo(String lobNo) {
		this.lobNo = lobNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getServiceLine() {
		return serviceLine;
	}
	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}
	public String getDu() {
		return du;
	}
	public void setDu(String du) {
		this.du = du;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getKeymember() {
		return keymember;
	}
	public void setKeymember(String keymember) {
		this.keymember = keymember;
	}
	public String getParticipate() {
		return participate;
	}
	public void setParticipate(String participate) {
		this.participate = participate;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getCustomerFeedback() {
		return customerFeedback;
	}
	public void setCustomerFeedback(String customerFeedback) {
		this.customerFeedback = customerFeedback;
	}
	public String getInitialEvalution() {
		return initialEvalution;
	}
	public void setInitialEvalution(String initialEvalution) {
		this.initialEvalution = initialEvalution;
	}
	public String getPmEvalution() {
		return pmEvalution;
	}
	public void setPmEvalution(String pmEvalution) {
		this.pmEvalution = pmEvalution;
	}
	public String getDuEvalution() {
		return duEvalution;
	}
	public void setDuEvalution(String duEvalution) {
		this.duEvalution = duEvalution;
	}
	public String getDuEvaManager() {
		return duEvaManager;
	}
	public void setDuEvaManager(String duEvaManager) {
		this.duEvaManager = duEvaManager;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getJump() {
		return jump;
	}
	public void setJump(String jump) {
		this.jump = jump;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPrevious1Quarter() {
		return previous1Quarter;
	}
	public void setPrevious1Quarter(String previous1Quarter) {
		this.previous1Quarter = previous1Quarter;
	}
	public String getPrevious2Quarter() {
		return previous2Quarter;
	}
	public void setPrevious2Quarter(String previous2Quarter) {
		this.previous2Quarter = previous2Quarter;
	}
	public String getPrevious3Quarter() {
		return previous3Quarter;
	}
	public void setPrevious3Quarter(String previous3Quarter) {
		this.previous3Quarter = previous3Quarter;
	}
	public PerformanceManageEvaBean(String ehr, String lobNo, String name, String hireDate, String position,
			String serviceLine, String bu, String du, String location, String keymember, String participate, String pm,
			String customerFeedback, String initialEvalution, String pmEvalution, String duEvalution,
			String duEvaManager, String achievement, String jump, String comments, String previous1Quarter,
			String previous2Quarter, String previous3Quarter) {
		super();
		this.ehr = ehr;
		this.lobNo = lobNo;
		this.name = name;
		this.hireDate = hireDate;
		this.position = position;
		this.serviceLine = serviceLine;
		this.bu = bu;
		this.du = du;
		this.location = location;
		this.keymember = keymember;
		this.participate = participate;
		this.manager = pm;
		this.customerFeedback = customerFeedback;
		this.initialEvalution = initialEvalution;
		this.pmEvalution = pmEvalution;
		this.duEvalution = duEvalution;
		this.duEvaManager = duEvaManager;
		this.achievement = achievement;
		this.jump = jump;
		this.comments = comments;
		this.previous1Quarter = previous1Quarter;
		this.previous2Quarter = previous2Quarter;
		this.previous3Quarter = previous3Quarter;
	}
	public PerformanceManageEvaBean() {
		super();
	}
	@Override
	public String toString() {
		return "PerformanceManageEvaBean [ehr=" + ehr + ", lobNo=" + lobNo + ", name=" + name + ", hireDate=" + hireDate
				+ ", position=" + position + ", serviceLine=" + serviceLine + ", bu=" + bu + ", du=" + du
				+ ", location=" + location + ", keymember=" + keymember + ", participate=" + participate + ", pm=" + manager
				+ ", customerFeedback=" + customerFeedback + ", initialEvalution=" + initialEvalution + ", pmEvalution="
				+ pmEvalution + ", duEvalution=" + duEvalution + ", duEvaManager=" + duEvaManager + ", achievement="
				+ achievement + ", jump=" + jump + ", comments=" + comments + ", previous1Quarter=" + previous1Quarter
				+ ", previous2Quarter=" + previous2Quarter + ", previous3Quarter=" + previous3Quarter + "]";
	}
	
	
}
