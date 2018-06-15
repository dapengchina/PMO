package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CSDeptMapper;
import com.pmo.dashboard.dao.CandidateMapper;
import com.pmo.dashboard.dao.DemandMapper;
import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.dao.RmCandidateMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.CandidatePush;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.QueryModel;
import com.pom.dashboard.service.DemandService;

/**
 * 需求service实现类
 * @author tianzhao
 *
 */

@Service
public class DemandServiceImpl implements DemandService{

	@Resource
	DemandMapper demandMapper;
	
	@Resource
	HSBCDeptMapper hsbcDeptMapper;
	
	@Resource
	CSDeptMapper csDeptMapper;
	
	@Resource
	RmCandidateMapper rmCandidateMapper;
	
	@Resource
	CandidateMapper candidateMapper;
	
	@Override
	public List<Demand> queryDemandList(Demand demand,PageCondition pageCondition,String csBuName,HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(demand.getHsbcDept()!=null) {
//			if(StringUtils.isNotBlank(demand.getHsbcDept().getHsbcDeptName()) || StringUtils.isNotBlank(demand.getHsbcDept().getHsbcSubDeptName())){
//				//查询满足条件的所有部门id
//				List<String> hsbcSubDeptIdList = hsbcDeptMapper.queryHSBCSubDeptId(demand);
//				params.put("hsbcSubDeptIdList", hsbcSubDeptIdList);
//			}
		}
		if(StringUtils.isNotBlank(csBuName)){
			//查询满足事业部的所有交付部
			List<CSDept> csSubDeptList = csDeptMapper.queryCSSubDeptNameByCsBuName(csBuName);
			params.put("csSubDeptList", csSubDeptList);
		}
		params.put("csBuName", csBuName);
		params.put("demand", demand);
		request.getSession().setAttribute("demandParams", params);
		params.put("pageCondition", pageCondition);
		//计算查询起始行号
		int num = (pageCondition.getCurrPage() - 1)*pageCondition.getPageSize();
		params.put("num", num);
		//查询所有满足条件的需求信息
		List<Demand> list = new ArrayList<Demand>();
		if(demand.getFlag()!=null&&"1".equals(demand.getFlag())) {
			list = demandMapper.queryDemandListForCandidate(params);
		}else {
			list = demandMapper.queryDemandList(params);
			//把查询到的结果存到session中
			request.getSession().setAttribute("demandList", demandMapper.queryDemandList(params));
		}
		
		//设置总页数
		int queryDemandCount = 0;
		if(demand.getFlag()!=null&&"1".equals(demand.getFlag())) {
			queryDemandCount = demandMapper.queryDemandCountForCandidate(params);
		}else {
			queryDemandCount = demandMapper.queryDemandCount(params);
		}
		
		pageCondition.setTotalPage((int) Math.ceil(queryDemandCount*1.0 / pageCondition.getPageSize()));
		String[] temp = null;
		HSBCDept hsbcdept = new HSBCDept();
		//将部门信息设置到需求信息中
		for (Demand demands : list) {
			List<HSBCDept> hsbcDept = null;
			if(demands.getHsbcSubDeptId()!=null && !"".equals(demands.getHsbcSubDeptId())){
				temp = demands.getHsbcSubDeptId().split(",");
				hsbcdept.setId(temp[0]);
				hsbcDept = hsbcDeptMapper.queryById(hsbcdept);
				demands.setHsbcDept(hsbcDept.get(0));
			}
			
			
			if(hsbcDept!=null) {
//				if(hsbcDept.getHsbcSubDeptName()==null||"".equals(hsbcDept.getHsbcSubDeptName())) {
//					hsbcDept.setHsbcSubDeptName(hsbcDept.getHsbcDeptName());
//				}
			}else {
				demands.setHsbcDept(new HSBCDept());
			}
			 CSDept csDept=new CSDept();
			 csDept=csDeptMapper.queryCSDeptById(demands.getCsSubDept());
			 demands.setCsSubDept(csDept==null?"":csDept.getCsSubDeptName());
		}
		return list;
	}

