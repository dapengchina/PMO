package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.PerformanceResultMapper;
import com.pmo.dashboard.entity.PerformanceManageTargetBean;
import com.pom.dashboard.service.PerformanceManageTargetService;

@Service
public class PerformanceManageTargetServiceImpl implements PerformanceManageTargetService {

	
	@Resource
	PerformanceResultMapper mapper;
	
	@Override
	public List<PerformanceManageTargetBean> queryManageTargetApprovalList(){
		List<PerformanceManageTargetBean> list = new ArrayList<>();

		for (int i =0; i<6; i++) {
			PerformanceManageTargetBean bean1 = new PerformanceManageTargetBean(i+"", "XXX", "Senior", "HTML", "是", "是 ", "已审批");
			list.add(bean1);
		}
		PerformanceManageTargetBean bean2 = new PerformanceManageTargetBean("8", "XXX", "BA", "Java", "否", "否 ", "未审批");
		list.add(bean2);
		return list;
	}
		
//	@Override
//	public List<PerformanceManageTargetBean> queryManageTargetApprovalList(){
//		List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(null);
//		
//		for (PerformanceManageEvaBean bean: list) {
////			String ehr, String empName, String role, String skill, String isSubmitted,
////			String pioneer, String approveStatus
//			PerformanceManageTargetBean bean2 = new PerformanceManageTargetBean("8", "XXX", "BA", "Java", "否", "否 ", "未审批");
//			bean2.setEhr(bean.getEhr());
//			bean2.setEmpName(bean.getName());
//			bean2.setRole(bean.getRole());
//			bean2.setSkill(bean.getSkill());	
//			list.add(bean2);
//		}
//		return list;
//	}
//	
}
