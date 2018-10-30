package com.pmo.dashboard.dao;

import java.math.BigDecimal;
import java.util.List;

import com.pmo.dashboard.entity.WorkHour;

public interface WorkHourMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkHour record);

    int insertSelective(WorkHour record);

    WorkHour selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkHour record);

    int updateByPrimaryKey(WorkHour record);
    
    BigDecimal queryWorkHour(WorkHour condition);
    
    WorkHour queryMonth(WorkHour condition);
    
    List<WorkHour> queryYear(String year);
}