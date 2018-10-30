package com.pom.dashboard.service;


import org.springframework.stereotype.Service;

import com.pmo.dashboard.entity.TransferDept;


@Service
public interface EmployeeModifyService {

    boolean modifyProperties(TransferDept transferDept);
    boolean modifyRoles(TransferDept transferDept);
    boolean modifyDept(TransferDept transferDept);
    boolean modifyRM(TransferDept transferDept);
}
