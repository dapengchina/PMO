package com.pmo.dashboard.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.dao.PerformanceEmpPBCMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeImpplan;
import com.pmo.dashboard.entity.EmployeeInfo;
import com.pmo.dashboard.entity.EmployeeKeyevent;
import com.pmo.dashboard.entity.EmployeeKpo;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceEmpKPOBean;
import com.pmo.dashboard.entity.PerformanceEmpKeyEventBean;
import com.pmo.dashboard.entity.PerformanceEmpPBCPlanBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.vo.EmployeePerforGoalVo;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pmo.dashboard.entity.vo.RmApprovalVo;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.DateUtils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeImpplanService;
import com.pom.dashboard.service.EmployeeInfoService;
import com.pom.dashboard.service.EmployeeKeyeventService;
import com.pom.dashboard.service.EmployeeKpoService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeperforgoalService;
import com.pom.dashboard.service.PerformanceEmpHistoryService;
import com.pom.dashboard.service.PerformanceManageEvaService;
import com.pom.dashboard.service.PerformanceMatrixService;
import com.pom.dashboard.service.PerformanceResultService;
import com.pom.dashboard.service.PerformanceService;

/**
 * Performance Management 绩效考评  页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value = "/performanceManageEva")
public class PerformanceManageEvaController {

    private static Logger                logger              = LoggerFactory.getLogger(PerformanceManageEvaController.class);

   
    
    @Resource
	private EmployeeperforgoalService employeeperforgoalService;
	
	@Resource
	private PerformanceMatrixService performanceMatrixService;
	
	@Resource
	private EmployeeKpoService employeeKpoService;
	
	@Resource
	private EmployeeKeyeventService employeeKeyeventService;
	
	@Resource
	private EmployeeImpplanService employeeImpplanService;
	
	@Resource
	private CSDeptService cSDeptService;
	
	private SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private PerformanceResultService performanceResultService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy");
	
	private SimpleDateFormat sf2 = new SimpleDateFormat("MM");
    
    @Resource
    private PerformanceManageEvaService  manageEvaService;
    @Resource
    private PerformanceEmpHistoryService empHistoryService;
    @Resource
    private CSDeptService                csDeptService;
    @Resource
    private EmployeeInfoService          employeeInfoService;
    @Resource
    private PerformanceService           performanceService;
    @Resource
    private EmployeeService              employeeService;
    @Resource
    public PerformanceEmpPBCMapper       performanceEmpPBCMapper;
    /** Management-绩效考评-事业部审批导出文件 **/
    private static String[]              approvalBUTitle     = new String[] { "NO.", "DU", "Year", "Quarter", "Status" };
    private static String[]              approvalBUContent   = new String[] { "NO.", "du", "year", "quarter", "status" };
    /** Management-绩效考评-交付部审批导出文件 **/
    private static String[]              approvalDUTitle     = new String[] { "NO.", "RM", "Year", "Quarter", "Status" };
    private static String[]              approvalDUContent   = new String[] { "NO.", "rm", "year", "quarter", "status" };
    /** Management-绩效目标-审批导出文件 **/
    private static String[]              approvalGoalTitle   = new String[] { "NO.", "E-HR", "Employee Name", "MSA Role", "Skill/Technology", "Submited", "Backbone", "Status" };
    private static String[]              approvalGoalContent = new String[] { "NO.", "E_HR", "STAFF_NAME", "ROLE", "SKILL", "submit", "backbone", "state" };

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
        map.put("rows", data);

        map.putAll(putPercentage(data));

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

        map.putAll(putPercentage(data));

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

    /** 原版本接口均为使用 **/

    /**
     * 新增
     * 百分比计算接口
     * 统计当年当季已定稿的各绩效数量
     * @author: xuexuan
     * 2018年10月23日 下午8:26:48
     * @return 
     * Map<String,Object>
     */
    @RequestMapping("/finalize/percentage")
    @ResponseBody
    public Map<String, Object> percentage() {
        // 统计当年当季已定稿绩效
        List<Map<String, Object>> list = manageEvaService.groupStatByResultFinalize();
        // 计算百分比
        Map<String, Object> rtn = manageEvaService.percentage(list);
        return rtn;
    }

    /**
     * 新增
     * 百分比计算接口
     * 根据登录用户权限-当年-当季-统计各绩效数量
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
        switch (user.getUserType()) {
            case "0":// 登录用户为管理员  TODO xuexuan 暂统计所有员工
                list = manageEvaService.groupStatByResultBU(null);
                logger.error("登录用户未管理员，统计所有员工");
                break;
            case "1":// 事业部经理
                list = manageEvaService.groupStatByResultBU(user.getBu());
                break;
            case "3":// 交付部经理
                CSDept csDept = csDeptService.queryCSDeptById(user.getDu());// 查询交付部名称
                list = manageEvaService.groupStatByResultDU(csDept.getCsSubDeptName());
                break;
            case "5":// 登录用户为RM
                list = manageEvaService.groupStatByResultRM(user.getNickname());
                break;
            default:
                break;
        }
        // 计算百分比
        Map<String, Object> rtn = manageEvaService.percentage(list);
        return rtn;
    }

    /**
     * 新增
     * 百分比计算接口
     * 指定RM-当年-当季-统计各绩效数量
     * @author: xuexuan
     * 2018年10月23日 下午8:26:48
     * @return 
     * Map<String,Object>
     */
    @RequestMapping(value = "/rm/percentage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> percentageRM(@RequestParam("rm") String rm, HttpServletRequest request) {
        // 查询数据
        List<Map<String, Object>> list = manageEvaService.groupStatByResultRM(rm);
        // 计算百分比
        Map<String, Object> rtn = manageEvaService.percentage(list);
        return rtn;
    }

    /**
    * 新增
    * 百分比计算接口
    * 指定DU-当年-当季-统计各绩效数量
    * @author: xuexuan
    * 2018年10月23日 下午8:26:48
    * @return 
    * Map<String,Object>
    */
    @RequestMapping(value = "/du/percentage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> percentageDU(@RequestParam("du") String du, HttpServletRequest request) {
        List<Map<String, Object>> list = manageEvaService.groupStatByResultDU(du);
        // 计算百分比
        Map<String, Object> rtn = manageEvaService.percentage(list);
        return rtn;
    }

    /**
     * 新增
     * 员工-绩效定稿-导出接口
     * @author: xuexuan
     * 2018年10月19日 下午4:21:29
     * @return 
     * ResponseEntity<byte[]>
     * @throws IOException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @RequestMapping("/finalize/result/export")
    public ResponseEntity<byte[]> finalizeResultExport(PerformanceQueryCondition condition) throws IOException, IllegalArgumentException, IllegalAccessException {
        // 查询数据,条件：当年-当季-finalize
        List<PerformanceManageEvaBean> data = manageEvaService.finalizeResultList();
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        performanceService.createSheetDetailList(book, "Latest Performancel", data);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        book.write(os);
        byte[] body = os.toByteArray();
        book.close();
        os.close();
        // 返回结果
        String fileName = null;
        try {
            fileName = new String("绩效定稿.xlsx".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", fileName);// 文件名称

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);

        return responseEntity;
    }

    /**
     * 新增
     * 分页查询员工-绩效定稿-列表
     * 默认为当年当季
     * 其他筛选条件：bu du eHr staffName
     * @author: xuexuan
     * 2018年10月25日 上午11:26:10
     * @param pageSize
     * @param pageNumber
     * @return 
     * Map<String,Object>
     */
    @RequestMapping("/finalize/result/list")
    @ResponseBody
    public Map<String, Object> finalizeResultList(@RequestParam int pageSize, @RequestParam int pageNumber) {
        // 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        // 查询数据,条件：年-季-finalize
        List<PerformanceManageEvaBean> data = manageEvaService.finalizeResultList();
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        // 返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotal());
        map.put("rows", page.getList());
        return map;
    }

    /**
     * 新增
     * 分页查询当前登录RM下员工绩效信息
     * @author: xuexuan
     * 2018年10月19日 下午2:19:57
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/processing/result/list/rm")
    @ResponseBody
    public Map<String, Object> processingResultList(@RequestParam int pageSize, @RequestParam int pageNumber, HttpServletRequest request) throws JsonProcessingException {
        // 判断登录用户类别
        User user = (User) request.getSession().getAttribute("loginUser");
        if (!"5".equals(user.getUserType())) {
            logger.error("当前登录用户不是RM");
        }
        // 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        // 查询条件：当年-当季-rm
        List<PerformanceManageEvaBean> data = manageEvaService.processingResultListRM(user.getNickname());
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        // 返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotal());
        map.put("rows", page.getList());
        return map;
    }

    /**
     * 新增
     * 绩效考评-审批列表
     * @author: xuexuan
     * 2018年10月19日 下午2:19:57
     * @return 
     * String
     */
    @RequestMapping("/assessment/approval/list")
    @ResponseBody
    public List<Map<String, Object>> processingResultList(HttpServletRequest request) throws JsonProcessingException {
        // 判断登录用户类别
        User user = (User) request.getSession().getAttribute("loginUser");
        List<Map<String, Object>> list = null;
        //        list = manageEvaService.listGroupByDU("数字移动事业部");
        //        list = manageEvaService.listGroupByRM("网银业务交付部");
        if ("1".equals(user.getUserType())) {// 事业部经理-根据交付部统计
            list = manageEvaService.listGroupByDU(user.getBu());
        }
        if ("3".equals(user.getUserType())) {// 交付部经理-根据RM统计
            CSDept csDept = csDeptService.queryCSDeptById(user.getCsdeptId());// 查询交付部名称
            list = manageEvaService.listGroupByRM(csDept.getCsSubDeptName());
        } else {
            logger.error("当前登录用户不是事业部经理/交付部经理");
        }
        return list;
    }

    /**
     * 新增
     * 绩效考评-事业部审批-审批提交
     * @author: xuexuan
     * 2018年10月22日 下午3:00:00
     * @param bu  审批的部门
     * @param state 审批状态
     * @return 
     * String
     */
    @RequestMapping("/assessment/approval/bu/submit")
    @ResponseBody
    public String approvalBUSubmit(@RequestParam String bu, @RequestParam String comments) {
        manageEvaService.updateCommentsByBU(comments, bu);
        return "";
    }

    /**
     * 新增
     * 绩效考评-交付部审批-审批提交
     * @author: xuexuan
     * 2018年10月22日 下午3:00:00
     * @param bu  审批的部门
     * @param state 审批状态
     * @return 
     * String
     */
    @RequestMapping("/assessment/approval/du/submit")
    @ResponseBody
    public String approvalDUSubmit(@RequestParam String du, @RequestParam String comments) {
        manageEvaService.updateCommentsByDU(comments, du);
        return "";
    }

    /**
     * 新增
     * 绩效考评-事业部审批-详情页-审批提交
     * @author: xuexuan
     * 2018年10月22日 下午3:00:00
     * @param bu  审批的部门
     * @param state 审批状态
     * @return 
     * String
     */
    @RequestMapping("/assessment/approval/bu/detail/submit")
    @ResponseBody
    public String approvalBUDetailSubmit(@RequestParam String du, @RequestParam String state) {
        manageEvaService.updateStateByDU(du, state);
        return "";
    }

    /**
     * 新增
     * 绩效考评-交付部审批-详情页-审批提交
     * @author: xuexuan
     * 2018年10月22日 下午3:00:00
     * @param bu  审批的部门
     * @param state 审批状态
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/assessment/approval/du/detail/submit")
    @ResponseBody
    public String approvalDUDetailSubmit(@RequestParam String rm, @RequestParam String state) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<String,Object>();
    	try{
        	manageEvaService.updateStateByRM(rm, state);
        	map.put("msg", "审批成功");
        	map.put("code", "1");
        }catch(Exception e){
        	map.put("msg", "审批失败");
        	map.put("code", "0");
        }
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 绩效考评-事业部审批导出
     * @author: xuexuan
     * 2018年10月26日 下午2:52:58
     * @return 
     * ResponseEntity<byte[]>
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @RequestMapping("/assessment/approval/bu/export")
    public ResponseEntity<byte[]> approvalBUExport(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException, IOException {
        // 获取登录用户所在事业部
        User user = (User) request.getSession().getAttribute("loginUser");
        // 审批列表
        List<Map<String, Object>> list = manageEvaService.listGroupByDU(user.getBu());
        // 员工绩效列表-当年-当季-BU
        List<PerformanceManageEvaBean> data = manageEvaService.processingResultListBU(user.getBu());
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        createSheetApproval(book, list, approvalBUTitle, approvalBUContent);
        performanceService.createSheetDetailList(book, "performance detail", data);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        book.write(os);
        byte[] body = os.toByteArray();
        book.close();
        os.close();
        // 返回结果
        String fileName = null;
        try {
            fileName = new String("事业部经理审批.xlsx".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", fileName);// 文件名称

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);

        return responseEntity;
    }

    /**
     * 绩效考评-交付部审批导出
     * @author: xuexuan
     * 2018年10月26日 下午2:53:00
     * @return 
     * ResponseEntity<byte[]>
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @RequestMapping("/assessment/approval/du/export")
    public ResponseEntity<byte[]> approvalDUExport(HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException, IOException {
        // 获取登录用户所在交付部
        User user = (User) request.getSession().getAttribute("loginUser");
        CSDept csDept = csDeptService.queryCSDeptById(user.getCsdeptId());
        // 审批列表
        List<Map<String, Object>> list = manageEvaService.listGroupByRM(csDept.getCsSubDeptName());
        // 员工绩效列表-当年-当季-DU
        List<PerformanceManageEvaBean> data = manageEvaService.processingResultListDU(csDept.getCsSubDeptName());
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        createSheetApproval(book, list, approvalDUTitle, approvalDUContent);
        performanceService.createSheetDetailList(book, "performance detail", data);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        book.write(os);
        byte[] body = os.toByteArray();
        book.close();
        os.close();
        // 返回结果
        String fileName = null;
        try {
            fileName = new String("交付部经理审批.xlsx".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", fileName);// 文件名称

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);

        return responseEntity;
    }

    /**
     * 新增
     * 绩效考评-初评-RM绩效评级-评级提交
     * @author: xuexuan
     * 2018年10月22日 下午3:00:00
     * @param id  绩效标识
     * @param grade 绩效等级
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/assessment/grade/rm/submit")
    @ResponseBody
    public String approvalRMSubmit(@RequestParam("id") String resultId, @RequestParam("grade") String preAssessment) throws JsonProcessingException {
    	Map<String,Object> map = new HashMap<String,Object>();
    	try{
    		manageEvaService.updatePreAssessmentResult(preAssessment,SysConstant.PRESULT_DRAFT, resultId);
    	    map.put("msg", "初评成功");
    	    map.put("code", "1");
    	}catch(Exception e){
    		map.put("msg", "初评失败");
    	    map.put("code", "0");
    	}
    	
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 新增
     * 绩效考评-初评-RM审批-审批提交
     * @author: xuexuan
     * 2018年10月22日 下午3:00:00
     * @param ids  审批ID集合
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/assessment/approval/rm/submit")
    @ResponseBody
    public String approvalRMDetailSubmit(@RequestParam("ids") String ids) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<String,Object>();
    	try{
        	List<String> list = Arrays.asList(ids.split(","));
            if (list.size() > 0) {
                manageEvaService.updateStateByIds(list, Constants.APPROVAL_DU);
            }
            map.put("msg", "提交成功");
            map.put("code", "1");
        }catch(Exception e){
        	map.put("msg", "提交失败");
            map.put("code", "0");
        }
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 绩效目标审批列表
     * @author: xuexuan
     * 2018年10月29日 下午5:07:09
     * @param pageSize
     * @param pageNumber
     * @param submit
     * @param backbone
     * @param state
     * @param request
     * @return 
     * Map<String,Object>
     */
    @RequestMapping(value = "/assessment/goal/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> goalList(
    		@RequestParam("pageSize") int pageSize, 
    		@RequestParam("pageNumber") int pageNumber, 
    		@RequestParam(value = "submit", required = false) String submit,
            @RequestParam(value = "backbone", required = false) String backbone, 
            @RequestParam(value = "state", required = false) String state, 
            HttpServletRequest request) {
    	
        User user = (User) request.getSession().getAttribute("loginUser");
        if (!"5".equals(user.getUserType())) {
            logger.error("当前登录用户不是RM");
        }
        RmApprovalVo rv = new RmApprovalVo();
        rv.setRmUserID(user.getUserId());
        if("".equals(submit)){
        	rv.setState("-1");//查全部
        }else{
        	rv.setState(submit);
        }
        if("".equals(state)){
        	rv.setState("-999");//查全部
        }else{
        	rv.setState(state);
        }
        // 分页获取该RM下所有员工
        PageHelper.startPage(pageNumber, pageSize);
        List<RmApprovalVo> list = employeeService.rmApprovalList(rv);
        if(list!=null && list.size()>0){
        	for(int i=0;i<list.size();i++){
        		if(list.get(i).getState()!=null && !"".equals(list.get(i).getState())){
        			list.get(i).setStateName(SysConstant.getPerforStateMap().get(list.get(i).getState()).toString());
        		}else{
        			list.get(i).setStateName("未提交");
        		}
        	}
        }
        PageInfo<RmApprovalVo> page = new PageInfo<RmApprovalVo>(list);
        Map<String, Object> rtn = new HashMap<String, Object>();
        rtn.put("total", page.getTotal());
        rtn.put("rows", list);
        return rtn;
    }

    /**
     * 绩效目标审批列表
     * @author: xuexuan
     * 2018年10月29日 下午5:07:09
     * @param id 员工id
     * @param state 审批状态
     * @param comments 评语
     * @return 
     * Map<String,Object>
     */
    @RequestMapping(value = "/assessment/goal/submit", method = RequestMethod.POST)
    @ResponseBody
    public String goalSubmit(@RequestParam("id") String employeeid, @RequestParam("state") String state, @RequestParam("comments") String comments) {
        manageEvaService.updateByEmployeeId(employeeid, state, comments);
        return "";
    }

    /**
     * 绩效目标审批列表-导出
     * @author: xuexuan
     * 2018年10月29日 下午6:44:40
     * @return 
     * ResponseEntity<byte[]>
     * @throws IOException 
     */
    @RequestMapping("/goal/export")
    public ResponseEntity<byte[]> goalExport(HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("loginUser");
        if (!"5".equals(user.getUserType())) {
            logger.error("当前登录用户不是RM");
        }
        RmApprovalVo rv = new RmApprovalVo();
        rv.setRmUserID(user.getUserId());
        // 查询数据
        List<RmApprovalVo> list = employeeService.rmApprovalList(rv);
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        Row row;
        Cell cell;
        Sheet sheet = book.createSheet();
        // 创建标题
        row = sheet.createRow(0);
        for (int c = 0; c < approvalGoalTitle.length; c++) {
            cell = row.createCell(c);
            cell.setCellValue(approvalGoalTitle[c]);
        }
        // 创建数据
        for (int r = 0; r < list.size(); r++) {
            row = sheet.createRow(r + 1);
            cell = row.createCell(0);
            cell.setCellValue(r + 1);
            for (int c = 1; c < approvalGoalContent.length; c++) {
                cell = row.createCell(c);
                if(c==1){
                	 cell.setCellValue(list.get(r).getEhr());
                }
                if(c==2){
                	 cell.setCellValue(list.get(r).getEmployeeName());
                }
                if(c==3){
                	 cell.setCellValue(list.get(r).getMsaRole());
                }
                if(c==4){
                	 cell.setCellValue(list.get(r).getSkill());
                }
                if(c==5){
                	 if(list.get(r).getState()!=null && !"".equals(list.get(r).getState())){
                		 if(!list.get(r).getState().equals(SysConstant.PERFOR_DRAFT_STATE)){
                			 cell.setCellValue("已提交"); 
                		 }
                	 }else{
                		 cell.setCellValue("未提交"); 
                	 }
                }
                if(c==6){
                	 cell.setCellValue("");
                }
                if(c==7){
                	 cell.setCellValue((String)SysConstant.getPerforStateMap().get(list.get(r).getState()));
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        book.write(os);
        byte[] body = os.toByteArray();
        book.close();
        os.close();
        // 返回结果
        String fileName = null;
        try {
            fileName = new String("绩效目标.xlsx".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", fileName);// 文件名称

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);

        return responseEntity;
    }


    /**
     * 绩效设定总表
     * @author: xuexuan
     * 2018年10月30日 上午11:44:34
     * @param id 绩效设定目标总表员工id
     * void
     */
    @RequestMapping("/goal/{id}")
    @ResponseBody
    public Map<String, Object> queryGoal(@PathVariable("id") String id) {
        Map<String, Object> map = manageEvaService.queryGoalById(id);
        if (map == null) {
            logger.error("=====绩效目标总表无该员工信息，请检查！员工employeeid=" + id);
        }
        return map;
    }

    /**
     * 员工重点工作
     * @author: xuexuan
     * 2018年10月30日 上午11:44:34
     * @param employeeId 员工重点工作employeeId 
     * void
     */
    @RequestMapping("/kpo/{employeeId}")
    @ResponseBody
    public List<PerformanceEmpKPOBean> queryKPO(@PathVariable("employeeId") String employeeId) {
        List<PerformanceEmpKPOBean> list = performanceEmpPBCMapper.queryPerformanceEmpKPOList(employeeId);
        return list;
    }

    /**
     * 员工关键事件
     * @author: xuexuan
     * 2018年10月30日 上午11:44:34
     * @param employeeId 员工重点工作employeeId 
     * void
     */
    @RequestMapping("/keyevent/{employeeId}")
    @ResponseBody
    public List<PerformanceEmpKeyEventBean> queryKeyEvent(@PathVariable("employeeId") String employeeId) {
        List<PerformanceEmpKeyEventBean> list = performanceEmpPBCMapper.queryPerformanceEmpEventList(employeeId);
        return list;
    }

    /**
     * 员工个人能力
     * @author: xuexuan
     * 2018年10月30日 上午11:44:34
     * @param employeeId 员工重点工作employeeId 
     * void
     */
    @RequestMapping("/plan/{employeeId}")
    @ResponseBody
    public List<PerformanceEmpPBCPlanBean> queryPlan(@PathVariable("employeeId") String employeeId) {
        List<PerformanceEmpPBCPlanBean> list = performanceEmpPBCMapper.queryPerformanceEmpPlanList(employeeId);
        return list;
    }

    /**
     * 导出excel
     * @author: xuexuan
     * 2018年10月30日 上午11:44:34
     * void
     */
    private void createSheetApproval(XSSFWorkbook book, List<Map<String, Object>> data, String[] titleAry, String[] contentAry) {
        // 创建工作簿
        Sheet sheet = book.createSheet("approval list");
        Row row;
        Cell cell;
        // 创建表头
        row = sheet.createRow(0);
        for (int c = 0; c < titleAry.length; c++) {
            cell = row.createCell(c);
            cell.setCellValue(titleAry[c]);
        }
        // 创建表内容
        for (int r = 0; r < data.size(); r++) {
            row = sheet.createRow(r + 1);// 从第二行开始
            cell = row.createCell(0);// 第一列为序号
            cell.setCellValue(r + 1);
            for (int c = 1; c < contentAry.length; c++) {
                cell = row.createCell(c);
                if ("status".equalsIgnoreCase(contentAry[c])) {
                    String stateName = Constants.APPROVAL_STATE.get(data.get(r).get(contentAry[c]));
                    cell.setCellValue(stateName);
                } else {
                    cell.setCellValue((String) data.get(r).get(contentAry[c]));
                }
            }
        }
    }

    /**
     * Management-绩效目标-审批-详情页面
     * @return
     */
    @RequestMapping("/detailPage/{employeeid}")
    public String detailPage(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/management/performanceDetail";
    }
    
    /**
     * Management-绩效目标-审批-详情页面数据
     * @return
     * @throws JsonProcessingException 
     */
    @RequestMapping("/detailData/{employeeid}")
    @ResponseBody
    public String detailData(HttpServletRequest request,@PathVariable("employeeid") String employeeid) throws JsonProcessingException{
    	//HttpSession session = request.getSession();
    	Employee emp = employeeService.queryEmployeeById(employeeid);
		Map<String,Object> map = new HashMap<String,Object>();
		//Ehr
		map.put("ehr", emp.geteHr());
		//EmployeeName
		map.put("staffname", emp.getStaffName());
		//查询中软部门信息
		CSDept csdept = cSDeptService.queryCSDeptById(emp.getCsSubDept());
		map.put("department", csdept!=null?csdept.getCsSubDeptName():"");
		//查询职位信息
		Employee employee = employeeService.queryEmployeeById(employeeid);
		map.put("role", employee!=null?employee.getRole():"");
		//查询考核主管
		Employee employee2 = employeeService.queryEmployeeById(employee.getRmUserId());
		map.put("assessmentSupervisor", employee2!=null?employee2.getStaffName():"");
		
		//查询重点工作表
		EmployeeKpo eo = new EmployeeKpo();
		eo.setEmployeeid(employeeid);//员工ID
		eo.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		eo.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKpo> kpoList = employeeKpoService.getEmployeeKpo(eo);
		
		//查询关键事件表
		EmployeeKeyevent ek = new EmployeeKeyevent();
		ek.setEmployeeid(employeeid);//员工ID
		ek.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		ek.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKeyevent> keyeventList = employeeKeyeventService.getEmployeeKeyEvent(ek);
		
		/**
		 * 重点工作数据和关键事件数据整合
		 */
		List<EmployeePerforGoalVo> data1 = new ArrayList<EmployeePerforGoalVo>();
		if(kpoList!=null && kpoList.size()>0){
			for(int i=0;i<kpoList.size();i++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(kpoList.get(i).getId());
				perforgoal.setIndex(kpoList.get(i).getIndex());
				perforgoal.setKeyaction(kpoList.get(i).getKeyaction());
				perforgoal.setPhasegoal(kpoList.get(i).getPhasegoal());
				perforgoal.setWeightrate(kpoList.get(i).getWeightrate());
				perforgoal.setEmployeeid(kpoList.get(i).getEmployeeid());
				perforgoal.setDescription(kpoList.get(i).getDescription());
				perforgoal.setCreatedate(kpoList.get(i).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.PRIORITY_WORK);//重点工作
				
				data1.add(perforgoal);
			}
		}
		if(keyeventList!=null && keyeventList.size()>0){
			for(int j=0;j<keyeventList.size();j++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(keyeventList.get(j).getId());
				perforgoal.setIndex(keyeventList.get(j).getIndex());
				perforgoal.setKeyaction(keyeventList.get(j).getKeyaction());
				perforgoal.setPhasegoal(keyeventList.get(j).getPhasegoal());
				perforgoal.setWeightrate(keyeventList.get(j).getWeightrate());
				perforgoal.setEmployeeid(keyeventList.get(j).getEmployeeid());
				perforgoal.setDescription(keyeventList.get(j).getDescription());
				perforgoal.setCreatedate(keyeventList.get(j).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.KEY_EVENTS);//关键事件
				
				data1.add(perforgoal);
			}
		}
		
		//查询个人能力提升计划表
		EmployeeImpplan el = new EmployeeImpplan();
		el.setEmployeeid(employeeid);//员工ID
		el.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		el.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeImpplan> planList = employeeImpplanService.getEmployeeImpplan(el);
	    if(planList!=null && planList.size()>0){
	    	for(int k=0;k<planList.size();k++){
	    		if(planList.get(k).getDealine()!=null && !"".equals(planList.get(k).getDealine())){
	    			planList.get(k).setDealineString(sf3.format(planList.get(k).getDealine()));
	    		}
		    }
	    }
	    
	    //查询员工绩效总表，获取自评信息
	    Employeeperforgoal epg = new Employeeperforgoal();
	    epg.setEmployeeid(employeeid);
	    epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
	    epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
	    Employeeperforgoal reperfor = employeeperforgoalService.getEmpPerforgoal(epg);
		
		//查询绩效结果表，获取comments
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(emp.geteHr());
		pmb.setYear(sf.format(new Date()));
		pmb.setQuarter(String.valueOf(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))));
		PresultVo pv = performanceResultService.getPerformance(pmb);
	    
		map.put("comments", pv!=null?pv.getResult_Comments():"");
		map.put("selfassessment", reperfor!=null?reperfor.getSelfassessment():"");
		map.put("state", reperfor!=null?reperfor.getState():"");
		map.put("data", data1);
		map.put("plan", planList);
		
		return objectMapper.writeValueAsString(map);
    }

    /**
     * Management-绩效考评-初评-详情页面
     * @return
     */
    @RequestMapping("/approvalDetailPage/{employeeid}/{pid}")
    public String approvalDetailPage(HttpServletRequest request,@PathVariable("employeeid") String employeeid,@PathVariable("pid") String pid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	model.addAttribute("pid", pid);
    	return "performance/management/performanceApprovalDetail";
    }
    
    /**
     * Management-绩效考评-初评-详情页面数据
     * @return
     * @throws JsonProcessingException 
     */
    @RequestMapping("/approvalDetailData/{employeeid}")
    @ResponseBody
    public String approvalDetailData(HttpServletRequest request,@PathVariable("employeeid") String employeeid) throws JsonProcessingException{
    	//HttpSession session = request.getSession();
    	Employee emp = employeeService.queryEmployeeById(employeeid);
		Map<String,Object> map = new HashMap<String,Object>();
		//Ehr
		map.put("ehr", emp.geteHr());
		//EmployeeName
		map.put("staffname", emp.getStaffName());
		//查询中软部门信息
		CSDept csdept = cSDeptService.queryCSDeptById(emp.getCsSubDept());
		map.put("department", csdept!=null?csdept.getCsSubDeptName():"");
		//查询职位信息
		Employee employee = employeeService.queryEmployeeById(employeeid);
		map.put("role", employee!=null?employee.getRole():"");
		//查询考核主管
		Employee employee2 = employeeService.queryEmployeeById(employee.getRmUserId());
		map.put("assessmentSupervisor", employee2!=null?employee2.getStaffName():"");
		
		//查询重点工作表
		EmployeeKpo eo = new EmployeeKpo();
		eo.setEmployeeid(employeeid);//员工ID
		eo.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		eo.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKpo> kpoList = employeeKpoService.getEmployeeKpo(eo);
		
		//查询关键事件表
		EmployeeKeyevent ek = new EmployeeKeyevent();
		ek.setEmployeeid(employeeid);//员工ID
		ek.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		ek.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKeyevent> keyeventList = employeeKeyeventService.getEmployeeKeyEvent(ek);
		
		/**
		 * 重点工作数据和关键事件数据整合
		 */
		List<EmployeePerforGoalVo> data1 = new ArrayList<EmployeePerforGoalVo>();
		if(kpoList!=null && kpoList.size()>0){
			for(int i=0;i<kpoList.size();i++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(kpoList.get(i).getId());
				perforgoal.setIndex(kpoList.get(i).getIndex());
				perforgoal.setKeyaction(kpoList.get(i).getKeyaction());
				perforgoal.setPhasegoal(kpoList.get(i).getPhasegoal());
				perforgoal.setWeightrate(kpoList.get(i).getWeightrate());
				perforgoal.setEmployeeid(kpoList.get(i).getEmployeeid());
				perforgoal.setDescription(kpoList.get(i).getDescription());
				perforgoal.setCreatedate(kpoList.get(i).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.PRIORITY_WORK);//重点工作
				
				data1.add(perforgoal);
			}
		}
		if(keyeventList!=null && keyeventList.size()>0){
			for(int j=0;j<keyeventList.size();j++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(keyeventList.get(j).getId());
				perforgoal.setIndex(keyeventList.get(j).getIndex());
				perforgoal.setKeyaction(keyeventList.get(j).getKeyaction());
				perforgoal.setPhasegoal(keyeventList.get(j).getPhasegoal());
				perforgoal.setWeightrate(keyeventList.get(j).getWeightrate());
				perforgoal.setEmployeeid(keyeventList.get(j).getEmployeeid());
				perforgoal.setDescription(keyeventList.get(j).getDescription());
				perforgoal.setCreatedate(keyeventList.get(j).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.KEY_EVENTS);//关键事件
				
				data1.add(perforgoal);
			}
		}
		
		//查询个人能力提升计划表
		EmployeeImpplan el = new EmployeeImpplan();
		el.setEmployeeid(employeeid);//员工ID
		el.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		el.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeImpplan> planList = employeeImpplanService.getEmployeeImpplan(el);
	    if(planList!=null && planList.size()>0){
	    	for(int k=0;k<planList.size();k++){
	    		if(planList.get(k).getDealine()!=null && !"".equals(planList.get(k).getDealine())){
	    			planList.get(k).setDealineString(sf3.format(planList.get(k).getDealine()));
	    		}
		    }
	    }
	    
	    //查询员工绩效总表，获取自评信息
	    Employeeperforgoal epg = new Employeeperforgoal();
	    epg.setEmployeeid(employeeid);
	    epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
	    epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
	    Employeeperforgoal reperfor = employeeperforgoalService.getEmpPerforgoal(epg);
		
		//查询绩效结果表，获取comments
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(emp.geteHr());
		pmb.setYear(sf.format(new Date()));
		pmb.setQuarter(String.valueOf(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))));
		PresultVo pv = performanceResultService.getPerformance(pmb);
	    
		map.put("comments", pv!=null?pv.getResult_Comments():"");
		map.put("directresult", pv!=null?pv.getDirect_Supervisor_Assessment_Result():"");
		map.put("selfassessment", reperfor!=null?reperfor.getSelfassessment():"");
		map.put("state", reperfor!=null?reperfor.getState():"");
		map.put("data", data1);
		map.put("plan", planList);
		
		return objectMapper.writeValueAsString(map);
    }

    /**
     * Management-绩效考评-审批-员工详情页面
     * @param request
     * @param employeeid
     * @param model
     * @return
     */
    @RequestMapping("/approvalDuDetailPage/{employeeid}")
    public String approvalDuDetailPage(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/management/performanceApprovalDuDetail";
    }
    
    /**
     * Management-绩效考评-审批-员工详情页面数据
     * @param request
     * @param employeeid
     * @param model
     * @return
     * @throws JsonProcessingException 
     */
    @RequestMapping("/approvalDuDetailData/{employeeid}")
    @ResponseBody
    public String approvalDuDetailData(HttpServletRequest request,@PathVariable("employeeid") String employeeid) throws JsonProcessingException{
    	//HttpSession session = request.getSession();
    	Employee emp = employeeService.queryEmployeeById(employeeid);
		Map<String,Object> map = new HashMap<String,Object>();
		//Ehr
		map.put("ehr", emp.geteHr());
		//EmployeeName
		map.put("staffname", emp.getStaffName());
		//查询中软部门信息
		CSDept csdept = cSDeptService.queryCSDeptById(emp.getCsSubDept());
		map.put("department", csdept!=null?csdept.getCsSubDeptName():"");
		//查询职位信息
		Employee employee = employeeService.queryEmployeeById(employeeid);
		map.put("role", employee!=null?employee.getRole():"");
		//查询考核主管
		Employee employee2 = employeeService.queryEmployeeById(employee.getRmUserId());
		map.put("assessmentSupervisor", employee2!=null?employee2.getStaffName():"");
		
		//查询重点工作表
		EmployeeKpo eo = new EmployeeKpo();
		eo.setEmployeeid(employeeid);//员工ID
		eo.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		eo.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKpo> kpoList = employeeKpoService.getEmployeeKpo(eo);
		
		//查询关键事件表
		EmployeeKeyevent ek = new EmployeeKeyevent();
		ek.setEmployeeid(employeeid);//员工ID
		ek.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		ek.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeKeyevent> keyeventList = employeeKeyeventService.getEmployeeKeyEvent(ek);
		
		/**
		 * 重点工作数据和关键事件数据整合
		 */
		List<EmployeePerforGoalVo> data1 = new ArrayList<EmployeePerforGoalVo>();
		if(kpoList!=null && kpoList.size()>0){
			for(int i=0;i<kpoList.size();i++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(kpoList.get(i).getId());
				perforgoal.setIndex(kpoList.get(i).getIndex());
				perforgoal.setKeyaction(kpoList.get(i).getKeyaction());
				perforgoal.setPhasegoal(kpoList.get(i).getPhasegoal());
				perforgoal.setWeightrate(kpoList.get(i).getWeightrate());
				perforgoal.setEmployeeid(kpoList.get(i).getEmployeeid());
				perforgoal.setDescription(kpoList.get(i).getDescription());
				perforgoal.setCreatedate(kpoList.get(i).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.PRIORITY_WORK);//重点工作
				
				data1.add(perforgoal);
			}
		}
		if(keyeventList!=null && keyeventList.size()>0){
			for(int j=0;j<keyeventList.size();j++){
				EmployeePerforGoalVo perforgoal = new EmployeePerforGoalVo();
				perforgoal.setId(keyeventList.get(j).getId());
				perforgoal.setIndex(keyeventList.get(j).getIndex());
				perforgoal.setKeyaction(keyeventList.get(j).getKeyaction());
				perforgoal.setPhasegoal(keyeventList.get(j).getPhasegoal());
				perforgoal.setWeightrate(keyeventList.get(j).getWeightrate());
				perforgoal.setEmployeeid(keyeventList.get(j).getEmployeeid());
				perforgoal.setDescription(keyeventList.get(j).getDescription());
				perforgoal.setCreatedate(keyeventList.get(j).getCreatedate());
				perforgoal.setDepartment(csdept!=null?csdept.getCsSubDeptName():"");
				perforgoal.setType(SysConstant.KEY_EVENTS);//关键事件
				
				data1.add(perforgoal);
			}
		}
		
		//查询个人能力提升计划表
		EmployeeImpplan el = new EmployeeImpplan();
		el.setEmployeeid(employeeid);//员工ID
		el.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		el.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<EmployeeImpplan> planList = employeeImpplanService.getEmployeeImpplan(el);
	    if(planList!=null && planList.size()>0){
	    	for(int k=0;k<planList.size();k++){
	    		if(planList.get(k).getDealine()!=null && !"".equals(planList.get(k).getDealine())){
	    			planList.get(k).setDealineString(sf3.format(planList.get(k).getDealine()));
	    		}
		    }
	    }
	    
	    //查询员工绩效总表，获取自评信息
	    Employeeperforgoal epg = new Employeeperforgoal();
	    epg.setEmployeeid(employeeid);
	    epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
	    epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
	    Employeeperforgoal reperfor = employeeperforgoalService.getEmpPerforgoal(epg);
		
		//查询绩效结果表，获取comments
		PerformanceManageEvaBean pmb = new PerformanceManageEvaBean();
		pmb.setEhr(emp.geteHr());
		pmb.setYear(sf.format(new Date()));
		pmb.setQuarter(String.valueOf(DateUtils.getQuarterByMonth(Integer.parseInt(sf2.format(new Date())))));
		PresultVo pv = performanceResultService.getPerformance(pmb);
	    
		map.put("comments", pv!=null?pv.getResult_Comments():"");
		map.put("directresult", pv!=null?pv.getDirect_Supervisor_Assessment_Result():"");
		map.put("selfassessment", reperfor!=null?reperfor.getSelfassessment():"");
		map.put("state", reperfor!=null?reperfor.getState():"");
		map.put("data", data1);
		map.put("plan", planList);
		
		return objectMapper.writeValueAsString(map);
    }

    /**
     * Management-绩效结果-绩效定稿-详情页面
     * @return
     */
    @RequestMapping("/latestPerforDetailPage/{employeeid}")
    public String latestPerforDetailPage(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/management/performanceLatestDetail";
    }
    
    /**
     * Management-绩效结果-历史绩效-详情页面
     * @return
     */
    @RequestMapping("/historyPerforDetailPage/{employeeid}")
    public String historyPerforDetailPage(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/management/performanceManageResultHistory";
    }
}
