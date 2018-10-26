package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceManageResultHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;

public interface PerformanceManageEvaService {

    public List<PerformanceManageEvaBean> queryManageEvaFirstDetailList(PerformanceQueryCondition condition);

    public List<PerformanceManageEvaBean> queryManageEvaFirstDetailWithAchieveList(PerformanceQueryCondition condition);

    public List<PerformanceEmpHistoryBean> queryManageEvaSecondDUList(PerformanceQueryCondition condition);

    public List<PerformanceEmpHistoryBean> queryManageEvaSecondBUList(PerformanceQueryCondition condition);

    public List<PerformanceManageEvaBean> queryManageEvaSecondQueryList(PerformanceQueryCondition condition);

    public List<PerformanceManageEvaBean> queryManageEvaSecondQueryDUList(PerformanceQueryCondition condition);

    public List<PerformanceManageResultHistoryBean> queryManageResultHistoryQueryList(PerformanceQueryCondition condition);

    public List<PerformanceManageEvaBean> queryManageEvaFinalList(PerformanceQueryCondition condition);

    /**
     * 统计各考评结果数量
     * @author: xuexuan
     * 2018年10月18日 下午7:01:52
     * @param bu 统计指定部门下的员工
     * @return 
     * Map<String,Integer>
     */
    List<Map<String, Object>> groupStatByResult(String bu);

    /**
     * 获取员工当年当季绩效结果
     * 根据条件筛选HRBP-绩效考评-集体评议当年-当季数据
     * @author: Song_Lee
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    List<PerformanceManageEvaBean> groupEvaList(PerformanceQueryCondition condition);

    /**
     * 获取各事业部当年当季审批状态
     * HRBP-绩效考评-审批列表
     * @author: Song_Lee
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    List<Map<String, Object>> approvalList();

    /**
     * 根据事业部名查询一条记录
     * @author: Song_Lee
     * 2018年10月22日 下午4:47:55
     * @param resultComments
     * @return 
     * List<PerformanceManageEvaBean>
     */
    PerformanceManageEvaBean queryResultComments(String bu);

    /**
     * 更新审批内容
     * @author: Song_Lee
     * 2018年10月22日 下午5:24:20 
     * void
     */
    void updateComments(String comments);

    /**
     * 更新审批状态
     * @author: Song_Lee
     * 2018年10月22日 下午5:24:20 
     * void
     */
    void updateStateByBu(String bu, String state);
}
