package com.pmo.dashboard.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.EmployeeInfo;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.EmployeeSkill;
import com.pmo.dashboard.entity.NewTree;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pmo.dashboard.entity.Template;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserAuthority;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeInfoService;
import com.pom.dashboard.service.EmployeeSkillService;
import com.pom.dashboard.service.PerformanceEmpHistoryService;
import com.pom.dashboard.service.PerformanceManageEvaService;
import com.pom.dashboard.service.PerformanceService;
import com.pom.dashboard.service.TemplateService;
import com.pom.dashboard.service.UserAuthorityService;

/**
 * Performance 模块的controller, 包含所有的页面跳转，和left slide menu
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value = "/performance")
public class PerformanceController {

    private static Logger                logger       = LoggerFactory.getLogger(PerformanceController.class);

    @Resource
    private UserAuthorityService         userAuthorityService;
    @Resource
    private PerformanceService           performanceService;
    @Resource
    private EmployeeInfoService          employeeInfoService;
    @Resource
    private PerformanceEmpHistoryService empHistoryService;
    @Resource
    private CSDeptService                csDeptService;
    @Resource
    private EmployeeSkillService         employeeSkillService;
    @Resource
    private TemplateService              templateService;
    @Resource
    private PerformanceManageEvaService  performanceManageEvaService;

    @SuppressWarnings("unused")
    private ObjectMapper                 objectMapper = new ObjectMapper();

    @RequestMapping("/performanceLeft")
    public ModelAndView left(final HttpServletRequest request, HttpSession session, final HttpServletResponse response) {
        ModelAndView v = new ModelAndView();
        v.setViewName("performance/performanceLeft");
        return v;
    }

    /**
     * 生成slide menu所需要的符合treeview控件的json
     */
    @RequestMapping("/performanceLeftMenu/{currentPageName}")
    @ResponseBody
    public Object performanceLeftMenu(@PathVariable("currentPageName") String currentPageName, final HttpServletRequest request, HttpSession session, final HttpServletResponse response) {
        User u = (User) session.getAttribute("loginUser");
        List<UserAuthority> list = userAuthorityService.queryUserAuthority(u.getUserType());
        List<UserAuthority> performanceList = new ArrayList<>();
        for (UserAuthority user : list) {
            if (user.getMenuId().indexOf("18") != -1 || user.getMenuId().indexOf("19") != -1) { //18为数据库中employee菜单的id
                performanceList.add(user);
            }
        }
        List<NewTree> topCateList = performanceService.transferToMenuList(currentPageName, performanceList);

        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(topCateList);
            logger.debug("left menu= " + resultString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultString;
    }

    @RequestMapping("/listPage")
    public String listPage(HttpServletRequest request, Model model) {
        return "performance/performanceListPage";
    }

    @RequestMapping("/performanceEmpPBC")
    public String getTMemployee(final HttpServletRequest request, Model model) {
        return "performance/performanceEmpPBC";
    }

    @RequestMapping("/performanceEmpEvaSelf")
    public String getPerformanceEmpEvaSelf(final HttpServletRequest request, Model model) {
        return "performance/performanceEmpEvaSelf";
    }

    @RequestMapping("/performanceEmpEvaProgress")
    public String getPerformanceEmpEvaProgress(final HttpServletRequest request, Model model) {
        return "performance/performanceEmpEvaProgress";
    }

    @RequestMapping("/performanceEmpEvaCurrentPeriod")
    public String getPerformanceEmpEvaCurrentPeriod(final HttpServletRequest request, Model model) {
        return "performance/performanceEmpEvaCurrentPeriod";
    }

    @RequestMapping("/performanceEmpEvaCurrentPeriodDetail")
    public String getPerformanceEmpEvaCurrentPeriodDetail(final HttpServletRequest request, Model model) {
        return "performance/performanceEmpEvaCurrentPeriodDetail";
    }

    @RequestMapping("/performanceEmpEvaHistoryQuery")
    public String getPerformanceEmpEvaHistoryQuery(final HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("loginUser");
        PerformanceQueryCondition condition = new PerformanceQueryCondition();
        condition.setUserId(user.getUserId());
        String ehr = empHistoryService.queryCurrentLoginUserEhr(condition);

        EmployeePageCondition employeePageCondition = new EmployeePageCondition();
        employeePageCondition.setCurrentPage("0");
        employeePageCondition.setPageRecordsNum(9);
        employeePageCondition.seteHr(ehr);
        List<EmployeeInfo> list = employeeInfoService.queryEmployeeList(employeePageCondition);
        if (list != null && list.size() > 0) {
            EmployeeInfo emp = list.get(0);
            request.setAttribute("eHr", emp.geteHr());
            request.setAttribute("staffName", emp.getStaffName());
            request.setAttribute("DU", emp.getCsSubDeptName());
            List<CSDept> dus = csDeptService.queryAllCSDept();
            for (CSDept du : dus) {
                if (du.getCsSubDeptName().equalsIgnoreCase(emp.getCsSubDeptName())) {
                    request.setAttribute("BU", du.getCsBuName());
                }
            }
            EmployeeSkill skillCondition = new EmployeeSkill();
            skillCondition.seteHr(emp.geteHr());
            List<EmployeeSkill> empSkill = employeeSkillService.query(skillCondition);
            for (EmployeeSkill skill : empSkill) {
                System.out.println("*************skill= " + skill.getMainAbility());
                System.out.println("*************role= " + skill.getRole());
                request.setAttribute("skill", skill.getMainAbility());
                request.setAttribute("role", skill.getRole());
            }
        }

        return "performance/performanceEmpEvaHistoryQuery";
    }

    @RequestMapping("/performanceManageTargetApproval")
    public String getPerformanceManageTargetApproval(final HttpServletRequest request, Model model) {
        return "performance/performanceManageTargetApproval";
    }

    @RequestMapping("/performanceManageTargetApprovalFilter")
    public String getPerformanceManageTargetApprovalFilter(final HttpServletRequest request, Model model) {
        return "performance/performanceManageTargetApprovalFilter";
    }

    @RequestMapping("/performanceManageTargetApprovalDetail")
    public String getPerformanceManageTargetApprovalDetail(final HttpServletRequest request, Model model) {
        return "performance/performanceManageTargetApprovalDetail";
    }

    @RequestMapping("/performanceManageEvaFirstDetail")
    public String getPerformanceManageEvaFirstDetail(final HttpServletRequest request, Model model) {
        return "performance/performanceManageEvaFirstDetail";
    }

    @RequestMapping("/performanceManageEvaFirst")
    public String getPerformanceManageEvaFirst(final HttpServletRequest request, Model model) {
        return "performance/performanceManageEvaFirst";
    }

    @RequestMapping("/performanceManageEvaFirstDetailComments")
    public String getPerformanceManageEvaFirstDetailComments(final HttpServletRequest request, Model model) {
        return "performance/performanceManageEvaFirstDetailComments";
    }

    @RequestMapping("/performanceManageEvaSecondDU")
    public String getPerformanceManageEvaSecondDU(final HttpServletRequest request, Model model) {
        // add by xuexuan 返回当前登录用户所在的交付部
        User user = (User) request.getSession().getAttribute("loginUser");
        CSDept csDept = csDeptService.queryCSDeptById(user.getCsdeptId());
        model.addAttribute("du", csDept.getCsSubDeptName());
        return "performance/performanceManageEvaSecondDU";
    }

    @RequestMapping("/performanceManageEvaSecondQuery")
    public String getPerformanceManageEvaSecondQuery(@RequestParam("rm") String rm, final HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("loginUser");
        //getDUBU(request, user);// update by xuexuan 重新计算bu du comments
        model.addAttribute("rm", rm);
        model.addAttribute("bu", user.getBu());
        CSDept csDept = csDeptService.queryCSDeptById(user.getCsdeptId());
        model.addAttribute("du", csDept.getCsSubDeptName());
        // 根据du查询审批内容
        String resultComments = performanceManageEvaService.queryResultCommentsByDU(csDept.getCsSubDeptName()).getResultComments();
        model.addAttribute("resultComments", resultComments);
        return "performance/performanceManageEvaSecondQuery";
    }

    private void getDUBU(final HttpServletRequest request, User user) {
        PerformanceQueryCondition condition = new PerformanceQueryCondition();
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
            List<CSDept> dus = csDeptService.queryAllCSDept();
            for (CSDept du : dus) {
                if (du.getCsSubDeptName().equalsIgnoreCase(emp.getCsSubDeptName())) {
                    request.setAttribute("BU", du.getCsBuName());
                }
            }
        }
    }

    @RequestMapping("/performanceManageEvaSecondBU")
    public String getPerformanceManageEvaSecondBU(final HttpServletRequest request, Model model) {
        // add by xuexuan 返回当前登录用户所在的事业部
        User user = (User) request.getSession().getAttribute("loginUser");
        model.addAttribute("bu", user.getBu());
        return "performance/performanceManageEvaSecondBU";
    }

    @RequestMapping("/performanceManageEvaSecondQueryDU")
    public String getPerformanceManageEvaSecondQueryDU(@RequestParam("du") String du, final HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("loginUser");
        //getDUBU(request, user);// update by xuexuan 重新计算bu du comments
        model.addAttribute("du", du);
        model.addAttribute("bu", user.getBu());
        // 根据bu查询审批内容
        String resultComments = performanceManageEvaService.queryResultCommentsByBU(user.getBu()).getResultComments();
        model.addAttribute("resultComments", resultComments);
        return "performance/performanceManageEvaSecondQueryDU";
    }

    @RequestMapping("/performanceManageEvaFinal")
    public String getPerformanceManageEvaFinal(final HttpServletRequest request, Model model) {
        return "performance/performanceManageEvaFinal";
    }

    @RequestMapping("/performanceManageResultHistoryQuery")
    public String getPerformanceManageResultHistoryQuery(final HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("loginUser");
        //getDUBU(request, user);// update by xuexuan 重新计算bu du comments
        model.addAttribute("BU", user.getBu());
        CSDept csDept = csDeptService.queryCSDeptById(user.getCsdeptId());
        model.addAttribute("DU", csDept.getCsSubDeptName());
        return "performance/performanceManageResultHistoryQuery";
    }

    @RequestMapping("/performanceManageResultHistory")
    public String getPerformanceManageResultHistory(final HttpServletRequest request, Model model) {
        return "performance/performanceManageResultHistory";
    }

    @RequestMapping("/performanceManageTemplateDownload")
    public String getPerformanceManageTemplateDownload(final HttpServletRequest request, Model model) {
        List<Template> list = templateService.list();
        model.addAttribute("type", 1);
        model.addAttribute("list", list);
        return "performance/performanceManageTemplateDownload";
    }

    /**
     * HRBP - performance Assessment - Group Assessment
     * @author: xuexuan
     * 2018年10月16日 上午11:11:02
     * @return 
     * String
     */
    @RequestMapping("/performanceHRBPGroupEva")
    public String getPerformanceHRBPGroupEva(Model model) {
        return "performance/performanceHRBPGroupEva";
    }

    /**
     * HRBP - performance Assessment - Approval
     * @author: xuexuan
     * 2018年10月16日 下午12:03:26
     * @return 
     * String
     */
    @RequestMapping("/performanceHRBPApproval")
    public String getPerformanceHRBPApproval() {
        return "performance/performanceHRBPApproval";
    }

    /**
     * HRBP - Template - Download
     * @author: xuexuan
     * 2018年10月16日 下午12:27:02
     * @return 
     * String
     */
    @RequestMapping("/performanceHRBPTemplateDownload")
    public String getPerformanceHRBPTemplateDownload(Model model) {
        List<Template> list = templateService.list();
        model.addAttribute("type", 2);
        model.addAttribute("list", list);
        return "performance/performanceManageTemplateDownload";
    }

    /**
    * HRBP - Template - Data Upload
    * @author: xuexuan
    * 2018年10月16日 下午12:27:02
    * @return 
    * String
    */
    @RequestMapping("/performanceHRBPTemplateUpload")
    public String getPerformanceHRBPTemplateUpload(Model model) {
        List<Template> list = templateService.list();
        model.addAttribute("list", list);
        return "performance/performanceHRBPTemplateUpload";
    }

    @RequestMapping("/performanceHRBPApprovalDetail")
    public String getPerformanceHRBPApprovalDetail(@RequestParam String bu, Model model) {
        model.addAttribute("bu", bu);
        // 根据bu查询审批内容
        String resultComments = performanceManageEvaService.queryResultCommentsByBU(bu).getResultComments();
        model.addAttribute("resultComments", resultComments);
        return "performance/performanceHRBPApprovalDetail";
    }

    @RequestMapping("/performanceLobApprove")
    public String getPerformanceLobApprove(final HttpServletRequest request, Model model) {
        return "performance/performanceLobApprove";
    }

	@RequestMapping("/performanceLobApproveDetails")
	public String getPerformanceLobDetails(final HttpServletRequest request, Model model){
    	request.setAttribute("bu", request.getParameter("bu"));
		return "performance/performanceLobApproveDetails";
	}

	@RequestMapping("/performanceLobHRReport")
	public String getPerformanceLobHRReport(final HttpServletRequest request, Model model){
		return "performance/performanceLobHRReport";
	}
	
	/**
	 * 获取绩效审批状态名称
	 * @author: xuexuan
	 * 2018年11月1日 下午6:07:07
	 * @param stateId
	 * @return 
	 * Map<String,String>
	 */
	@RequestMapping("/state/{stateId}")
    @ResponseBody
    public Map<String, String> getStateName(@PathVariable("stateId") String stateId) {
        Map<String, String> map = new HashMap<>();
        map.put("stateName", Constants.APPROVAL_STATE.get(stateId));
        return map;
    }
	
	
	
	
	
	
	
	
}
