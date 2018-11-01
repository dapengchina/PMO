package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.CommonMenu;

public interface CommonMenuMapper {
	
	
	   public int insert(CommonMenu cm);
	   
	   public int update(CommonMenu cm);
	   
	   public int delete(String id);
	   
	   public List<CommonMenu> getTopMenu(CommonMenu cm);
	   
	   public List<CommonMenu> getChildMenu(CommonMenu cm);

}
