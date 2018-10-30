package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CandidateMapper;
import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.dao.InterviewMapper;
import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidateInterview;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Resource
	private CandidateMapper candidateMapper;

	@Resource
	private EmployeeMapper employeeMapper;
	
    @Resource
    private HSBCDeptMapper hsbcDeptMapper;
    
    @Resource
    private CSDeptService CSDeptService;
    
    @Resource
    private InterviewMapper interviewMapper;


	@Override
	public Map<String, List<CandidateInfo>> getInterviewRecordByCandId(String candidateId) {
		List<CandidateInfo> list = candidateMapper.getInterviewRecordByCandId(candidateId);
		Iterator<CandidateInfo> it = list.iterator();
		CandidateInfo candidate = null;
		Map<String, List<CandidateInfo>> map = new LinkedHashMap<String, List<CandidateInfo>>(10);
		List<CandidateInfo> listInfo = null;
		while (it.hasNext()) {
			candidate = it.next();
			String fatherInterviewId = candidate.getFatherInterviewId();
			if (map.containsKey(candidate.getFatherInterviewId())) {
				listInfo = map.get(fatherInterviewId);
			} else {
				listInfo = new LinkedList<CandidateInfo>();
				map.put(fatherInterviewId, listInfo);
			}
			if ("0".equals(candidate.getInterviewStatus())) {
				candidate.setInterviewStatus("通过");
			}else if("1".equals(candidate.getInterviewStatus())){
				candidate.setInterviewStatus("未通过");
			}else{
			    candidate.setInterviewStatus("面试中");
			}
			
			if(null == candidate.getInterviewFeedBack()){
			    candidate.setInterviewFeedBack("");
			}
			
			if("0".equals(candidate.getInterviewType())){
			    candidate.setInterviewType("电话面试");
			}else if("1".equals(candidate.getInterviewType())){
                candidate.setInterviewType("现场面试");
            }
			
			
			listInfo.add(candidate);
		}
		return map;
	}

	@Override
	public List<CandidateInfo> getCandidateList(CandidateInfo candidate) {
		List<CandidateInfo> list = candidateMapper.getCandidateList(candidate);
		Iterator<CandidateInfo> it = list.iterator();
		while (it.hasNext()) {
			candidate = it.next();
			candidate.setCandidateSex("0".equals(candidate.getCandidateSex()) ? "男" : "女");
			candidate.setEnglishLevel("0".equals(candidate.getEnglishLevel()) ? "非工作语言" : "工作语言");
		}
		return list;
	}

	@Override
	public int getCandidateListCount(CandidateInfo candidate) {
		return candidateMapper.getCandidateListCount(candidate);
	}

	@Override
	public boolean lockCandidate(Map<String, Object> params) {
		try {
			int res = candidateMapper.lockCandidate(params);
			if (res > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getBillRate(Employee employee) {
		String billRate = employeeMapper.getBillRate(employee);
		return billRate;
	}

	@Override
	public Employee queryEmployeeById(String employeeId) {
		Employee employee = employeeMapper.queryEmployeeById(employeeId);
		return employee;
	}

	@Override
	public HSBCDept queryHSBCSubDeptById(String hsbcSubDeptId) {
		//return hsbcDeptMapper.queryDemandHSBCSubDeptById(hsbcSubDeptId);
		return null;
	}

	@Override
	public CandidateInterview getNewInterviewRecord(String candidateId, String csSubdeptName) {
		String csSubDeptId = CSDeptService.changeCsSubDeptNameToId(csSubdeptName);
		Map<String, Object> params = new HashMap<>();
		params.put("candidateId", candidateId);
		params.put("csSubDeptId", csSubDeptId);
		CandidateInterview  candidateInfo= interviewMapper.queryNewInterviewByCandidateId(params);
		return candidateInfo;
	}

	@Override
	public CandidateInterview getInteviewer(Map<String, Object> map) {
		return interviewMapper.getInteviewer(map);
	}

}
