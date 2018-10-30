package com.pmo.dashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.*;
import com.pmo.dashboard.util.ExcelUtil;
import com.pom.dashboard.service.PerformanceLobApproveService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/performanceLobApprove")
public class PerformanceLobApproveController {
    private static Logger logger = LoggerFactory.getLogger(PerformanceLobApproveController.class);

    @Resource
    private PerformanceLobApproveService performanceLobApproveService;

    @RequestMapping(value = "/getPerformanceLobApproveProportions",method = RequestMethod.POST)
    @ResponseBody
    public PerformanceLobApproveProportions getPerformanceLobApproveProportions(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        PerformanceLobApprove performanceLobApprove = performanceLobApproveService.getPerformanceLobApprove(condition);
        return performanceLobApprove.getPerformanceLobApproveProportions();
    }

    @RequestMapping(value = "/getPerformanceLobReportProportions",method = RequestMethod.POST)
    @ResponseBody
    public PerformanceLobApproveProportions getPerformanceLobReportProportions(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        PerformanceLobApproveProportions performanceLobApproveProportions = performanceLobApproveService.getPerformanceLobReportProportions(condition);
        return performanceLobApproveProportions;
    }

    @RequestMapping(value = "/performanceLobStatusToApprove",method = RequestMethod.POST)
    @ResponseBody
    public int performanceLobStatusToApprove(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        condition.setState("1"); // TODO: 0: pending 1: approve 2: reject
        int updateResult = performanceLobApproveService.updateState(condition);
        return updateResult;
    }

    @RequestMapping(value = "/performanceLobStatusToReject",method = RequestMethod.POST)
    @ResponseBody
    public int performanceLobStatusToReject(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        condition.setState("2"); // TODO: 0: pending 1: approve 2: reject
        int updateResult = performanceLobApproveService.updateState(condition);
        return updateResult;
    }

    @RequestMapping(value = "/updateApprovalFeedback",method = RequestMethod.POST)
    @ResponseBody
    public int updateApprovalFeedback(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        int updateResult = performanceLobApproveService.updateResultComments(condition);
        return updateResult;
    }

    @RequestMapping(value = "/getPerformanceLobApprovePerformances",method = RequestMethod.POST)
    @ResponseBody
    public List<PerformanceLobApprovePerformances> getPerformanceLobApprovePerformances(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        PerformanceLobApprove performanceLobApprove = performanceLobApproveService.getPerformanceLobApprove(condition);
        return performanceLobApprove.getPerformanceLobApprovePerformances();
    }

    @RequestMapping(value = "/getPerformanceLobApproveReport", method = RequestMethod.POST)
    @ResponseBody
    public String getPerformanceLobApproveReport(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        Page<Object> p = PageHelper.startPage(condition.getPageNumber(), condition.getPageSize());

        List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveReport(condition);
        int count = performanceLobApproveService.getPerformanceLobApproveDetailsCount(condition);

        Map<String,Object> map = new HashMap<String,Object>();

        PageInfo<PerformanceLobDetails> page = new PageInfo<>(performanceLobDetails);
        map.put("total", count);
        map.put("rows", performanceLobDetails);

        ObjectMapper objectMapper = new ObjectMapper();
        String rtn = objectMapper.writeValueAsString(map);
        logger.debug("getPerformanceLobApproveReport rtn:" + rtn);
        return rtn;
    }

    @RequestMapping(value = "/getPerformanceLobDetails", method = RequestMethod.POST)
    @ResponseBody
    public String getPerformanceLobApproveDetails(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        Page<Object> p = PageHelper.startPage(condition.getPageNumber(), condition.getPageSize());

        List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveDetails(condition);
        int count = performanceLobApproveService.getPerformanceLobApproveDetailsCount(condition);

        Map<String,Object> map = new HashMap<String,Object>();

        PageInfo<PerformanceLobDetails> page = new PageInfo<>(performanceLobDetails);
        map.put("total", count);
        map.put("rows", performanceLobDetails);

        ObjectMapper objectMapper = new ObjectMapper();
        String rtn = objectMapper.writeValueAsString(map);
        request.setAttribute("du", condition.getDu());
        logger.debug("getPerformanceLobApproveDetails rtn:" + rtn);
        return rtn;
    }

