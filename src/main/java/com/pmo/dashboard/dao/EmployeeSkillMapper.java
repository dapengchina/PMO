package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeSkill;

public interface EmployeeSkillMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployeeSkill record);

    int insertSelective(EmployeeSkill record);

    EmployeeSkill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployeeSkill record);

    int updateByPrimaryKey(EmployeeSkill record);
    
    List<EmployeeSkill> query(EmployeeSkill condition);
    
    List<String> skills();
    
    List<EmployeeSkill> detail(String eHr);
    
    List<EmployeeSkill> toEdit(String eHr);
    
    List<String> queryEhrIds(String eHr);
    
    String haveSkill(EmployeeSkill condition);
    
    List<EmployeeSkill> toBatch();
    
}