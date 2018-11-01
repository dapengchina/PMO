package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;

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
     * 其他筛选条件：bu du staffName eHr rm
     * @author: xuexuan
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<PerformanceManageEvaBean> processingResultList(String bu, String du, String eHr, String staffName, String rm);

    /**
     * 指定RM下员工-当年-当季-绩效列表
     * @author: xuexuan
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<PerformanceManageEvaBean> processingResultListRM(String rm);

    /**
     * 指定BU下员工-当年-当季-绩效列表
     * @author: xuexuan
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<PerformanceManageEvaBean> processingResultListBU(String bu);

    /**
     * 指定DU下员工-当年-当季-绩效列表
     * @author: xuexuan
     * 2018年10月19日 下午3:46:21
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public List<PerformanceManageEvaBean> processingResultListDU(String du);

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
    public PerformanceManageEvaBean queryResultCommentsByBU(String bu);

    /**
     * 根据交付部查询一条记录
     * @author: xuexuan
     * 2018年10月22日 下午4:47:55
     * @param resultComments
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public PerformanceManageEvaBean queryResultCommentsByDU(String du);

    /**
     * 更新所有员工的审批内容
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateComments(String comments);

    /**
     * 更新指定事业部的审批内容
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateCommentsByBU(String comments, String bu);

    /**
     * 更新指定交付部的审批内容
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateCommentsByDU(String comments, String du);

    /**
     * 更新指定事业部审批状态
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateStateByBU(String bu, String state);

    /**
     * 更新指定交付部审批状态
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateStateByDU(String du, String state);

    /**
     * 更新指定RM审批状态
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateStateByRM(String rm, String state);

    /**
     * 更新ID集合审批状态
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateStateByIds(List<String> list, String state);

    /**
     * 查询指定事业部-当年-当季-的审批列表
     * @author: xuexuan
     * 2018年10月25日 下午7:51:24
     * @param bu
     * @return 
     * Map<String,Object>
     */
    public List<Map<String, Object>> listGroupByDU(String bu);

    /**
     * 查询指定交付部的审批列表
     * @author: xuexuan
     * 2018年10月25日 下午7:51:24
     * @param bu
     * @return 
     * Map<String,Object>
     */
    public List<Map<String, Object>> listGroupByRM(String csSubDeptName);

    /**
     * RM绩效初评
     * @author: xuexuan
     * 2018年10月26日 下午6:24:35 
     * void
     */
    public void updatePreAssessmentResult(String preAssessment, String id);

    /**
     * 根据id查询绩效目标设定总表
    * @author: xuexuan
    * 2018年10月30日 上午11:29:03
    * @param id
    * @return 
    */
    public Map<String, Object> queryGoalById(String id);

    /**
     * 更新绩效目标流程表
    * @author: xuexuan
    * 2018年10月30日 上午11:29:03
    * @return 
    */
    public void updateByEmployeeId(String employeeId, String state, String remark);

    /**
     * 根据resultId获取employeeId、PreAssessment_Result
     * @author: xuexuan
     * 2018年10月26日 下午6:24:35 
     * void
     */
    public Map<String, String> queryEmployeeIdByResultId(String resultId);

}
