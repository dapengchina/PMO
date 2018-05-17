package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.entity.HSBCDept;
import com.pom.dashboard.service.HSBCDeptService;

@Service
public class HSBCDeptServiceImpl implements HSBCDeptService
{

    @Resource
    private HSBCDeptMapper hsbcDeptMapper;
    
    @Override
    public List<HSBCDept> queryHSBCDeptName()
    {
        ///List<HSBCDept> list = hsbcDeptMapper.queryHSBCDeptName();
        return null;
    }

    @Override
    public List<HSBCDept> queryHSBCDubDeptNameById(String hsbcSubDeptId)
    {
        //List<HSBCDept> list = hsbcDeptMapper.queryHSBCDubDeptNameById(hsbcSubDeptId);
        return null;
    }

    @Override
    public HSBCDept queryHSBCSubDeptById(String hsbcProjectId)
    {
        //HSBCDept hsbcDept = hsbcDeptMapper.queryHSBCSubDeptById(hsbcProjectId);
        return null;
    }

	@Override
	public List<HSBCDept> queryHSBCSubDeptNameByDeptName(String hsbcDeptName) {
		//List<HSBCDept> list = hsbcDeptMapper.queryHSBCSubDeptNameByDeptName(hsbcDeptName);
		return null;
	}

	public String queryHsbcSubDeptId(String str1, String str2) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hsbcSubDeptName", str1);
		params.put("hsbcDeptName", str2);
		//String hsbcSubDeptId = hsbcDeptMapper.queryHsbcSubDeptId(params);
		return "";
	}

    @Override
    public HSBCDept queryDemandHSBCSubDeptById(String hsbcSubDeptId)
    {
        //HSBCDept hsbcDept = hsbcDeptMapper.queryDemandHSBCSubDeptById(hsbcSubDeptId);
        return null;
    }

	@Override
	public List<HSBCDept> queryAllHSBCDept() {
		//List<HSBCDept> list = hsbcDeptMapper.queryAllHSBCDept();
		return null;
	}

	@Override
	public List<HSBCDept> queryTopParent(HSBCDept hsbcDept) {
		return hsbcDeptMapper.queryTopParent(hsbcDept);
	}

	@Override
	public List<HSBCDept> queryChild(HSBCDept hsbcDept) {
		return hsbcDeptMapper.queryChild(hsbcDept);
	}

	@Override
	public List<HSBCDept> queryById(HSBCDept hsbcDept) {
		return hsbcDeptMapper.queryById(hsbcDept);
	}
}
