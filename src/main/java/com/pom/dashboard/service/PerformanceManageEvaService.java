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
     * 统计指定RM/所有员工当年当季各绩效等级数量
     * @author: xuexuan
     * 2018年10月18日 下午7:01:52
     * @param bu 统计指定部门下的员工
     * @param du 统计指定交付部下的员工
     * @return 
     * Map<String,Integer>
     */
    public List<Map<String, Object>> groupStatByResultRM(String rm);

    /**
     * 统计指定部门/所有员工当年当季各绩效等级数量
     * @author: xuexuan
     * 2018年10月24日 上午10:36:49
     * @param bu
     * @return 
     * List<Map<String,Object>>
     */
    public List<Map<String, Object>> groupStatByResultBU(String bu);

    /**
     * 统计指定交付部/所有员工当年当季各绩效等级数量
     * @author: xuexuan
     * 2018年10月24日 上午10:36:49
     * @param bu
     * @return 
     * List<Map<String,Object>>
     */
    public List<Map<String, Object>> groupStatByResultDU(String du);

    /**
     * 统计指定时间范围内-已定稿-的各绩效等级数量
     * 初始时间未指定时默认为当年当季
     * @author: xuexuan
     * 2018年10月24日 上午9:36:08
     * @param startYear
     * @param startQuarter
     * @param endYear
     * @param endQuarter
     * @param finalize
     * @return 
     * List<Map<String,Object>>
     */
    public List<Map<String, Object>> groupStatByResultFinalize();

    /**
     * 计算百分比
     * @author: xuexuan
     * 2018年10月24日 上午9:27:33
     * @param list
     * @return 
     * Map<String,Object>
     * 
     */
    public Map<String, Object> percentage(List<Map<String, Object>> list);

    /**
     * 所有员工-当年-当季-绩效列表
     * 其他筛选条件：bu du staffName eHr
     * @author: xuexuan
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<PerformanceManageEvaBean> processingResultList(String bu, String du, String eHr, String staffName, String rm);

    /**
     * 所有员工-当年-当季-最终-绩效列表
     * @author: xuexuan
     * 2018年10月25日 上午10:43:17
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<PerformanceManageEvaBean> finalizeResultList();

    /**
     * 获取各事业部当年当季审批状态
     * HRBP-绩效考评-审批列表
     * @author: xuexuan
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<Map<String, Object>> approvalList();

    /**
     * 根据事业部名查询一条记录
     * @author: xuexuan
     * 2018年10月22日 下午4:47:55
     * @param resultComments
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public PerformanceManageEvaBean queryResultComments(String bu);

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
    public void updateStateByBu(@Param("bu") String bu, @Param("state") String state);
}
