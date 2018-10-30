package com.pom.dashboard.service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import com.pmo.dashboard.entity.AddDemand;
import com.pmo.dashboard.entity.StayinCandidate;

public interface StayinService
{
    //查询列表
	List<StayinCandidate> queryStayinList(StayinCandidate candidate);
    //chaxun 数量
	int queryCandidateCount(StayinCandidate candidate);
	//导出查询列表的方法
	void transferExportData( List<LinkedHashMap<String,String>> candidateDatalist,List<String> conditionList,File file);
	//gengju 条件导出查询的列表
    List<LinkedHashMap<String, String>> queryExportData(StayinCandidate candidate);
	//查询相关需求
    public List<AddDemand> queryDemand();
	//更改候选人对应需求方法
    public boolean updateDemand(AddDemand d);
	//变更之后要把更改原来的需求为null
    public boolean updateDemandAfter(AddDemand d1);

    
}
