package com.pmo.dashboard.entity;

import java.math.BigDecimal;

public class Promote
{
	private String id;
	private String employeeId;
	private String createDate;
	private String operateId;
	private String effectDate;
	private String originalLevel;
	private String nowLevel;
	private String previousRate;
	private String nowRate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getOperateId() {
		return operateId;
	}
	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	public String getOriginalLevel() {
		return originalLevel;
	}
	public void setOriginalLevel(String originalLevel) {
		this.originalLevel = originalLevel;
	}
	public String getNowLevel() {
		return nowLevel;
	}
	public void setNowLevel(String nowLevel) {
		this.nowLevel = nowLevel;
	}
	public String getPreviousRate() {
		return previousRate;
	}
	public void setPreviousRate(String previousRate) {
		this.previousRate = previousRate;
	}
	public String getNowRate() {
		return nowRate;
	}
	public void setNowRate(String nowRate) {
		this.nowRate = nowRate;
	}
	
}