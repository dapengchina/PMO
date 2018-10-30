package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.UserAuthority;

public interface UserAuthorityService {
	
	
		List<UserAuthority> queryUserAuthority(String userType);		
		
		List<UserAuthority> queryMenus();
		
		

}