	@Override
	public List<Demand> queryAllDemand(Map<String, Object> params) {
		List<Demand> list = demandMapper.queryAllDemand(params);
		String[] temp = null;
		HSBCDept hd = new HSBCDept();
		for (Demand demands : list) {
			if(demands.getHsbcSubDeptId()!=null && !"".equals(demands.getHsbcSubDeptId())){
				temp = demands.getHsbcSubDeptId().split(",");
			}
			//处理hsbcdept
			if(temp!=null && temp.length>1){
				hd.setId(temp[1]);
				List<HSBCDept> list1 = hsbcDeptMapper.queryById(hd);
				if(list1!=null && list1.size()>0){
					demands.setHsbcDeptName(list1.get(0).getName());
				}
			}
			//处理hsbcsubdept
			if(temp!=null && temp.length>2){
				hd.setId(temp[2]);
				List<HSBCDept> list2 = hsbcDeptMapper.queryById(hd);
				if(list2!=null && list2.size()>0){
					demands.setHsbcSubDeptName(list2.get(0).getName());
				}
			}
//			HSBCDept hsbcDept = hsbcDeptMapper.queryDemandHSBCSubDeptById(demands.getHsbcSubDeptId());
//			demands.setHsbcDept(hsbcDept);
			CSDept csDept=new CSDept();
			csDept=csDeptMapper.queryCSDeptById(demands.getCsSubDept());
			demands.setCsSubDept(csDept==null?"":csDept.getCsSubDeptName());
		}
		return list;
	}

	@Override
	public List<Demand> queryOfferDemandList(CandidatePush candidatePush) {
		List<Demand> list = demandMapper.queryOfferDemandList(candidatePush);
		return list;
	}

	@Override
	public void updateCandidateIdById(CandidatePush candidatePush, String demandId, String pushId) {
		String candidateId=candidatePush.getCandidateId();
		String candidateName=candidatePush.getCandidateInfo().getCandidateName();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("candidateId", candidateId);
		params.put("demandId", demandId);
		params.put("pushId", pushId);
		params.put("candidateName", candidateName);
		//表示推送表的已发offer的状态
		params.put("status", "2");
		//表示候选人表的面试状态
		params.put("interviewStatus", "3");
		demandMapper.updateCandidateIdById(params);
		rmCandidateMapper.updateCandidateStatus(params);
		candidateMapper.updateInterviewStatusById(params);
	}

	//gkf add 17-09-15
	@Override
	public boolean updateDemandOnBoardById(Demand demand) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("candidateId", demand.getCandidateId());
		params.put("profilesNo", demand.getProfilesNo());
		params.put("interviewedNo", demand.getInterviewedNo());
		params.put("doNumber", demand.getDoNumber());
		params.put("onboardDate", demand.getOnboardDate());
		params.put("plannedOnboardDate", demand.getPlannedOnboardDate());
		int res = demandMapper.updateDemandOnBoardById(params);
		if(res>0) {
			return true;
		}
		return false;
	}

    @Override
    public Demand queryDemandById(String demandId)
    {
        return demandMapper.queryDemandById(demandId);
    }

	@Override
	public Demand queryDemandByCandidateId(String candidateId) {
		
		return demandMapper.queryDemandBycandidateId(candidateId);
	}

	@Override
	public boolean updateBackForCandidate(Demand demand) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("candidateId", demand.getCandidateId());
		params.put("bgvCleared", demand.getBgvCleared());
		int res = demandMapper.updateBackForCandidate(params);
		if(res>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Demand> getDemand(QueryModel qm) {
		return demandMapper.getDemand(qm);
	}

	@Override
	public int update(Demand demand) {
		return demandMapper.update(demand);
	}
	
}
