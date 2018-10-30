package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.HrFeedbackMapper;
import com.pmo.dashboard.entity.HrFeedback;
import com.pom.dashboard.service.HrFeedbackService;
@Service
public class HrFeedbackServiceImpl implements HrFeedbackService {

	@Resource
	private HrFeedbackMapper hrFeedbackMapper;
	
	@Override
	public boolean updateCandidateInfo(HrFeedback hrFeedback) {

		return hrFeedbackMapper.updateCandidateInfo(hrFeedback);
	}

	@Override
	public List<HrFeedback> hrfeedbackQuery(String candidateId) {
		
		return hrFeedbackMapper.hrfeedbackQuery(candidateId);
	}
}
