package com.pmo.dashboard.entity.vo;

public class PresultVo {
	
	
	
    private String resultId;

    private String pbcId;

    private String eHr;

    private String year;

    private String quarter;

    private String bu;

    private String du;

    private String rm;

    private String role;

    private String skill;

    private String location;

    private String backbone;

    private String assessed;

    private String directSupervisor;

    private String result;

    private String result_Comments;
    
    private String resultComments;

    private String clientFeedback;

    private String preassessmentResult;

    private String direct_Supervisor_Assessment_Result;
    
    private String directSupervisorAssessmentResult;

    private String groupAssessmentResult;

    private String groupAssessmentManager;

    private String performanceFacts;

    private String performanceSkip;

    private String finalize;

    private String remark;

    private String state;
    
    
    
    /**
     * 表外字段
     * @return
     */
    private String employeeid;
    
    
    
    
    

    public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getResultComments() {
		return resultComments;
	}

	public void setResultComments(String resultComments) {
		this.resultComments = resultComments;
	}

	public String getDirectSupervisorAssessmentResult() {
		return directSupervisorAssessmentResult;
	}

	public void setDirectSupervisorAssessmentResult(String directSupervisorAssessmentResult) {
		this.directSupervisorAssessmentResult = directSupervisorAssessmentResult;
	}

	public String getDirect_Supervisor_Assessment_Result() {
		return direct_Supervisor_Assessment_Result;
	}

	public void setDirect_Supervisor_Assessment_Result(String direct_Supervisor_Assessment_Result) {
		this.direct_Supervisor_Assessment_Result = direct_Supervisor_Assessment_Result;
	}

	public String getResult_Comments() {
		return result_Comments;
	}

	public void setResult_Comments(String result_Comments) {
		this.result_Comments = result_Comments;
	}

	public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId == null ? null : resultId.trim();
    }

    public String getPbcId() {
        return pbcId;
    }

    public void setPbcId(String pbcId) {
        this.pbcId = pbcId == null ? null : pbcId.trim();
    }

    public String geteHr() {
        return eHr;
    }

    public void seteHr(String eHr) {
        this.eHr = eHr == null ? null : eHr.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter == null ? null : quarter.trim();
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu == null ? null : bu.trim();
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du == null ? null : du.trim();
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm == null ? null : rm.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getBackbone() {
        return backbone;
    }

    public void setBackbone(String backbone) {
        this.backbone = backbone == null ? null : backbone.trim();
    }

    public String getAssessed() {
        return assessed;
    }

    public void setAssessed(String assessed) {
        this.assessed = assessed == null ? null : assessed.trim();
    }

    public String getDirectSupervisor() {
        return directSupervisor;
    }

    public void setDirectSupervisor(String directSupervisor) {
        this.directSupervisor = directSupervisor == null ? null : directSupervisor.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getClientFeedback() {
        return clientFeedback;
    }

    public void setClientFeedback(String clientFeedback) {
        this.clientFeedback = clientFeedback == null ? null : clientFeedback.trim();
    }

    public String getPreassessmentResult() {
        return preassessmentResult;
    }

    public void setPreassessmentResult(String preassessmentResult) {
        this.preassessmentResult = preassessmentResult == null ? null : preassessmentResult.trim();
    }

    public String getGroupAssessmentResult() {
        return groupAssessmentResult;
    }

    public void setGroupAssessmentResult(String groupAssessmentResult) {
        this.groupAssessmentResult = groupAssessmentResult == null ? null : groupAssessmentResult.trim();
    }

    public String getGroupAssessmentManager() {
        return groupAssessmentManager;
    }

    public void setGroupAssessmentManager(String groupAssessmentManager) {
        this.groupAssessmentManager = groupAssessmentManager == null ? null : groupAssessmentManager.trim();
    }

    public String getPerformanceFacts() {
        return performanceFacts;
    }

    public void setPerformanceFacts(String performanceFacts) {
        this.performanceFacts = performanceFacts == null ? null : performanceFacts.trim();
    }

    public String getPerformanceSkip() {
        return performanceSkip;
    }

    public void setPerformanceSkip(String performanceSkip) {
        this.performanceSkip = performanceSkip == null ? null : performanceSkip.trim();
    }

    public String getFinalize() {
        return finalize;
    }

    public void setFinalize(String finalize) {
        this.finalize = finalize == null ? null : finalize.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}