package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CommonMenuMapper;
import com.pmo.dashboard.entity.CommonMenu;
import com.pom.dashboard.service.CommonMenuService;


@Service
public class CommonMenuServiceImpl implements CommonMenuService{
	
	
	@Resource
	private CommonMenuMapper commonMenuMapper;

	@Override
	public List<CommonMenu> getChildMenu(CommonMenu cm) {
		return commonMenuMapper.getChildMenu(cm);
	}

	@Override
	public List<CommonMenu> getTopMenu(CommonMenu cm) {
		return commonMenuMapper.getTopMenu(cm);
	}

}
