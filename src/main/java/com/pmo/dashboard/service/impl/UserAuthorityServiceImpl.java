package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.UserAuthorityMapper;
import com.pmo.dashboard.entity.UserAuthority;
import com.pom.dashboard.service.UserAuthorityService;
@Service
public class UserAuthorityServiceImpl implements UserAuthorityService {

	
	@Resource
	private UserAuthorityMapper userAuthorityMapper;
	
	@Override
	public List<UserAuthority> queryUserAuthority(String userType) {
		
		return userAuthorityMapper.queryUserAuthority(userType);
	}

	
	
	@Override
	public List<UserAuthority> queryMenus(){
		
		return userAuthorityMapper.queryMenus();
	}
	
	
}
