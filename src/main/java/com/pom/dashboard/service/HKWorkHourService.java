package com.pom.dashboard.service;

import com.pmo.dashboard.entity.WorkHour;

public interface HKWorkHourService {
	boolean deleteByPrimaryKey(String id);

	boolean insert(WorkHour record);

	boolean insertSelective(WorkHour record);

    WorkHour selectByPrimaryKey(String id);

    boolean updateByPrimaryKeySelective(WorkHour record);

    boolean updateByPrimaryKey(WorkHour record);
}