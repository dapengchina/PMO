package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;

public interface ExportOfflineOperService {
	String exportOfflineOper(String sheetName, User user );

	String exportSummary(String sheetName, User user) ;
	
}
