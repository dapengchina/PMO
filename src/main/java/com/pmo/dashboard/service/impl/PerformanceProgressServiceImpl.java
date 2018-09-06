package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.entity.PerformanceProgressBean;
import com.pom.dashboard.service.PerformanceProgressService;

@Service
public class PerformanceProgressServiceImpl implements PerformanceProgressService {
	
	@Override
	public List<PerformanceProgressBean> queryPerformancePregressList(){
		List<PerformanceProgressBean> list = new ArrayList<>();
		
		PerformanceProgressBean bean1 = new PerformanceProgressBean("绩效目标审核", "XXX", "通过", "OK");
		PerformanceProgressBean bean2 = new PerformanceProgressBean("绩效初评 ", "XXX", "通过", "OK");
		PerformanceProgressBean bean3 = new PerformanceProgressBean("绩效集体评议 ", "XXX", "通过", "OK");
		PerformanceProgressBean bean4 = new PerformanceProgressBean("审批 ", "XXX", "通过", "OK");
		
		list.add(bean1);
		list.add(bean2);
		list.add(bean3);
		list.add(bean4);
				
		return list;
	}


}
