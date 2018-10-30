package com.pmo.dashboard.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidatePush;

public interface CandidateMapper
{
    List<CandidateInfo> queryCandidateList(CandidateInfo candidate);
    
    int queryCandidateCount(CandidateInfo candidate);
    
    String queryCandidateResumePath(CandidateInfo candidate);
    
    List<LinkedHashMap<String,String>> queryExportData(CandidateInfo candidate);
    
    List<CandidateInfo> queryMyCandidateList(CandidateInfo candidate);
    
    int queryMyCandidateCount(CandidateInfo candidate);
    
    boolean updateCandidateInfo(CandidateInfo candidate);
    
    List<Map<String,String>> queryCusDeptInfo();
    
    CandidateInfo queryCandidateForId(String candidateId);
    
    boolean updateCandidateInterviewStatus(CandidateInfo candidate);
    
    boolean insertCandidatePushData(CandidatePush candidatePush);
    
    boolean updateCandidatePushStatus(CandidatePush candidatePush);
    
    boolean updateCandidateStatus(CandidateInfo candidate);
  
	List<CandidateInfo> queryinterviewFeedBack(CandidateInfo candidate);
	
	List<CandidateInfo> queryinterviewAllFeedBack(CandidateInfo candidate);

	int queryinterviewFeedBackCount(CandidateInfo candidate);
	
	int queryinterviewAllFeedBackCount(CandidateInfo candidate);

	int updateInterviewFeedBack(CandidateInfo candidate);
	
	int queryMyWaitEntryCandidateCount(CandidateInfo candidate);
	
	List<CandidateInfo> queryMyWaitEntryCandidateList(CandidateInfo candidate);
	
	boolean updateCandidateEntryInfo(CandidateInfo candidate);
	
	boolean updateCandidateDelayArrivalDate(CandidateInfo candidate);
	
	boolean updateCandidateAbortInfo(CandidateInfo candidate);
	
	List<Map<String,String>> queryDemandForCandidateId(CandidateInfo candidate);
	
	boolean updateDemandAbortColumnInfo(CandidateInfo candidate);
	
	boolean insertDemandForAbortCandidate(Map<String,String> map);
	
	boolean updateDemandStatusDelay(CandidateInfo candidate);

	List<CandidateInfo> getInterviewRecordByCandId(String candidateId);

	int getCandidateListCount(CandidateInfo candidate);

	List<CandidateInfo> getCandidateList(CandidateInfo candidate);
	
	void updateInterviewStatusById(Map<String, Object> params);
	
    boolean modifyInterviewStatusById(Map<String, Object> params);
	int lockCandidate(Map<String, Object> params);
	
	int updateDemandStatusOfferMade(CandidateInfo candidate);
	boolean updateOnboardCandidate(String candidateId);
	
	List<CandidateInfo> queryBlackList(CandidateInfo candidate);
	
	int queryBlackListCount(CandidateInfo candidate);
	boolean updateCandidatepushed(String pushId);
	boolean deleteInterview(String candidateId);
	String queryPushedCS(String pushId);
	
	int getCount(String candidateId);
}
