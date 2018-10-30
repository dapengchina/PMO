package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.HrFeedback;

public interface HrFeedbackService {
	boolean updateCandidateInfo(HrFeedback candidate);
	List<HrFeedback> hrfeedbackQuery(String candidateId);
}
