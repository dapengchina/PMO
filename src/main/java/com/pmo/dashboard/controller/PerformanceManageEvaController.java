package com.pmo.dashboard.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.pom.dashboard.service.PerformanceService;

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
    @Resource
    private PerformanceService           performanceService;

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
        map.put("rows", page.getList());// update by xuexuan 返回值由data改为page中的list

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
        if ("0".equals(user.getUserType())) {// 登录用户为管理员  TODO  暂统计所有员工
            list = manageEvaService.groupStatByResultBU(null);
        }
        if ("1".equals(user.getUserType())) {// 事业部经理
            list = manageEvaService.groupStatByResultBU(user.getBu());
        }
        if ("3".equals(user.getUserType())) {// 交付部经理
            CSDept csDept = csDeptService.queryCSDeptById(user.getDu());// 查询交付部名称
            list = manageEvaService.groupStatByResultDU(csDept.getCsSubDeptName());
        }
        if ("5".equals(user.getUserType())) {// 登录用户为RM
            list = manageEvaService.groupStatByResultRM(user.getNickname());
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
        // 查询数据,条件：年-季-finalize-bu/du/ehr/staffName
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
        // 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        // 查询条件：当年-当季-bu/du/eHr/staffName/rm
        List<PerformanceManageEvaBean> data = manageEvaService.processingResultList(null, null, null, null, user.getNickname());
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        // 返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotal());
        map.put("rows", page.getList());
        return map;
    }
}
