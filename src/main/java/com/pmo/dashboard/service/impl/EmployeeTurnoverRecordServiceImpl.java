package com.pmo.dashboard.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeTurnoverRecordMapper;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeTurnoverRecord;
import com.pom.dashboard.service.EmployeeTurnoverService;

@Service
public class EmployeeTurnoverRecordServiceImpl implements EmployeeTurnoverService{
	
	@Resource 
	private EmployeeTurnoverRecordMapper employeeTurnoverRecordMapper;
	
	@Override 
	public boolean insert(EmployeeTurnoverRecord record){
		if(employeeTurnoverRecordMapper.insertSelective(record)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public List<EmployeeTurnoverRecord> queryList(EmployeeTurnoverRecord record){
		List<EmployeeTurnoverRecord> list = employeeTurnoverRecordMapper.queryList(record);
		return list;
	}
	
	@Override
	public boolean update(EmployeeTurnoverRecord record){
		if(employeeTurnoverRecordMapper.updateByPrimaryKeySelective(record) >0){
			return true;
		}
		return false;
	}
	
	@Override
	public EmployeeTurnoverRecord queryById(String id){
		return employeeTurnoverRecordMapper.queryById(id);
	}
}