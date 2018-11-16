package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CommonUserMenuMapper;
import com.pmo.dashboard.entity.CommonUserMenu;
import com.pom.dashboard.service.CommonUserMenuService;


@Service
public class CommonUserMenuServiceImpl implements CommonUserMenuService{
	
	
	
	@Resource
	private CommonUserMenuMapper commonUserMenuMapper;

	@Override
	public List<CommonUserMenu> getUserMenu(CommonUserMenu cm) {
		return commonUserMenuMapper.selectUserMenu(cm);
	}

}
