package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pmo.dashboard.entity.vo.PresultVo;

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
     * 根据事业部/交付部查询一条记录
     * @author: xuexuan
     * 2018年10月22日 下午4:47:55
     * @param resultComments
     * @return 
     * List<PerformanceManageEvaBean>
     */
    public PerformanceManageEvaBean queryResultComments(@Param("bu") String bu, @Param("du") String du, @Param("startYear") String startYear, @Param("startQuarter") String startQuarter);

    /**
     * 更新审批内容
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateComments(@Param("comments") String comments, @Param("bu") String bu, @Param("du") String du);

    /**
     * 根据ID集合更新审批状态
     * @author: xuexuan
     * 2018年10月22日 下午5:24:20 
     * void
     */
    public void updateState(@Param("bu") String bu, @Param("du") String du, @Param("rm") String rm, @Param("list") List<String> resultIdList, @Param("state") String state);

    /**
     * @author: xuexuan
     * 2018年10月25日 上午10:33:30 
     * @param map 查询key:eHr staffName du bu rm role skill startYear startQuarter endYear endQuarter finalize
     * void
     */
    public List<PerformanceManageEvaBean> filterQuery(Map<String, Object> map);

    /**
     * 查询指定事业部的审批列表
     * @author: xuexuan
     * 2018年10月25日 下午7:57:34
     * @param bu
     * @return 
     * List<Map<String,Object>>
     */
    public List<Map<String, Object>> listGroupByDU(@Param("bu") String bu, @Param("startYear") String startYear, @Param("startQuarter") String startQuarter);

    /**
     * 查询指定交付部的审批列表
     * @author: xuexuan
     * 2018年10月25日 下午7:57:34
     * @param bu
     * @return 
     * List<Map<String,Object>>
     */
    public List<Map<String, Object>> listGroupByRM(@Param("du") String du, @Param("startYear") String startYear, @Param("startQuarter") String startQuarter);

    /**
     * RM绩效初评
     * @author: xuexuan
     * 2018年10月26日 下午6:24:35 
     * void
     */
    public void preAssessment(@Param("preAssessment") String preAssessment, @Param("state") String state,@Param("id") String id);

    /**
     * 根据resultId获取employeeId、PreAssessment_Result
     * @author: xuexuan
     * 2018年10月26日 下午6:24:35 
     * void
     */
    public Map<String, String> queryEmployeeIdByResultId(String resultId);
    
    /**
     * Employee-绩效结果-当期绩效
     * @param pmb
     * @return
     */
    public PresultVo queryCurrentYearQuarter(PerformanceManageEvaBean pmb);
    
    /**
     * Employee-绩效结果-历史绩效
     * @param pmb
     * @return
     */
    public List<PresultVo> queryPerformanceList(PerformanceManageEvaBean pmb);
}
