package com.pmo.dashboard.entity;

import java.util.Date;

public class CapabilityLabelParam {
    private String id;

    private Date createDate;

    private Date updateDate;

    private String operateId;

    private String majorcateId;

    private String subcateId;

    private String paramName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getMajorcateId() {
        return majorcateId;
    }

    public void setMajorcateId(String majorcateId) {
        this.majorcateId = majorcateId;
    }

    public String getSubcateId() {
        return subcateId;
    }

    public void setSubcateId(String subcateId) {
        this.subcateId = subcateId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}