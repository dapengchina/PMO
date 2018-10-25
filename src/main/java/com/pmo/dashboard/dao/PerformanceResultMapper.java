package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;

public interface PerformanceResultMapper {

    List<PerformanceManageEvaBean> queryManageEvaSecondQueryDUList(PerformanceQueryCondition condition);

    List<PerformanceManageEvaBean> queryPerformanceEmpCurrentList(PerformanceQueryCondition condition);

    List<PerformanceManageEvaBean> queryPerformanceEmpHistoryList(PerformanceQueryCondition condition);

    List<PerformanceManageEvaBean> queryCurrentLoginUserEhr(PerformanceQueryCondition condition);

    List<PerformanceManageEvaBean> queryManageEvaPreviousResult(PerformanceQueryCondition condition);

    /**
     * 统计各考评结果数量
     * @author: xuexuan
     * 2018年10月18日 下午7:01:52
     * @param map查找key:bu du rm startYear endYear startQuarter endQuarter finalize
     * @return 
     * Map<String,Integer>
     */
    public List<Map<String, Object>> groupStatByResult(Map<String, Object> map);

    /**
     * 获取各事业部审批状态
     * HRBP-绩效考评-审批列表
     * @author: xuexuan
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<Map<String, Object>> approvalList(PerformanceQueryCondition condition);

    /**
     * 根据事业部名查询一条记录
     * @author: xuexuan
     * 2018年10月22日 下午4:47:55
     * @param resultComments
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public PerformanceManageEvaBean queryResultComments(@Param("bu") String bu);

    /**
     * 更新审批内容
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateComments(String comments);

    /**
     * 更新审批状态
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateStateByBu(String bu, String state);

    /**
     * @author: xuexuan
     * 2018年10月25日 上午10:33:30 
     * @param map 查询key:eHr staffName du bu rm role skill startYear startQuarter endYear endQuarter finalize
     * void
     */
    public List<PerformanceManageEvaBean> filterQuery(Map<String, Object> map);

}
