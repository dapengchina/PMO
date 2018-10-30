package com.pmo.dashboard.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.ModifyEmployeeMapper;
import com.pmo.dashboard.entity.TransferDept;
import com.pom.dashboard.service.EmployeeModifyService;
@Service
public class EmployeeModifyServiceImp implements EmployeeModifyService {
    
	@Resource
	private ModifyEmployeeMapper modifyEmployeeMapper;
	
	@Override
	public boolean modifyProperties(TransferDept transferDept) {
		return modifyEmployeeMapper.modifyproperties(transferDept);
	}

	@Override
	public boolean modifyRoles(TransferDept transferDept) {
		return modifyEmployeeMapper.modifyRoles(transferDept);
	}

	@Override
	public boolean modifyDept(TransferDept transferDept) {
		return modifyEmployeeMapper.modifyDept(transferDept);
	}

	@Override
	public boolean modifyRM(TransferDept transferDept) {
		
		return modifyEmployeeMapper.modifyRM(transferDept);
	}

}
