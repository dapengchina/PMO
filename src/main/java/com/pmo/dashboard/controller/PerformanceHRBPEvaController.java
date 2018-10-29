package com.pmo.dashboard.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pom.dashboard.service.PerformanceManageEvaService;

/**
 * HRBP模块 -- 绩效考评控制器
 * @author xuexuan
 * 2018年10月16日 上午10:35:47
 * 
 */
@Controller
@RequestMapping("/performanceHRBPEva")
public class PerformanceHRBPEvaController {
    @Resource
    private PerformanceManageEvaService performanceManageEvaService;
    /** HRBP-绩效考评-集体评议导出文件 **/
    private static String[]             groupEvaExcelTitle   = new String[] { "NO.", "EHR ID", "LOB ID", "Name", "Date on-board", "MSA Role", "LOB", "BU", "DU", "Location", "BackBone", "Assessed",
            "Direct Supervisor", "Client Feedback", "Pre-Assessment(Refer Client Feedback)", "Pre-Assessment", "Group Assessment Result", "Group Assessment Result", "Performance Facts(A/C/D)",
            "Reformance Skip", "Remarks", "Last Q", "2 Qs ago", "3 Qs ago" };

    private static String[]             groupEvaExcelContent = new String[] { "NO.", "ehr", "lobNo", "name", "hireDate", "position", "serviceLine", "bu", "du", "location", "keymember", "participate",
            "manager", "customerFeedback", "initialEvalution", "pmEvalution", "duEvalution", "duEvaManager", "achievement", "jump", "comments", "previous1Quarter", "previous2Quarter",
            "previous3Quarter"                              };
    /** HRBP-绩效考评-审批导出文件 **/
    private static String[]             approvalTitle        = new String[] { "NO.", "Bu", "Year", "Quarter", "Status" };
    private static String[]             approvalContent      = new String[] { "NO.", "BU", "Year", "Quarter", "State" };

    /**
     * 主管比例统计
     * 各绩效等级数量（统计所有员工）
     * @author: Song_Lee
     * 2018年10月19日 上午10:05:43
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/percentage")
    @ResponseBody
    public String getPercent(@RequestParam(required = false) String bu) throws JsonProcessingException {
        List<Map<String, Object>> list = performanceManageEvaService.groupStatByResult(bu);
        int sum = 0;
        for (Map<String, Object> map : list) {
            sum += Integer.parseInt(map.get("count") + "");
        }
        Map<String, Object> rtn = new HashMap<String, Object>();
        NumberFormat nf = NumberFormat.getPercentInstance();
        String level = "";
        int count = 0;
        for (Map<String, Object> map : list) {
            level = ((String) map.get("result")).toUpperCase();
            count = Integer.parseInt(map.get("count") + "");
            rtn.put(level, count);
            rtn.put("percent" + level, nf.format((float) count / sum));
        }
        rtn.put("sum", sum);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(rtn);
    }

    /**
     * 根据筛选条件分页查询员工绩效信息
     * @author: Song_Lee
     * 2018年10月19日 下午2:19:57
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/groupEva/list")
    @ResponseBody
    public String groupEvaList(@RequestParam int pageSize, @RequestParam int pageNumber, PerformanceQueryCondition condition) throws JsonProcessingException {
        // 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        List<PerformanceManageEvaBean> data = performanceManageEvaService.queryManageEvaSecondQueryList(condition);
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        // 返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotal());
        map.put("rows", page.getList());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 导出员工绩效信息
     * @author: Song_Lee
     * 2018年10月19日 下午4:21:29
     * @return 
     * ResponseEntity<byte[]>
     * @throws IOException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @RequestMapping("/groupEva/export")
    public ResponseEntity<byte[]> groupEvaExport(PerformanceQueryCondition condition) throws IOException, IllegalArgumentException, IllegalAccessException {
        // 查询数据
        List<PerformanceManageEvaBean> data = performanceManageEvaService.queryManageEvaSecondQueryList(condition);
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        groupEvaSheet(book, data);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        book.write(os);
        byte[] body = os.toByteArray();
        book.close();
        os.close();
        // 返回结果
        String fileName = null;
        try {
            fileName = new String("集体评议.xlsx".getBytes("UTF-8"), "ISO-8859-1");
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
     * 审批列表
     * @author: Song_Lee
     * 2018年10月22日 上午11:20:32
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/approval/list")
    @ResponseBody
    public String buGroupEvaList() throws JsonProcessingException {
        List<Map<String, Object>> data = performanceManageEvaService.approvalList();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }

    /**
     * 审批详情页-审批提交
     * @author: Song_Lee
     * 2018年10月22日 下午3:00:00
     * @param bu  审批的部门
     * @param state 审批状态
     * @return 
     * String
     */
    @RequestMapping("/approval/detail/submit")
    @ResponseBody
    public String approvalDetailSubmit(@RequestParam String bu, @RequestParam String state) {
        performanceManageEvaService.updateStateByBu(bu, state);
        return "";
    }

