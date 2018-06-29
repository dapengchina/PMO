package com.pmo.dashboard.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CapabilityLabelParamMapper;
import com.pmo.dashboard.dao.EmployeeSkillMapper;
import com.pmo.dashboard.entity.CapabilityLabelParam;
import com.pmo.dashboard.entity.EmployeeSkill;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.EmployeeSkillService;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {
	@Resource
	private EmployeeSkillMapper employeeSkillMapper;
	
	@Resource
	private CapabilityLabelParamMapper capabilityLabelParamMapper;
	
	@Override
	public boolean delete(String id) {
		if(employeeSkillMapper.deleteByPrimaryKey(id)>0){
            return true;
        }
		return false;
	}

	@Override
	public List<EmployeeSkill> detail(String eHr) {
		return employeeSkillMapper.detail(eHr);
	}

	@Override
	public boolean insert(EmployeeSkill record) {
		boolean rtn = false;
		setParam(record);
		record.setId(Utils.getUUID());
		record.setCreateDate(new Date());
		if(employeeSkillMapper.insertSelective(record)>0){
			rtn = true;
        }
		return rtn;
	}

	@Override
	public EmployeeSkill select(String id) { 
		return employeeSkillMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(EmployeeSkill record) {
		setParam(record);
		record.setUpdateDate(new Date());
		if(employeeSkillMapper.updateByPrimaryKeySelective(record)>0){
            return true;
        }
		return false;
	}

	@Override
	public List<EmployeeSkill> query(EmployeeSkill condition) {
		return employeeSkillMapper.query(condition);
	}

	@Override
	public List<String> skills() {
		return employeeSkillMapper.skills();
	}

	@Override
	public List<EmployeeSkill> toEdit(String eHr) {
		return employeeSkillMapper.toEdit(eHr);
	}

	@Override
	public String haveSkill(EmployeeSkill condition) {
		return employeeSkillMapper.haveSkill(condition);
	}

	@Override
	public List<EmployeeSkill> toBatch() {
		return employeeSkillMapper.toBatch();
	}

	@Override
	public boolean batch(EmployeeSkill record) {
		String[] ids = record.getEmployeeId().split(",");
		for(String eHr: ids) {
			employeeSkillMapper.cleanMainSkill(eHr);
			record.setEmployeeId(eHr);
			String id = employeeSkillMapper.haveSkill(record);
			if(null==employeeSkillMapper.haveSkill(record)) {
				record.setCreateDate(new Date());
				record.setId(Utils.getUUID());
				record.setParamName(capabilityLabelParamMapper.selectByPrimaryKey(record.getCapparamId()).getParamName());
				employeeSkillMapper.insertSelective(record);
			}else {
				record.setId(id);
				record.setUpdateDate(new Date());
				employeeSkillMapper.updateByPrimaryKeySelective(record);
			}
		}
		return true;
	}
	
	private void setParam(EmployeeSkill record) {
		if(StringUtils.isBlank(record.getCapparamId())) {
			CapabilityLabelParam p = new CapabilityLabelParam();
			p.setParamName(record.getParamName());
			List<CapabilityLabelParam> r = capabilityLabelParamMapper.query(p);
			if(r.size()>0) {
				record.setCapparamId(r.get(0).getId());
			}
		}
		if(StringUtils.isBlank(record.getParamName())) {
			record.setParamName(capabilityLabelParamMapper.selectByPrimaryKey(record.getCapparamId()).getParamName());
		}
		if(StringUtils.isNotBlank(record.getAbilityLevel()) && record.getAbilityLevel().length()>1) {
			String level = record.getAbilityLevel();
			if("junior".equals(level)) {
				record.setAbilityLevel("0");
			}else if("intermediate".equals(level)) {
				record.setAbilityLevel("1");
			}else if("senior".equals(level)) {
				record.setAbilityLevel("2");
			}
		}
	}

	@Override
	public int cleanMainSkill(String eHr) {
		return employeeSkillMapper.cleanMainSkill(eHr);
	}

}
