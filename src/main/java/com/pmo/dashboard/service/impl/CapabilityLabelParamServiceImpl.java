package com.pmo.dashboard.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CapabilityLabelParamMapper;
import com.pmo.dashboard.entity.CapabilityLabelParam;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CapabilityLabelParamService;

@Service
public class CapabilityLabelParamServiceImpl implements CapabilityLabelParamService {
	@Resource
	private CapabilityLabelParamMapper capabilityLabelParamMapper;

	@Override
	public boolean delete(String id) {
		if (capabilityLabelParamMapper.deleteByPrimaryKey(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(CapabilityLabelParam record) {
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		Integer intHao = Integer.valueOf(record.getMajorcateId()) + 1 ;
		record.setMajorcateId(df.format(intHao));
		
		record.setId(Utils.getUUID());
		record.setCreateDate(new Date());
		if (capabilityLabelParamMapper.insertSelective(record) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public CapabilityLabelParam select(String id) {
		return capabilityLabelParamMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(CapabilityLabelParam record) {
		record.setUpdateDate(new Date());
		if (capabilityLabelParamMapper.updateByPrimaryKeySelective(record) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CapabilityLabelParam> query(CapabilityLabelParam condition) {
		return capabilityLabelParamMapper.query(condition);
	}

	@Override
	public List<CapabilityLabelParam> majorcateIds() {
		return capabilityLabelParamMapper.majorcateIds();
	}

	@Override
	public String maxSubCate(String majorcateId) {
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		Integer intHao = Integer.valueOf(majorcateId) + 1 ;
		majorcateId = df.format(intHao);
		
		String rtn = capabilityLabelParamMapper.maxSubCate(majorcateId);
		if (StringUtils.isBlank(rtn)) {
			rtn = majorcateId + "001";
		} else {
			rtn = rtn.replaceFirst(majorcateId, "");
			intHao = Integer.parseInt(rtn);
			intHao++;
			return majorcateId + df.format(intHao);
		}
		return rtn;
	}

	private static final String STR_FORMAT = "000";

	@Override
	public boolean save(CapabilityLabelParam capabilityLabelParam) {
		int rtnCount = 0;
		if (null == capabilityLabelParam.getId()) {
			capabilityLabelParam.setCreateDate(new Date());
			rtnCount = capabilityLabelParamMapper.insertSelective(capabilityLabelParam);
		} else {
			capabilityLabelParam.setUpdateDate(new Date());
			rtnCount = capabilityLabelParamMapper.updateByPrimaryKeySelective(capabilityLabelParam);
		}
		return rtnCount > 0 ? true : false;
	}

	@Override
	public String fatherCapability(String id) {
		return capabilityLabelParamMapper.fatherCapability(id);
	}

	@Override
	public List<CapabilityLabelParam> queryChilds() {
		return capabilityLabelParamMapper.queryChilds();
	}
}
