package com.pom.dashboard.service;

import java.math.BigDecimal;

import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.WorkHour;

public interface ChinaWorkHourService {
	boolean delete(String id);   // mapper.deleteByPrimaryKey

	boolean insert(WorkHour record); // mapper.insertSelective

    WorkHour selectById(String id); //  mapper.selectByPrimaryKey

    boolean update(WorkHour record);  // mapper.updateByPrimaryKeySelective
    
    BigDecimal queryWorkHour(WorkHour workHour); // mapper.queryWorkHour
}