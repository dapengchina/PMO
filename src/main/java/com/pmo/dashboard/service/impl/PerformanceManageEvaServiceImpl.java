package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.PerformanceResultMapper;
import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceManageResultHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pom.dashboard.service.PerformanceManageEvaService;

@Service
public class PerformanceManageEvaServiceImpl implements PerformanceManageEvaService {
	private static final Logger logger = LoggerFactory.getLogger(PerformanceManageEvaServiceImpl.class);
	
	@Resource
	PerformanceResultMapper mapper;

	@Override
	public List<PerformanceManageEvaBean> queryManageEvaFirstDetailWithAchieveList(PerformanceQueryCondition condition) {
		List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(condition);
		return list;	
//		List<PerformanceManageEvaBean> list = queryManageEvaFirstDetailList(condition);
//		for (PerformanceManageEvaBean bean : list) {
//			bean.setPmEvalution("B+");
//			bean.setDuEvalution("B");
//			bean.setAchievement("工作负责高效 ");
//			bean.setJump("否");
//		}
//		return list;		
	}
	
	@Override
	public List<PerformanceManageEvaBean> queryManageEvaFirstDetailList(PerformanceQueryCondition condition) {
		List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(condition);
		return list;
//		List<PerformanceManageEvaBean> list = new ArrayList<>();
//		
//		for(int i=0; i<5; i++) {
//			PerformanceManageEvaBean bean = new PerformanceManageEvaBean();
//			bean.setEhr("0090127655");
//			bean.setLobNo("45678");
//			bean.setName("William");
//			bean.setHireDate("2017-10-11 ");
//			bean.setPosition("Senior Developer ");
//			bean.setServiceLine("HSBC");
//			bean.setBu("xxx 事业部");
//			bean.setDu("xxx 交付部 ");
//			bean.setLocation("Xian");
//			bean.setKeymember("是");
//			bean.setParticipate("是");
//			bean.setManager("XXX Rm");
//			bean.setCustomerFeedback("该员工平时工作仔细认真，负责。不但执行力强，而且工作配合度也好，有积极向上的工作态度，能主动协调其他同事工作，并且能及时完成上级领导安排的其他工作");
//			bean.setInitialEvalution("B+");
//			bean.setPmEvalution("");
//			bean.setDuEvalution("");
//			bean.setDuEvaManager("XXX交付部经理 ");
//			bean.setAchievement("");
//			bean.setJump("");
//			bean.setComments("");
//			bean.setPrevious1Quarter("B+");
//			bean.setPrevious2Quarter("B+");
//			bean.setPrevious3Quarter("A");
//
//			list.add(bean);
//		}
//		for(int i=0; i<4; i++) {
//			PerformanceManageEvaBean bean = new PerformanceManageEvaBean();
//			bean.setEhr("0090127666");
//			bean.setLobNo("45678");
//			bean.setName("William");
//			bean.setHireDate("2017-10-11 ");
//			bean.setPosition("Senior Developer ");
//			bean.setServiceLine("HSBC");
//			bean.setBu("xxx 事业部");
//			bean.setDu("xxx 交付部 ");
//			bean.setLocation("Xian");
//			bean.setKeymember("是");
//			bean.setParticipate("是");
//			bean.setManager("XXX Rm");
//			bean.setCustomerFeedback("该员工平时工作仔细认真，负责。不但执行力强，而且工作配合度也好，有积极向上的工作态度，能主动协调其他同事工作，并且能及时完成上级领导安排的其他工作");
//			bean.setInitialEvalution("B");
//			bean.setPmEvalution("");
//			bean.setDuEvalution("");
//			bean.setDuEvaManager("XXX交付部经理 ");
//			bean.setAchievement("");
//			bean.setJump("");
//			bean.setComments("");
//			bean.setPrevious1Quarter("B+");
//			bean.setPrevious2Quarter("B+");
//			bean.setPrevious3Quarter("A");
//
//			list.add(bean);
//		}
//		return list;
	}
	
	
	@Override
	public List<PerformanceEmpHistoryBean> queryManageEvaSecondDUList(PerformanceQueryCondition condition) {
		List<PerformanceEmpHistoryBean> list = new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean();
			bean.setRM("Abigal ");
			bean.setYear("2018");
			bean.setQuarter("Q2");
			bean.setStatus("待审批");
			list.add(bean);
		}

		return list;
	}
	
	
	@Override
	public List<PerformanceEmpHistoryBean> queryManageEvaSecondBUList(PerformanceQueryCondition condition) {
		List<PerformanceEmpHistoryBean> list = new ArrayList<>();
		
		for(int i=0; i<5; i++) {
			PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean();
			bean.setDU("移动");
			bean.setYear("2018");
			bean.setQuarter("Q2");
			bean.setStatus("待审批");
			list.add(bean);
		}

		return list;
	}

	@Override
	public List<PerformanceManageEvaBean> queryManageEvaSecondQueryList(PerformanceQueryCondition condition) {
		List<PerformanceManageEvaBean> list = queryManageEvaFirstDetailWithAchieveList(condition);
		//为了测试查询，简单添加了ehr过滤条件
		List<PerformanceManageEvaBean> list2 = new ArrayList<>();
		String ehr = condition==null?"":condition.geteHr();
		for (PerformanceManageEvaBean b : list) {
			if (ehr == null || ehr.equals("") || b.getEhr().contains(ehr)) { 
				list2.add(b);				
			}
		}
		return list2;		
	}
	
	
	@Override
	public List<PerformanceManageEvaBean> queryManageEvaSecondQueryDUList(PerformanceQueryCondition condition) {		
		List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(condition);
		return list;		
	}
	
	@Override
	public List<PerformanceManageResultHistoryBean> queryManageResultHistoryQueryList(PerformanceQueryCondition condition) {
		List<PerformanceManageEvaBean> list0 = mapper.queryManageEvaSecondQueryDUList(condition);
		
		List<PerformanceManageResultHistoryBean> list = new ArrayList<>();
		for(PerformanceManageEvaBean b : list0) {
			PerformanceManageResultHistoryBean bean = new PerformanceManageResultHistoryBean();
			bean.setEhr(b.getEhr());
			bean.setEmpName(b.getName());
			bean.setDu(b.getDu());
			bean.setBu(b.getBu());
			bean.setRm(b.getRm());
			bean.setResult(b.getResult());
			bean.setComments(b.getResultComments());
			
			String year = b.getYear();
			String quarter = b.getQuarter();
			String start = "";
			String end = "";
			if (quarter != null && quarter.equalsIgnoreCase("Q1")) {
				start = "01/01/" + year;  
				end = "31/03/" + year;  
			} else if (quarter != null && quarter.equalsIgnoreCase("Q2")) {
				start = "01/04/" + year;  
				end = "30/06/" + year;  
			} else if (quarter != null && quarter.equalsIgnoreCase("Q3")) {
				start = "01/07/" + year;  
				end = "30/09/" + year;  
			} else if (quarter != null && quarter.equalsIgnoreCase("Q4")) {
				start = "01/10/" + year;  
				end = "31/12/" + year;  
			}
			bean.setBeginDate(start);
			bean.setEndDate(end);
			
			list.add(bean);
		}


		return list;
	}
	
}
