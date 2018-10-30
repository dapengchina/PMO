package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.HrFeedback;

public interface HrFeedbackMapper {
	boolean updateCandidateInfo(HrFeedback hrFeedback);

	List<HrFeedback> hrfeedbackQuery(String candidateId);
}
