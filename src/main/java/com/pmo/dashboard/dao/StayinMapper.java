package com.pmo.dashboard.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.pmo.dashboard.entity.AddDemand;
import com.pmo.dashboard.entity.StayinCandidate;

public interface StayinMapper
{
    public List<StayinCandidate> queryStayinList(StayinCandidate candidate);
    
    public int queryCandidateCount(StayinCandidate candidate);
    List<LinkedHashMap<String,String>> queryExportData(StayinCandidate candidate);

 
}