    @RequestMapping(value = "/getDuListByBu", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getDuListByBu(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition){
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));
        List<String> duList = performanceLobApproveService.getDuListByBu(condition);
        return duList;
    }

    @RequestMapping(value = "/exportLobHRReport")
    @ResponseBody
    public void exportHRReport(final HttpServletRequest request, final HttpServletResponse response){
        PerformanceLobQueryCondition condition = new PerformanceLobQueryCondition();
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));

        //获取数据
        List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveReport(condition);

        //excel标题
        String[] title = {"E-HR编号", "LOB工号", "姓名", "入职时间", "职务", "LOB", "BU", "交付部", "归属地", "是否骨干", "是否参评", "直接主管", "初评(依据客户反馈)", "部门集体评议结果", "集体评议主管", "A/C/D人员绩效事实", "是否绩效跳变", "备注"};

        //excel文件名
        String fileName = "HR Report" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "performance list";
        String[][] content = new String[performanceLobDetails.size()][];

        for (int i = 0; i < performanceLobDetails.size(); i++) {
            content[i] = new String[title.length];
            PerformanceLobDetails obj = performanceLobDetails.get(i);
            content[i][0] = obj.getEhr();
            content[i][1] = obj.getLobId();
            content[i][2] = obj.getStaffName();
            content[i][3] = obj.getOnBoardDate();
            content[i][4] = obj.getRole();
            content[i][5] = obj.getLob();
            content[i][6] = obj.getBu();
            content[i][7] = obj.getDu();
            content[i][8] = obj.getLocation();
            // TODO:
            content[i][9] = "1".equals(obj.getBackbone()) ? "是":"否";
            content[i][10] ="1".equals(obj.getAssessed()) ? "是":"否";
            content[i][11] = obj.getDirectSupervisor();
            content[i][12] = obj.getPreAssessmentResult();
            content[i][13] = obj.getGroupAssessmentResult();
            content[i][14] = obj.getGroupAssessmentManager();
            content[i][15] = obj.getPerformanceFacts();
            content[i][16] = "1".equals(obj.getPerformanceSkip()) ? "是":"否";
            content[i][17] = obj.getRemark();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
       try {
           this.setResponseHeader(response, fileName);
           OutputStream os = response.getOutputStream();
           wb.write(os);
           os.flush();
           os.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @RequestMapping(value = "/getPerformanceLobApproveDetailsExport")
    @ResponseBody
    public void getPerformanceLobApproveDetailsExport(final HttpServletRequest request, final HttpServletResponse response) {
        PerformanceLobQueryCondition condition = new PerformanceLobQueryCondition();
        condition.setYear(getCurrentQuarter().get("year"));
        condition.setQuarter(getCurrentQuarter().get("quarter"));

        //获取数据
        List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveDetails(condition);

        //excel标题
        String[] title = {"E-HR编号", "LOB工号", "姓名", "入职时间", "职务", "LOB", "BU", "交付部", "归属地", "是否骨干", "是否参评", "直接主管", "客户反馈", "部门集体评议结果", "集体评议主管", "A/C/D人员绩效事实", "是否绩效跳变", "备注", "上季度绩效", "上上季度绩效", "上上上季度绩效"};

        //excel文件名
        String fileName = "HR Report" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "performance list";
        String[][] content = new String[performanceLobDetails.size()][];

        for (int i = 0; i < performanceLobDetails.size(); i++) {
            content[i] = new String[title.length];
            PerformanceLobDetails obj = performanceLobDetails.get(i);
            content[i][0] = obj.getEhr();
            content[i][1] = obj.getLobId();
            content[i][2] = obj.getStaffName();
            content[i][3] = obj.getOnBoardDate();
            content[i][4] = obj.getRole();
            content[i][5] = obj.getLob();
            content[i][6] = obj.getBu();
            content[i][7] = obj.getDu();
            content[i][8] = obj.getLocation();
            // TODO:
            content[i][9] = "1".equals(obj.getBackbone()) ? "是":"否";
            content[i][10] ="1".equals(obj.getAssessed()) ? "是":"否";
            content[i][11] = obj.getDirectSupervisor();
            content[i][12] = obj.getPreAssessmentResult();
            content[i][13] = obj.getGroupAssessmentResult();
            content[i][14] = obj.getGroupAssessmentManager();
            content[i][15] = obj.getPerformanceFacts();
            content[i][16] = "1".equals(obj.getPerformanceSkip()) ? "是":"否";
            content[i][17] = obj.getRemark();
            content[i][18] = obj.getPrevious1Quarter();
            content[i][19] = obj.getPrevious2Quarter();
            content[i][20] = obj.getPrevious3Quarter();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Map<String, String> getCurrentQuarter() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String quarter = "";
        Map<String, String> map = new HashMap<>();
        map.put("year",String.valueOf(c.get(Calendar.YEAR)));
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                quarter = "1";
            else if (currentMonth >= 4 && currentMonth <= 6)
                quarter = "2";
            else if (currentMonth >= 7 && currentMonth <= 9)
                quarter = "3";
            else if (currentMonth >= 10 && currentMonth <= 12)
                quarter = "4";
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("quarter",quarter);
        return map;
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
