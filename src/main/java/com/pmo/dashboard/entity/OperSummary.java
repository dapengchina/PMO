package com.pmo.dashboard.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public class OperSummary {
	private String id;
	private String departmentName;
	private String type;
	private String remark;
	private BigDecimal yearTotal = BigDecimal.ZERO;
	private Map<String,BigDecimal> month ;
	private BigDecimal ifaw;
	private BigDecimal infOt;
	private BigDecimal infPt;
	private BigDecimal infAd;
	private BigDecimal infTravel;
	private BigDecimal infEquipment;
	private BigDecimal infSub;
	private BigDecimal invalid;
	private BigDecimal effectiveSt;
	private BigDecimal invalidSt;
	private BigDecimal thinkTotalIncome;
	private BigDecimal realTotalIncome;
	private BigDecimal effectiveNr;
	private BigDecimal effectiveHuman;
	
	public OperSummary() {
		month = new TreeMap<String,BigDecimal>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length()>o2.length()) {
					return 1;
				}else if(o1.length()<o2.length()) {
					return -1;
				}
				return o1.compareTo(o2);
			}});
		for(int i=1;i<=12;i++) {
			month.put("month"+i, BigDecimal.ZERO);
		}
	}
	
	public BigDecimal getIfaw() {
		return ifaw;
	}
	public void setIfaw(BigDecimal ifaw) {
		this.ifaw = ifaw;
	}
	public BigDecimal getInfOt() {
		return infOt;
	}
	public void setInfOt(BigDecimal infOt) {
		this.infOt = infOt;
	}
	public BigDecimal getInfPt() {
		return infPt;
	}
	public void setInfPt(BigDecimal infPt) {
		this.infPt = infPt;
	}
	public BigDecimal getInfAd() {
		return infAd;
	}
	public void setInfAd(BigDecimal infAd) {
		this.infAd = infAd;
	}
	public BigDecimal getInfTravel() {
		return infTravel;
	}
	public void setInfTravel(BigDecimal infTravel) {
		this.infTravel = infTravel;
	}
	public BigDecimal getInfEquipment() {
		return infEquipment;
	}
	public void setInfEquipment(BigDecimal infEquipment) {
		this.infEquipment = infEquipment;
	}
	public BigDecimal getInfSub() {
		return infSub;
	}
	public void setInfSub(BigDecimal infSub) {
		this.infSub = infSub;
	}
	public BigDecimal getInvalid() {
		return invalid;
	}
	public void setInvalid(BigDecimal invalid) {
		this.invalid = invalid;
	}
	public BigDecimal getEffectiveSt() {
		return effectiveSt;
	}
	public void setEffectiveSt(BigDecimal effectiveSt) {
		this.effectiveSt = effectiveSt;
	}
	public BigDecimal getInvalidSt() {
		return invalidSt;
	}
	public void setInvalidSt(BigDecimal invalidSt) {
		this.invalidSt = invalidSt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getYearTotal() {
		return yearTotal;
	}
	public void setYearTotal(BigDecimal yearTotal) {
		this.yearTotal = yearTotal;
	}
	public Map<String, BigDecimal> getMonth() {
		return month;
	}
	public void setMonth(Map<String, BigDecimal> month) {
		this.month = month;
	}
	public BigDecimal getThinkTotalIncome() {
		thinkTotalIncome = getRealTotalIncome()
				.add(getValue(invalid));
		return thinkTotalIncome;
	}

	public void setThinkTotalIncome(BigDecimal thinkTotalIncome) {
		this.thinkTotalIncome = thinkTotalIncome;
	}

	public BigDecimal getRealTotalIncome() {
		realTotalIncome = getValue(ifaw).add(getValue(infOt)).add(getValue(infPt))
				.add(getValue(infAd)).add(getValue(infTravel)).add(getValue(infEquipment));
		return realTotalIncome;
	}

	public void setRealTotalIncome(BigDecimal realTotalIncome) {
		this.realTotalIncome = realTotalIncome;
	}

	public BigDecimal getEffectiveNr() {
		effectiveNr = getRealTotalIncome().divide(new BigDecimal("1.06"),2 , BigDecimal.ROUND_HALF_UP);
		return effectiveNr;
	}

	public void setEffectiveNr(BigDecimal effectiveNr) {
		this.effectiveNr = effectiveNr;
	}

	public BigDecimal getEffectiveHuman() { 
		if(BigDecimal.ZERO.compareTo( getValue(effectiveSt))==0) {
			return BigDecimal.ZERO;
		}
		effectiveHuman = getValue(ifaw).divide(getValue(effectiveSt),2 , BigDecimal.ROUND_HALF_UP);
//		effectiveHuman 未完成, 还没/当月工作日 
		return effectiveHuman;
	}

	public void setEffectiveHuman(BigDecimal effectiveHuman) {
		this.effectiveHuman = effectiveHuman;
	}
	private BigDecimal getValue(BigDecimal b) {
		if(null == b) {
			return BigDecimal.ZERO;
		}else if(BigDecimal.ZERO.compareTo(b)==0){
			return BigDecimal.ZERO;
		}
		return b ;
	}
}
