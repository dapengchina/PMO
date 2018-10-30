package com.pmo.dashboard.dao;


import com.pmo.dashboard.entity.TransferDept;

public interface ModifyEmployeeMapper {

     boolean modifyproperties(TransferDept transferDept);
     boolean modifyRoles(TransferDept transferDept);
     boolean modifyDept(TransferDept transferDept);
     boolean modifyRM(TransferDept transferDept);
}
