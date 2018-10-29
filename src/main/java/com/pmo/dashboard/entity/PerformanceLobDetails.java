package com.pmo.dashboard.entity;

/**
 * 绩效结果
 */
public class PerformanceLobDetails {
    /**
     * 主键
     */
    private String resultId;
    /**
     * 对应的PBC表ID
     */
    private String pbcId;
    /**
     * eHR编号
     */
    private String ehr;
    /**
     * 年
     */
    private String year;
    /**
     * 季度
     */
    private String quarter;
    /**
     * 事业部名称
     */
    private String bu;
    /**
     * 交付部名称
     */
    private String du;
    /**
     * RM人名
     */
    private String rm;
    /**
     * MSA Role
     */
    private String role;
    /**
     * skill
     */
    private String skill;
    /**
     * 归属地
     */
    private String location;
    /**
     * 是否骨干
     */
    private String backbone;
    /**
     * 是否参评
     */
    private String assessed;
    /**
     * 直接主管
     */
    private String directSupervisor;
    /**
     * 绩效结果
     */
    private String result;
    /**
     * 绩效备注
     */
    private String resultComments;
    /**
     * 客户反馈
     */
    private String clientFeedback;
    /**
     * 初评（依据客户反馈）
     */
    private String preAssessmentResult;
    /**
     * 直接主管初评
     */
    private String directSupervisorAssessmentResult;
    /**
     * 部门集体评议结果
     */
    private String groupAssessmentResult;
    /**
     * 集体评议主管
     */
    private String groupAssessmentManager;
    /**
     * A/C/D人员绩效事实
     */
    private String performanceFacts;
    /**
     * 是否绩效跳变
     */
    private String performanceSkip;
    /**
     * 是否最终结果
     */
    private String finalize;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态（带交付部审批， 待LOB总审批）
     */
    private String state;
    /**
     * 员工名字
     */
    private String staffName;
    /**
     * lobId
     */
    private String lobId;
    /**
     * lob
     */
    private String lob;
    /**
     * 入职日期
     */
    private String onBoardDate;
    private String previous1Quarter;
    private String previous2Quarter;
    private String previous3Quarter;

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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getLobId() {
        return lobId;
    }

    public void setLobId(String lobId) {
        this.lobId = lobId;
    }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getOnBoardDate() {
        return onBoardDate;
    }

    public void setOnBoardDate(String onBoardDate) {
        this.onBoardDate = onBoardDate;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getPbcId() {
        return pbcId;
    }

    public void setPbcId(String pbcId) {
        this.pbcId = pbcId;
    }

    public String getEhr() {
        return ehr;
    }

    public void setEhr(String ehr) {
        this.ehr = ehr;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
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

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBackbone() {
        return backbone;
    }

    public void setBackbone(String backbone) {
        this.backbone = backbone;
    }

    public String getAssessed() {
        return assessed;
    }

    public void setAssessed(String assessed) {
        this.assessed = assessed;
    }

    public String getDirectSupervisor() {
        return directSupervisor;
    }

    public void setDirectSupervisor(String directSupervisor) {
        this.directSupervisor = directSupervisor;
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

    public String getClientFeedback() {
        return clientFeedback;
    }

    public void setClientFeedback(String clientFeedback) {
        this.clientFeedback = clientFeedback;
    }

    public String getPreAssessmentResult() {
        return preAssessmentResult;
    }

    public void setPreAssessmentResult(String preAssessmentResult) {
        this.preAssessmentResult = preAssessmentResult;
    }

    public String getDirectSupervisorAssessmentResult() {
        return directSupervisorAssessmentResult;
    }

    public void setDirectSupervisorAssessmentResult(String directSupervisorAssessmentResult) {
        this.directSupervisorAssessmentResult = directSupervisorAssessmentResult;
    }

    public String getGroupAssessmentResult() {
        return groupAssessmentResult;
    }

    public void setGroupAssessmentResult(String groupAssessmentResult) {
        this.groupAssessmentResult = groupAssessmentResult;
    }

    public String getGroupAssessmentManager() {
        return groupAssessmentManager;
    }

    public void setGroupAssessmentManager(String groupAssessmentManager) {
        this.groupAssessmentManager = groupAssessmentManager;
    }

    public String getPerformanceFacts() {
        return performanceFacts;
    }

    public void setPerformanceFacts(String performanceFacts) {
        this.performanceFacts = performanceFacts;
    }

    public String getPerformanceSkip() {
        return performanceSkip;
    }

    public void setPerformanceSkip(String performanceSkip) {
        this.performanceSkip = performanceSkip;
    }

    public String getFinalize() {
        return finalize;
    }

    public void setFinalize(String finalize) {
        this.finalize = finalize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
