package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CandidateMapper;
import com.pmo.dashboard.dao.InterviewMapper;
import com.pmo.dashboard.dao.RmCandidateMapper;
import com.pmo.dashboard.entity.CandidateInterview;
import com.pmo.dashboard.entity.CandidatePush;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.RmCandidateService;

/**
 * RM后人选人实现类
 * @author blkmk7
 *
 */
@Service
public class RmCandidateServiceImpl implements RmCandidateService {
	
	@Resource
	RmCandidateMapper rmCandidateMapper;
	
	@Resource
	InterviewMapper interviewMapper;
	
	@Resource
	CandidateMapper candidateMapper;
	
	@SuppressWarnings("null")
	@Override
	public List<CandidatePush> queryPushedCandidate(User user, String status,PageCondition pageCondition) {
		Map<String, Object> params = new HashMap<String,Object>();
		String csSubDeptId = user.getCsdeptId();
		String[] csSubDeptIds=csSubDeptId.split(",");
		String userType = user.getUserType();
		String userId = user.getUserId();
		params.put("userId", userId);
		params.put("status", status);
		params.put("pageCondition", pageCondition);
		params.put("name", pageCondition.getName());
		params.put("tel", pageCondition.getTel());
		//计算查询起始行号
		int num = 0;
		if(pageCondition.getPageRecNum()!=null){
			num = (pageCondition.getCurrPage() - 1)*pageCondition.getPageRecNum();
			pageCondition.setPageSize(pageCondition.getPageRecNum());
		}else{
			num = (pageCondition.getCurrPage() - 1)*pageCondition.getPageSize();
		}
		params.put("num", num);
		//查找面试表中登录rm的部门下的最近一次面试的数据关联到推送表
		List<CandidatePush> candidateList = null;
		List<CandidatePush> candidateLista = null;
		int queryCandidateCount = 0;
		int queryCandidateCounta = 0;
        for(int i=0;i<csSubDeptIds.length;i++){
        	params.put("csSubDeptId", csSubDeptIds[i]);
        	if("0".equals(userType)){
    			candidateList = rmCandidateMapper.queryAllPushedCandidate(params);
    		}else if("5".equals(userType)||"12".equals(userType)||"14".equals(userType)){
    			candidateList = rmCandidateMapper.queryPushedCandidate(params);
    			
    		}
        	if(candidateLista==null){
        		candidateLista=candidateList;
        	}else{
        	candidateLista.addAll(candidateList);
        	}
        	if("0".equals(userType)){
    			queryCandidateCount = rmCandidateMapper.queryAllCandidateCount(params);
    		}else if("5".equals(userType)){
    			queryCandidateCount = rmCandidateMapper.queryCandidateCount(params);
    		}
        	queryCandidateCounta+=queryCandidateCount;
		}
	
		
		//将面试表的id关联到推送表的InterviewId
		/*for (CandidatePush candidatePush : candidateList) {
			List<CandidateInterview> interviewList = candidatePush.getInterviewList();
			//candidatePush.setInterviewId(interviewList.get(0).getInterviewId());
			Map<String, Object> params1 = new HashMap<String,Object>();
			params1.put("interviewId", interviewList.get(0).getInterviewId());
			params1.put("candidateId", candidatePush.getCandidateId());
			rmCandidateMapper.updateInterviewId(params1);
		}*/
		//设置总页数
		
		pageCondition.setTotalPage((int) Math.ceil(queryCandidateCounta*1.0 / pageCondition.getPageSize()));
		return candidateLista;
	}

	@Override
	public void addInterview(CandidateInterview candidateInterview,String pushId) {
		interviewMapper.addInterview(candidateInterview);
		Map<String, Object> params = new HashMap<String,Object>();
		String candidateId = candidateInterview.getCandidateId();
		params.put("candidateId", candidateId);
		//候选人表interviewStatus=6表示确认面试
		params.put("interviewStatus", "6");
		//confirmStatus
		candidateMapper.updateInterviewStatusById(params);
	}

	@Override
	public void addNextInterview(CandidateInterview candidateInterview, String pushId) {
		String candidateId = candidateInterview.getCandidateId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("candidateId", candidateId);
		params.put("csSubDeptId", candidateInterview.getCssubDept());
		//获取最新的一轮面试数据
		CandidateInterview newCandidateInterview = interviewMapper.queryNewInterviewByCandidateId(params);
		//设置下一轮面试的fatherid
		candidateInterview.setFatherInterviewId(newCandidateInterview.getFatherInterviewId());
		int newSerial = Integer.valueOf(newCandidateInterview.getInterviewSerial())+1;
		//设置面试轮次加一
		
		candidateInterview.setInterviewSerial(String.valueOf(newSerial));
		interviewMapper.addInterview(candidateInterview);
		//候选人表interviewStatus=6表示面试中
		params.put("interviewStatus", "6");
		candidateMapper.updateInterviewStatusById(params);
	}

	public void interviewBack(String pushId) {
		//1表示已完成
		String status = "1";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("pushId", pushId);
		rmCandidateMapper.updateCandidateStatus(params);
	}

	public CandidateInterview getIntervieInfo(String candidateId) {
		CandidateInterview candidateInterview=interviewMapper.getIntervieInfo(candidateId);
		return candidateInterview;
	}

	public boolean updateInterview(CandidateInterview candidateInterview) {
		boolean result =interviewMapper.updateInterview(candidateInterview);
		Map<String, Object> params = new HashMap<String,Object>();
		String candidateId = candidateInterview.getCandidateId();
		params.put("candidateId", candidateId);
		//候选人表interviewStatus=6表示确认面试
		params.put("interviewStatus", "6");
		boolean flag = candidateMapper.modifyInterviewStatusById(params);
		
		return result&&flag;
	}

	@Override
	public CandidateInterview getConfirminfo(String candidateId) {
		CandidateInterview candidateInterview=interviewMapper.getConfirminfo(candidateId);
		return candidateInterview;
	}

	@Override
	public boolean updateCSinfo(String pushId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CandidatePush> getPushUser(String pushid) {
		Map<String,Object> param = new HashMap<String,Object>();
		if(pushid!= null && !"".equals(pushid)){
			param.put("pushid", pushid);
		}
		return rmCandidateMapper.getPushUser(param);
	}

}
