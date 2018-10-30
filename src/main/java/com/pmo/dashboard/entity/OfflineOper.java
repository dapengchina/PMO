package com.pmo.dashboard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OfflineOper {
    private String id;						//id
    
    private String eHr; 					//员工E-HR账号

    private String staffName;				//员工中文名

    private String employeeId;				//员工employee_id

    private Date createDate;				//创建日期

    private Date createUpdate;				//修改日期

    private String csSubdeptId;				//中软部门ID

    private String rmId;					//RM_ID

    private String rmName;					//RM姓名

    private String operatorId;				//操作人ID

    private String month;					//月份

    private String year;					//年份

    private BigDecimal chsoftiMskHours ;		//中软月标准工时

    private BigDecimal chsoftiAwHours;		//中软实际工时

    private BigDecimal chsoftiIwHours;		//中软无效工时

    private BigDecimal chsoftiOtHours;		//中软加班费工时

    private BigDecimal chsoftiToHours;		//中软调休工时

    private BigDecimal chsoftiApwHours;		//中软调整上月工时

    private BigDecimal chsoftiIfaw = BigDecimal.ZERO ;			//实际工时收入

    private BigDecimal chsoftiInvalid = BigDecimal.ZERO ;		//无效工时收入

    private BigDecimal chsoftiInfOt = BigDecimal.ZERO ;		//加班费工时收入

    private BigDecimal chsoftiInfPt = BigDecimal.ZERO ;		//调休工时收入

    private BigDecimal chsoftiInfAd = BigDecimal.ZERO ;		//调整上月工时收入

    private BigDecimal chsoftiInfTravel;	//差旅收入

    private BigDecimal chsoftiInfEquipment;	//付费设备收入

    private BigDecimal chsoftiInfSub;		//分包收入

    private BigDecimal chsoftiInfTotal = BigDecimal.ZERO ;		//月收入合计-原币种

    private BigDecimal chsoftiInfCurrent = BigDecimal.ZERO ;	//当月有效收入

    private BigDecimal chsoftiEffectiveNr = BigDecimal.ZERO ;	//有效NR

    private BigDecimal chsoftiEffectiveSt = BigDecimal.ZERO ;	//当月有效人力

    private BigDecimal chsoftiInvalidSt = BigDecimal.ZERO ;	//当月无效人力
    
    private BigDecimal chsoftiInfRmbtotal = BigDecimal.ZERO ; // •	月收入合计-人民币 
    
    private BigDecimal exRate;  // 汇率
    
    private String chsoftiProNumber;
    private String chsoftiProName;
    private String csSubDept;

    private String remark;					//备注
    
    private String[] ids;
    
    private String billRate;  
    
    
    

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String geteHr() {
        return eHr;
    }

    public void seteHr(String eHr) {
        this.eHr = eHr;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateUpdate() {
        return createUpdate;
    }

    public void setCreateUpdate(Date createUpdate) {
        this.createUpdate = createUpdate;
    }

    public String getCsSubdeptId() {
        return csSubdeptId;
    }

    public void setCsSubdeptId(String csSubdeptId) {
        this.csSubdeptId = csSubdeptId;
    }

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getChsoftiMskHours() {
        return chsoftiMskHours;
    }

    public void setChsoftiMskHours(BigDecimal chsoftiMskHours) {
        this.chsoftiMskHours = chsoftiMskHours;
    }

    public BigDecimal getChsoftiAwHours() {
        return chsoftiAwHours;
    }

    public void setChsoftiAwHours(BigDecimal chsoftiAwHours) {
        this.chsoftiAwHours = chsoftiAwHours;
    }

    public BigDecimal getChsoftiIwHours() {
        return chsoftiIwHours;
    }

    public void setChsoftiIwHours(BigDecimal chsoftiIwHours) {
        this.chsoftiIwHours = chsoftiIwHours;
    }

    public BigDecimal getChsoftiOtHours() {
        return chsoftiOtHours;
    }

    public void setChsoftiOtHours(BigDecimal chsoftiOtHours) {
        this.chsoftiOtHours = chsoftiOtHours;
    }

    public BigDecimal getChsoftiToHours() {
        return chsoftiToHours;
    }

    public void setChsoftiToHours(BigDecimal chsoftiToHours) {
        this.chsoftiToHours = chsoftiToHours;
    }

    public BigDecimal getChsoftiApwHours() {
        return chsoftiApwHours;
    }

    public void setChsoftiApwHours(BigDecimal chsoftiApwHours) {
        this.chsoftiApwHours = chsoftiApwHours;
    }

    public BigDecimal getChsoftiIfaw() {
        return chsoftiIfaw;
    }

    public void setChsoftiIfaw(BigDecimal chsoftiIfaw) {
        this.chsoftiIfaw = chsoftiIfaw;
    }

    public BigDecimal getChsoftiInvalid() {
        return chsoftiInvalid;
    }

    public void setChsoftiInvalid(BigDecimal chsoftiInvalid) {
        this.chsoftiInvalid = chsoftiInvalid;
    }

    public BigDecimal getChsoftiInfOt() {
        return chsoftiInfOt;
    }

    public void setChsoftiInfOt(BigDecimal chsoftiInfOt) {
        this.chsoftiInfOt = chsoftiInfOt;
    }

    public BigDecimal getChsoftiInfPt() {
        return chsoftiInfPt;
    }

    public void setChsoftiInfPt(BigDecimal chsoftiInfPt) {
        this.chsoftiInfPt = chsoftiInfPt;
    }

    public BigDecimal getChsoftiInfAd() {
        return chsoftiInfAd;
    }

    public void setChsoftiInfAd(BigDecimal chsoftiInfAd) {
        this.chsoftiInfAd = chsoftiInfAd;
    }

    public BigDecimal getChsoftiInfTravel() {
        return chsoftiInfTravel;
    }

    public void setChsoftiInfTravel(BigDecimal chsoftiInfTravel) {
        this.chsoftiInfTravel = chsoftiInfTravel;
    }

    public BigDecimal getChsoftiInfEquipment() {
        return chsoftiInfEquipment;
    }

    public void setChsoftiInfEquipment(BigDecimal chsoftiInfEquipment) {
        this.chsoftiInfEquipment = chsoftiInfEquipment;
    }

    public BigDecimal getChsoftiInfSub() {
        return chsoftiInfSub;
    }

    public void setChsoftiInfSub(BigDecimal chsoftiInfSub) {
        this.chsoftiInfSub = chsoftiInfSub;
    }

    public BigDecimal getChsoftiInfTotal() {
        return chsoftiInfTotal;
    }

    public void setChsoftiInfTotal(BigDecimal chsoftiInfTotal) {
        this.chsoftiInfTotal = chsoftiInfTotal;
    }

    public BigDecimal getChsoftiInfCurrent() {
        return chsoftiInfCurrent;
    }

    public void setChsoftiInfCurrent(BigDecimal chsoftiInfCurrent) {
        this.chsoftiInfCurrent = chsoftiInfCurrent;
    }

    public BigDecimal getChsoftiEffectiveNr() {
        return chsoftiEffectiveNr;
    }

    public void setChsoftiEffectiveNr(BigDecimal chsoftiEffectiveNr) {
        this.chsoftiEffectiveNr = chsoftiEffectiveNr;
    }

    public BigDecimal getChsoftiEffectiveSt() {
        return chsoftiEffectiveSt;
    }

    public void setChsoftiEffectiveSt(BigDecimal chsoftiEffectiveSt) {
        this.chsoftiEffectiveSt = chsoftiEffectiveSt;
    }

    public BigDecimal getChsoftiInvalidSt() {
        return chsoftiInvalidSt;
    }

    public void setChsoftiInvalidSt(BigDecimal chsoftiInvalidSt) {
        this.chsoftiInvalidSt = chsoftiInvalidSt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getBillRate() {
		return billRate;
	}

	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}

	public String getChsoftiProNumber() {
		return chsoftiProNumber;
	}

	public void setChsoftiProNumber(String chsoftiProNumber) {
		this.chsoftiProNumber = chsoftiProNumber;
	}

	public String getChsoftiProName() {
		return chsoftiProName;
	}

	public void setChsoftiProName(String chsoftiProName) {
		this.chsoftiProName = chsoftiProName;
	}

	public String getCsSubDept() {
		return csSubDept;
	}

	public void setCsSubDept(String csSubDept) {
		this.csSubDept = csSubDept;
	}

	public BigDecimal getChsoftiInfRmbtotal() {
		return chsoftiInfRmbtotal;
	}

	public void setChsoftiInfRmbtotal(BigDecimal chsoftiInfRmbtotal) {
		this.chsoftiInfRmbtotal = chsoftiInfRmbtotal;
	}

	public BigDecimal getExRate() {
		return exRate;
	}

	public void setExRate(BigDecimal exRate) {
		this.exRate = exRate;
	}

}