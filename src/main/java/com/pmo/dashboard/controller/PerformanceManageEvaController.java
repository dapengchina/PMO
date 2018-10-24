package com.pmo.dashboard.controller;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import org.apache.ibatis.annotations.Param;
import org.json.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.EmployeeInfo;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeInfoService;
import com.pom.dashboard.service.PerformanceEmpHistoryService;
import com.pom.dashboard.service.PerformanceManageEvaService;

/**
 * Performance Management 绩效考评  页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value = "/performanceManageEva")
public class PerformanceManageEvaController {

    private static Logger                logger = LoggerFactory.getLogger(PerformanceManageEvaController.class);

    @Resource
    private PerformanceManageEvaService  manageEvaService;
    @Resource
    private PerformanceEmpHistoryService empHistoryService;
    @Resource
    private CSDeptService                csDeptService;
    @Resource
    private EmployeeInfoService          employeeInfoService;

    @RequestMapping("/queryManageEvaFirstDetailList")
    @ResponseBody
    public String queryManageEvaFirstDetailList(int pageSize, int pageNumber, String showAchievement, PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        logger.debug("showAchievement=" + showAchievement);
        User user = (User) request.getSession().getAttribute("loginUser");
        getDUBU(request, user, condition);
        List<PerformanceManageEvaBean> data;

        if ("true".equals(showAchievement)) {
            data = manageEvaService.queryManageEvaFirstDetailWithAchieveList(condition);
        } else {
            data = manageEvaService.queryManageEvaFirstDetailList(condition);
        }
        Map<String, Object> map = new HashMap<String, Object>();

        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        map.put("total", page.getTotal());
        map.put("rows", page.getList());// update by xuexuan 返回值由data改为page中的list

        //map.putAll(putPercentage(data));// update by xuexuan  列表查询与百分比查询分开 

        ObjectMapper objectMapper = new ObjectMapper();
        String rtn = objectMapper.writeValueAsString(map);
        logger.debug("rtn:" + rtn);
        return rtn;
    }

    private void getDUBU(final HttpServletRequest request, User user, PerformanceQueryCondition condition) {
        condition.setUserId(user.getUserId());
        String[] csBuNames = null;
        if (user.getBu() != null && user.getBu() != "") {
            csBuNames = user.getBu().split(",");
            String csBuName = csBuNames[0];
            request.setAttribute("BU", csBuName);
            logger.debug("Get BU from user: " + csBuName);
        }
        String ehr = empHistoryService.queryCurrentLoginUserEhr(condition);
        EmployeePageCondition employeePageCondition = new EmployeePageCondition();
        employeePageCondition.setCurrentPage("0");
        employeePageCondition.setPageRecordsNum(9);
        employeePageCondition.seteHr(ehr);
        List<EmployeeInfo> list = employeeInfoService.queryEmployeeList(employeePageCondition);
        if (list != null && list.size() > 0) {
            EmployeeInfo emp = list.get(0);
            request.setAttribute("DU", emp.getCsSubDeptName());
            condition.setDu(emp.getCsSubDeptName());
            List<CSDept> dus = csDeptService.queryAllCSDept();
            for (CSDept du : dus) {
                if (du.getCsSubDeptName().equalsIgnoreCase(emp.getCsSubDeptName())) {
                    request.setAttribute("BU", du.getCsBuName());
                    condition.setBu(du.getCsBuName());
                }
            }
        }
    }

    @RequestMapping("/queryManageEvaFinalList")
    @ResponseBody
    public String queryManageEvaFinalList(int pageSize, int pageNumber, String showAchievement, PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        User user = (User) request.getSession().getAttribute("loginUser");
        getDUBU(request, user, condition);
        List<PerformanceManageEvaBean> data = manageEvaService.queryManageEvaFinalList(condition);

        Map<String, Object> map = new HashMap<String, Object>();

        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        map.put("total", page.getTotal());
        map.put("rows", data);

        //map.putAll(putPercentage(data));// update by xuexuan  列表查询与百分比查询分开

        ObjectMapper objectMapper = new ObjectMapper();
        String rtn = objectMapper.writeValueAsString(map);
        logger.debug("rtn:" + rtn);
        return rtn;
    }

    private Map<String, Object> putPercentage(List<?> data) {
        Map<String, Object> map = new HashMap<String, Object>();
        int iA = 0;
        int iBplus = 0;
        int iB = 0;
        int iC = 0;
        int iD = 0;
        int i = 0;
        for (Object obj : data) {
            i++;
            if (obj instanceof PerformanceManageEvaBean) {
                PerformanceManageEvaBean b = (PerformanceManageEvaBean) obj;
                String score = b.getInitialEvalution();
                if ("A".equalsIgnoreCase(score)) {
                    iA++;
                } else if ("B+".equalsIgnoreCase(score)) {
                    iBplus++;
                } else if ("B".equalsIgnoreCase(score)) {
                    iB++;
                } else if ("C".equalsIgnoreCase(score)) {
                    iC++;
                } else if ("D".equalsIgnoreCase(score)) {
                    iD++;
                }
            } else if (obj instanceof PerformanceEmpHistoryBean) {
                iA = 10;
                iBplus = 60;
                iB = 30;
                iC = 22;
                iD = 4;
                i = iA + iBplus + iB + iC + iD;
            }
        }
        map.put("empA", iA);
        map.put("empBplus", iBplus);
        map.put("empB", iB);
        map.put("empC", iC);
        map.put("empD", iD);
        map.put("empSum", i);

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        map.put("percentA", nf.format((float) iA / i));
        map.put("percentBplus", nf.format((float) iBplus / i));
        map.put("percentB", nf.format((float) iB / i));
        map.put("percentC", nf.format((float) iC / i));
        map.put("percentD", nf.format((float) iD / i));
        map.put("percentSum", "100%");

        return map;
    }

    @RequestMapping("/queryManageEvaSecondDUList")
    @ResponseBody
    public Object queryManageEvaSecondDUList(PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<PerformanceEmpHistoryBean> data = manageEvaService.queryManageEvaSecondDUList(condition);
        return data;
    }

    @RequestMapping("/queryPercentage")
    @ResponseBody
    public Object queryPercentage(PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        List<PerformanceEmpHistoryBean> data = manageEvaService.queryManageEvaSecondDUList(condition);

        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(putPercentage(data));

        ObjectMapper objectMapper = new ObjectMapper();
        String rtn = objectMapper.writeValueAsString(map);
        logger.debug("queryPercentage rtn:" + rtn);

        return rtn;
    }

    @RequestMapping("/queryManageEvaSecondBUList")
    @ResponseBody
    public Object queryManageEvaSecondBUList(PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        List<PerformanceEmpHistoryBean> data = manageEvaService.queryManageEvaSecondBUList(condition);
        return data;
    }

    @RequestMapping("/queryManageEvaSecondQueryList")
    @ResponseBody
    public String queryManageEvaSecondQueryList(int pageSize, int pageNumber, PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        logger.debug("condition:" + condition);
        List<PerformanceManageEvaBean> data = manageEvaService.queryManageEvaSecondQueryList(condition);

        Map<String, Object> map = new HashMap<String, Object>();

        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        map.put("total", page.getTotal());
        map.put("rows", data);

        map.putAll(putPercentage(data));

        ObjectMapper objectMapper = new ObjectMapper();
        String rtn = objectMapper.writeValueAsString(map);
        logger.debug("rtn:" + rtn);
        return rtn;
    }

    @RequestMapping("/queryManageEvaSecondQueryDUList")
    @ResponseBody
    public String queryManageEvaSecondQueryDUList(int pageSize, int pageNumber, PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response)
            throws JsonProcessingException {
        logger.warn("condition-----------:" + condition);
        List<PerformanceManageEvaBean> data = manageEvaService.queryManageEvaSecondQueryDUList(condition);

        Map<String, Object> map = new HashMap<String, Object>();

        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        map.put("total", page.getTotal());
        map.put("rows", data);

        map.putAll(putPercentage(data));

        ObjectMapper objectMapper = new ObjectMapper();
        String rtn = objectMapper.writeValueAsString(map);
        logger.debug("rtn:" + rtn);
        return rtn;
    }

    /**
     * 新增百分比计算接口
     * 统计当年当季已定稿的各绩效数量
     * @author: xuexuan
     * 2018年10月23日 下午8:26:48
     * @return 
     * Map<String,Object>
     */
    @RequestMapping("/finalize/percentage")
    @ResponseBody
    public Map<String, Object> percentage() {
        // 查询当年当季已定稿绩效
        List<Map<String, Object>> list = manageEvaService.groupStatByResultFinalize();
        // 计算百分比
        Map<String, Object> rtn = manageEvaService.percentage(list);
        return rtn;
    }

    /**
     * 新增百分比计算接口
     * 统计指定时间范围内已定稿的各绩效数量
     * 未指定时间时默认为当年当季
     * @author: xuexuan
     * 2018年10月23日 下午8:26:48
     * @return 
     * Map<String,Object>
     */
    @RequestMapping(value = "/curRole/percentage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> percentage(HttpServletRequest request) {
        // 判断登录用户类别，根据类别统计
        User user = (User) request.getSession().getAttribute("loginUser");
        List<Map<String, Object>> list = null;
        if ("0".equals(user.getUserType())) {// 登录用户为管理员  TODO  暂统计所有员工
            list = manageEvaService.groupStatByResultBU(null);
        }
        if ("1".equals(user.getUserType())) {// 事业部经理
            list = manageEvaService.groupStatByResultBU(user.getBu());
        }
        if ("3".equals(user.getUserType())) {// 交付部经理
            // 查询交付部名称
            CSDept csDept = csDeptService.queryCSDeptById(user.getDu());
            list = manageEvaService.groupStatByResultDU(csDept.getCsSubDeptName());
        }
        if ("5".equals(user.getUserType())) {// 登录用户为RM
            list = manageEvaService.groupStatByResultRM(user.getNickname());
        }
        // 计算百分比
        Map<String, Object> rtn = manageEvaService.percentage(list);
        return rtn;
    }
}
