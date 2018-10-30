package com.pmo.dashboard.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pmo.dashboard.entity.PerformanceManageResultHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pom.dashboard.service.PerformanceManageEvaService;

/**
 * Performance Management 绩效结果  页面的controller
 * @author Yankui
 *
 */

@Controller
@RequestMapping(value = "/performanceManageResultHistory")
public class PerformanceManageEvaResultController {
    private static Logger               logger              = LoggerFactory.getLogger(PerformanceManageEvaResultController.class);

    /** 绩效结果导出文件 **/
    private static String[]             historyExcelTitle   = new String[] { "NO.", "E-HR", "Employee Name", "DU", "BU", "Begin Date", "End Date", "RM", "Assessment Result", "Remarks" };

    private static String[]             historyExcelContent = new String[] { "NO.", "ehr", "empName", "du", "bu", "beginDate", "endDate", "rm", "result", "comments" };

    @Resource
    private PerformanceManageEvaService manageEvaService;

    @RequestMapping("/queryManageResultHistoryQueryList")
    @ResponseBody
    public Object queryManageResultHistoryQueryList(PerformanceQueryCondition condition, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        logger.debug("query condition:" + condition);
        List<PerformanceManageResultHistoryBean> data = manageEvaService.queryManageResultHistoryQueryList(condition);
        return data;
    }

    /**
     * 新增
     * 历史绩效导出
     * @author: xuexuan
     * 2018年10月25日 下午2:47:35
     * @param condition
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     * ResponseEntity<byte[]>
     */
    @RequestMapping("/result/export")
    public ResponseEntity<byte[]> historyResultExport(PerformanceQueryCondition condition) throws IOException, IllegalArgumentException, IllegalAccessException {
        // 查询数据
        List<PerformanceManageResultHistoryBean> data = manageEvaService.queryManageResultHistoryQueryList(condition);
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        Sheet sheet_groupEva = book.createSheet("History Performancel");
        Row row;
        Cell cell;
        // 创建表头
        row = sheet_groupEva.createRow(0);
        for (int c = 0; c < historyExcelTitle.length; c++) {
            cell = row.createCell(c);// 创建数据各列
            cell.setCellValue(historyExcelTitle[c]);// 赋值
        }
        // 创建表格内容
        for (int r = 0; r < data.size(); r++) {
            row = sheet_groupEva.createRow(r + 1);// 从第二行开始
            cell = row.createCell(0);// 第一列为序号
            cell.setCellValue(r + 1);
            for (int c = 1; c < historyExcelContent.length; c++) {
                cell = row.createCell(c);// 创建数据各列
                Class clazz = data.get(r).getClass();
                Field field;
                try {
                    field = clazz.getDeclaredField(historyExcelContent[c]);
                    field.setAccessible(true);
                    cell.setCellValue((String) field.get(data.get(r)));// 赋值
                } catch (NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
                    cell.setCellValue("");
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
            fileName = new String("历史绩效.xlsx".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDispositionFormData("attachment", fileName);// 文件名称

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.CREATED);

        return responseEntity;
    }
}