    /**
     * 审批页-审批提交
     * @author: Song_Lee
     * 2018年10月22日 下午3:01:07
     * @return 
     * String
     * @throws JsonProcessingException 
     * 
     */
    @RequestMapping("/approval/submit")
    public String approvalSubmit(@RequestParam String comments) throws JsonProcessingException {
        if (StringUtils.isNotBlank(comments)) {
            performanceManageEvaService.updateComments(comments);
        }
        return "performance/performanceHRBPApproval";
    }

    /**
     * 审批页面批量导出
     * @author: Song_Lee
     * 2018年10月22日 下午3:04:46
     * @return 
     * ResponseEntity<byte[]>
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws IOException 
     */
    @RequestMapping("/approval/export")
    public ResponseEntity<byte[]> approvalExport() throws IllegalArgumentException, IllegalAccessException, IOException {
        // 查询数据
        List<PerformanceManageEvaBean> data_groupEva = performanceManageEvaService.queryManageEvaSecondQueryList(new PerformanceQueryCondition());
        List<Map<String, Object>> data_approval = performanceManageEvaService.approvalList();
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        approvalSheet(book, data_approval);
        groupEvaSheet(book, data_groupEva);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        book.write(os);
        byte[] body = os.toByteArray();
        book.close();
        os.close();
        // 返回结果
        String fileName = null;
        try {
            fileName = new String("审批.xlsx".getBytes("UTF-8"), "ISO-8859-1");
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
     * 创建集体评议工作簿
     * @author: Song_Lee
     * 2018年10月22日 下午3:28:13
     * @param book
     * @param data
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     * void
     */
    private void groupEvaSheet(XSSFWorkbook book, List<PerformanceManageEvaBean> data) throws IllegalArgumentException, IllegalAccessException {
        // 集体评议工作簿
        Sheet sheet_groupEva = book.createSheet("group assessment");
        Row row;
        Cell cell;
        // 创建表头
        row = sheet_groupEva.createRow(0);
        for (int c = 0; c < groupEvaExcelTitle.length; c++) {
            cell = row.createCell(c);// 创建数据各列
            cell.setCellValue(groupEvaExcelTitle[c]);// 赋值
        }
        // 创建表格内容
        for (int r = 0; r < data.size(); r++) {
            row = sheet_groupEva.createRow(r + 1);// 从第二行开始
            cell = row.createCell(0);// 第一列为序号
            cell.setCellValue(r + 1);
            for (int c = 1; c < groupEvaExcelContent.length; c++) {
                cell = row.createCell(c);// 创建数据各列
                Class clazz = data.get(r).getClass();
                Field field;
                try {
                    field = clazz.getDeclaredField(groupEvaExcelContent[c]);
                    field.setAccessible(true);
                    cell.setCellValue((String) field.get(data.get(r)));// 赋值
                } catch (NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
                    cell.setCellValue("");
                }
            }
        }
    }

    /**
     * 创建审批工作簿
     * @author: Song_Lee
     * 2018年10月22日 下午3:28:17
     * @param book
     * @param data 
     * void
     */
    private void approvalSheet(XSSFWorkbook book, List<Map<String, Object>> data) {
        Sheet sheet_approval = book.createSheet("approval");
        Row row;
        Cell cell;
        row = sheet_approval.createRow(0);
        for (int c = 0; c < approvalTitle.length; c++) {
            cell = row.createCell(c);// 创建数据各列
            cell.setCellValue(approvalTitle[c]);// 赋值
        }
        int approval_r = 0;
        for (Map<String, Object> map : data) {
            row = sheet_approval.createRow(approval_r + 1);// 从第二行开始
            cell = row.createCell(0);// 第一列为序号
            cell.setCellValue(approval_r + 1);
            for (int c = 1; c < approvalContent.length; c++) {
                cell = row.createCell(c);
                cell.setCellValue((String) map.get(approvalContent[c]));
            }
            approval_r++;
        }
    }
}
