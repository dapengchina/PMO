package com.pmo.dashboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.UserAuthority;

public interface UserAuthorityMapper {
	
	List<UserAuthority> queryUserAuthority(@Param("userType") String userType);
	
	List<UserAuthority> queryMenus();
	
}
