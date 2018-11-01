package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.CommonMenu;

public interface CommonMenuService {
	
	   public List<CommonMenu> getChildMenu(CommonMenu cm);
	   
	   public List<CommonMenu> getTopMenu(CommonMenu cm);
	   

}
