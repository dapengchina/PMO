package com.pmo.dashboard.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.ChinaWorkHourMapper;
import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.WorkHour;
import com.pom.dashboard.service.ChinaWorkHourService;

@Service
public class ChinaWorkHourServiceImpl implements ChinaWorkHourService {
	@Resource
	private ChinaWorkHourMapper chinaWorkHourMapper;
	
	@Override
	public boolean delete(String id) {
		if(chinaWorkHourMapper.deleteByPrimaryKey(id)>0){
            return true;
        }
		return false;
	}

	@Override
	public boolean insert(WorkHour record) {
		if(chinaWorkHourMapper.insertSelective(record)>0){
            return true;
        }
		return false;
	}

	@Override
	public WorkHour selectById(String id) {
		return chinaWorkHourMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(WorkHour record) {
		if(chinaWorkHourMapper.updateByPrimaryKeySelective(record)>0){
            return true;
        }
		return false;
	}

	@Override
	public BigDecimal queryWorkHour(WorkHour workHour) {
		return chinaWorkHourMapper.queryWorkHour(workHour);
	}

}
