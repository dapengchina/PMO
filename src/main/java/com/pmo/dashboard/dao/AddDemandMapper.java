package com.pmo.dashboard.dao;


import java.util.List;

import com.pmo.dashboard.entity.AddDemand;

public interface AddDemandMapper {
    //增添新需求
	int addDemand(AddDemand demand);
    //根据相关需求查询模态窗口的需求
	public List<AddDemand> queryDemand();
	//更改需求
	public boolean updateDemand(AddDemand d);
	//变更需求之后变更之前的需求
	public boolean updateDemandAfter(AddDemand d1);
	
	int modifyDemand(AddDemand demand);
	int modifyDemand2(AddDemand demand);
}
