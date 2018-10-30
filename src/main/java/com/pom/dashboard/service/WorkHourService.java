package com.pom.dashboard.service;

import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.WorkHour;

public interface WorkHourService {
	boolean deleteByPrimaryKey(String id);

	boolean insert(WorkHour record);

	boolean insertSelective(WorkHour record);

    ChinaWorkHour selectByPrimaryKey(String id);

    boolean updateByPrimaryKeySelective(WorkHour record);

    boolean updateByPrimaryKey(WorkHour record);
}