package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.CapabilityLabelParam;

public interface CapabilityLabelParamMapper {
    int deleteByPrimaryKey(String id);

    int insert(CapabilityLabelParam record);

    int insertSelective(CapabilityLabelParam record);

    CapabilityLabelParam selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CapabilityLabelParam record);

    int updateByPrimaryKey(CapabilityLabelParam record);
    
    List<CapabilityLabelParam> query(CapabilityLabelParam condition);
    
    List<CapabilityLabelParam> majorcateIds();
    
    String maxSubCate(String majorcateId);
    
    String fatherCapability(String id);
    
    List<CapabilityLabelParam> queryChilds();
    
}