package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.controller.PerformanceProgressController;
import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pom.dashboard.service.PerformanceEmpHistoryService;

@Service
public class PerformanceEmpHistoryServiceImpl implements PerformanceEmpHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceEmpHistoryServiceImpl.class);
	
	@Override
	public List<PerformanceEmpHistoryBean> queryPerformanceEmpHistoryList(PerformanceQueryCondition condition) {
		
		List<PerformanceEmpHistoryBean> list = new ArrayList<>();
		
		for(int i=0; i<6; i++) {
			PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean("2018", "Q2", "投资银行交付部", "XXX", "B+", "OK");
			list.add(bean);
		}

		return list;
	}

}
