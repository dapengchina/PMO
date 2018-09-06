package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.NewTree;
import com.pmo.dashboard.entity.UserAuthority;

public interface PerformanceService {
	List<NewTree> transferToMenuList(String currentPageUrl, List<UserAuthority> performanceList);
}
