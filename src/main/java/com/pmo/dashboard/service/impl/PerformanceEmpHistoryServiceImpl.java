package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.controller.PerformanceEmpProgressController;
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
	public List<PerformanceEmpHistoryBean> queryPerformanceEmpHistoryList(PerformanceQueryCondition condition) {
		logger.info("****" + condition);
		List<PerformanceManageEvaBean> list0 = mapper.queryManageEvaSecondQueryDUList(condition);
		
		List<PerformanceEmpHistoryBean> list = new ArrayList<>();
		
		for(PerformanceManageEvaBean b: list0) {
			
			PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean("2018", "Q2", "投资银行交付部", "XXX", "B+", "OK");
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

}
