package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.Resume;

/**
 * 候选人Dao接口
 * 
 * @author dilu
 * @version 1.0， 2017-8-28 10:44:03
 */
public interface ResumeMapper {

	/**
	 * 录入候选人的方法
	 * 
	 * @param resume
	 */
	void add(Resume resume);

	Resume selectTel(String tel);
	
	public List<Resume> queryResumeInfoById(String canID);
	
	int upd(Resume resume);

}
