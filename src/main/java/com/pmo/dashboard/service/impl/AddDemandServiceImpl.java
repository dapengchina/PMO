package com.pmo.dashboard.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.AddDemandMapper;
import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.entity.AddDemand;
import com.pom.dashboard.service.AddDemandService;

@Service
public class AddDemandServiceImpl implements AddDemandService{

	@Resource
	AddDemandMapper addDemandMapper;
	
	@Resource
	HSBCDeptMapper hsbcDeptMapper;
	
	@Override
	public boolean addDemand(AddDemand demand) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		if(demand.getHsbcDept()!=null&&demand.getHsbcDept()!=""){
//		if(demand.getHsbcDept().equals(demand.getHsbcSubDept())) {
//			params.put("hsbcDeptName",demand.getHsbcDept() );
//		}else {
//			params.put("hsbcSubDeptName", demand.getHsbcSubDept());
//			params.put("hsbcDeptName",demand.getHsbcDept() );
//		}
//		String hsbcSubDeptId = hsbcDeptMapper.queryHsbcSubDeptId(params);
//		demand.setHsbcSubDeptId(hsbcSubDeptId);
//		}
		
		if(addDemandMapper.addDemand(demand)>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 更新招聘需求信息
	 * add by jama
	 */
	@Override
	public boolean updateDemand(AddDemand demand) {
		Map<String, Object> params = new HashMap<String, Object>();

		if(demand.getHsbcDept()!=""&&demand.getHsbcDept()!=null) {
			if(demand.getHsbcDept().equals(demand.getHsbcSubDept())) {
				params.put("hsbcDeptName",demand.getHsbcDept() );
			}else {
				params.put("hsbcSubDeptName", demand.getHsbcSubDept());
				params.put("hsbcDeptName",demand.getHsbcDept() );
			}
			//String hsbcSubDeptId = hsbcDeptMapper.queryHsbcSubDeptId(params);
			//demand.setHsbcSubDeptId(hsbcSubDeptId);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		String onboradDate = format.format(new Date());
		if(demand.getOnborad()!=null&&demand.getOnborad()!="") {
			demand.setOnboardDate(onboradDate);
		}
		if(demand.getOnborad()!=null&&demand.getOnborad()!="") {
			if(addDemandMapper.modifyDemand2(demand)>0){
				return true;
			}
		}else {
			if(addDemandMapper.modifyDemand(demand)>0){
				return true;
			}
		}
		
		return false;
	}
	
}