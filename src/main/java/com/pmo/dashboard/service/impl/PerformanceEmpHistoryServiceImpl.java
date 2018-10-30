package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.PerformanceResultMapper;
import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pom.dashboard.service.PerformanceEmpHistoryService;

@Service
public class PerformanceEmpHistoryServiceImpl implements PerformanceEmpHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceEmpHistoryServiceImpl.class);
	
	@Resource
	PerformanceResultMapper mapper;
	
	@Override
	public List<PerformanceEmpHistoryBean> queryPerformanceEmpCurrentList(PerformanceQueryCondition condition) {
		Calendar c = Calendar.getInstance();	
		condition.setStartYear(c.get(Calendar.YEAR)+ ""); 
		condition.setStartQuarter("Q"+getSeason());
		
		List<PerformanceManageEvaBean> list0 = mapper.queryPerformanceEmpCurrentList(condition);

		List<PerformanceEmpHistoryBean> list = new ArrayList<>();
		
		for(PerformanceManageEvaBean b: list0) {
			
			PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean();
			bean.setYear(b.getYear());
			bean.setQuarter(b.getQuarter());
			bean.setDU(b.getDu());
			bean.setRM(b.getRm());
			bean.setRating(b.getResult());
			bean.setComments(b.getResultComments());
			list.add(bean);
		}

		return list;
	}

	
	public static int getSeason() {		 
		int season = 0;
 
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}


	@Override
	public List<PerformanceEmpHistoryBean> queryPerformanceEmpHistoryList(PerformanceQueryCondition condition) {
		logger.info("queryPerformanceEmpHistoryList.condition=" + condition);
		List<PerformanceManageEvaBean> list0 = mapper.queryPerformanceEmpHistoryList(condition);

		List<PerformanceEmpHistoryBean> list = new ArrayList<>();
		
		for(PerformanceManageEvaBean b: list0) {
			
			PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean();
			bean.setYear(b.getYear());
			bean.setQuarter(b.getQuarter());
			bean.setDU(b.getDu());
			bean.setRM(b.getRm());
			bean.setRating(b.getResult());
			bean.setComments(b.getResultComments());
			bean.setBu(b.getBu());
			bean.setSkill(b.getSkill());
			bean.setRole(b.getRole());
			list.add(bean);
		}

		return list;
	}
	
	@Override
	public String queryCurrentLoginUserEhr(PerformanceQueryCondition condition) {
		List<PerformanceManageEvaBean> list0 = mapper.queryCurrentLoginUserEhr(condition);
		String ehr = "";
		for(PerformanceManageEvaBean b: list0) {
			ehr = b.getEhr();
		}
		return ehr;
	}

}
