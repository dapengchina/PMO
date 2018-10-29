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
     * @param bu 统计指定部门下的员工
     * @return 
     * Map<String,Integer>
     */
    List<Map<String, Object>> groupStatByResult(@Param("bu") String bu);

    /**
     * 获取各事业部审批状态
     * HRBP-绩效考评-审批列表
     * @author: Song_Lee
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    List<Map<String, Object>> approvalList(PerformanceQueryCondition condition);

    /**
     * 根据事业部名查询一条记录
     * @author: Song_Lee
     * 2018年10月22日 下午4:47:55
     * @param resultComments
     * @return 
     * List<PerformanceManageEvaBean>
     */
    PerformanceManageEvaBean queryResultComments(@Param("bu") String bu);

    /**
     * 更新审批内容
     * @author: Song_Lee
     * 2018年10月22日 下午5:24:20 
     * void
     */
    void updateComments(@Param("comments") String comments);

    /**
     * 更新审批状态
     * @author: Song_Lee
     * 2018年10月22日 下午5:24:20 
     * void
     */
    void updateStateByBu(@Param("bu") String bu, @Param("state") String state);

}
