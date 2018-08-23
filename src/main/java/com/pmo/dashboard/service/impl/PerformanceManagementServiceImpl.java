package com.pmo.dashboard.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.controller.PerformanceManagementController;
import com.pom.dashboard.service.PerformanceManagementService;


@Service
public class PerformanceManagementServiceImpl implements PerformanceManagementService {

	@Resource
    private PerformanceManagementController managementMapper;
}
