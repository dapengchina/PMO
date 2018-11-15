package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeTurnoverRecord;

public interface EmployeeTurnoverRecordMapper {
	int deleteByPrimaryKey(String id);
    int insertSelective(EmployeeTurnoverRecord record);

    List<EmployeeTurnoverRecord> queryList(EmployeeTurnoverRecord record);
    
    EmployeeTurnoverRecord queryById(String id);

    int updateByPrimaryKeySelective(EmployeeTurnoverRecord record);
}