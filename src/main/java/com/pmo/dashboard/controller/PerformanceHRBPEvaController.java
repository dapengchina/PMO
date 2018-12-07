package com.pmo.dashboard.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.PerformanceManageEvaService;
import com.pom.dashboard.service.PerformanceService;

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
    
    @Resource
    private PerformanceService performanceService;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    /** HRBP-绩效考评-审批导出文件 **/
    private static String[]             approvalTitle   = new String[] { "NO.", "Bu", "Year", "Quarter", "Status" };
    private static String[]             approvalContent = new String[] { "NO.", "BU", "Year", "Quarter", "State" };

    /**
     * 当年当季各绩效等级比例统计
     * 统计所有员工/指定事业部
     * @author: xuexuan
     * 2018年10月19日 上午10:05:43
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/percentage")
    @ResponseBody
    public Map<String, Object> getPercent(@RequestParam(required = false) String bu) throws JsonProcessingException {
        List<Map<String, Object>> list = performanceManageEvaService.groupStatByResultBU(bu);
        Map<String, Object> rtn = performanceManageEvaService.percentage(list);
        return rtn;
    }

    /**
     * 根据筛选条件分页查询员工绩效信息
     * @author: xuexuan
     * 2018年10月19日 下午2:19:57
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/processing/result/list")
    @ResponseBody
    public Map<String, Object> processingResultList(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) String bu, @RequestParam(required = false) String du,
            @RequestParam(required = false) String eHr, @RequestParam(required = false) String staffName, @RequestParam(required = false) String rm) throws JsonProcessingException {
        // 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        // 查询条件：当年-当季-bu/du/eHr/staffName
        List<PerformanceManageEvaBean> data = performanceManageEvaService.processingResultList(bu, du, eHr, staffName, rm);
        //是否骨干，是否参评转换为汉字
        for(int i=0;i<data.size();i++){
        	if(data.get(i).getKeymember()!=null && !"".equals(data.get(i).getKeymember())){
        		data.get(i).setKeymember(SysConstant.getBackBoneMap().get(data.get(i).getKeymember()).toString());
        	}
        	if(data.get(i).getParticipate()!=null && !"".equals(data.get(i).getParticipate())){
        		data.get(i).setParticipate(SysConstant.getAssessedMap().get(data.get(i).getParticipate()).toString());
        	}
        }
        PageInfo<PerformanceManageEvaBean> page = new PageInfo<>(data);
        // 返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotal());
        map.put("rows", page.getList());
        return map;
    }

    /**
     * 导出员工绩效信息
     * @author: xuexuan
     * 2018年10月19日 下午4:21:29
     * @return 
     * ResponseEntity<byte[]>
     * @throws IOException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    @RequestMapping("/processing/result/export")
    public ResponseEntity<byte[]> processingResultExport() throws IOException, IllegalArgumentException, IllegalAccessException {
        // 查询数据
        List<PerformanceManageEvaBean> data = performanceManageEvaService.processingResultList(null, null, null, null, null);
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        performanceService.createSheetDetailList(book, "group assessment", data);
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
     * @author: xuexuan
     * 2018年10月22日 上午11:20:32
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/approval/list")
    @ResponseBody
    public String buGroupEvaList(HttpServletRequest request) throws JsonProcessingException {
    	// 判断登录用户类别，根据类别统计
//        User user = (User) request.getSession().getAttribute("loginUser");
//        List<Map<String, Object>> list = null;
    	//list = performanceManageEvaService.groupStatByResultBU(user.getBu());
        List<Map<String, Object>> data = performanceManageEvaService.approvalList();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }

    /**
     * 审批详情页-审批提交
     * @author: xuexuan
     * 2018年10月22日 下午3:00:00
     * @param bu  审批的部门
     * @param state 审批状态
     * @return 
     * String
     * @throws JsonProcessingException 
     */
    @RequestMapping("/approval/detail/submit")
    @ResponseBody
    public String approvalDetailSubmit(@RequestParam String bu, @RequestParam String state) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<String,Object>();
        try{
        	//performanceManageEvaService.updateStateByBU(bu, state);
        	PresultVo pv = new PresultVo();
        	pv.setState(state);
        	pv.setBu(bu);
        	pv.setFinalize(SysConstant.ISFINAL);
        	performanceManageEvaService.update(pv);
        	map.put("msg", "审批成功");
        	map.put("code", "1");
        }catch(Exception e){
        	map.put("msg", "审批失败");
        	map.put("code", "0");
        }
    	
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 审批页-审批提交
     * @author: xuexuan
     * 2018年10月22日 下午3:01:07
     * @return 
     * String
     * @throws JsonProcessingException 
     * 
     */
    @RequestMapping("/approval/submit")
    @ResponseBody
    public String approvalSubmit(@RequestParam String comments) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<String,Object>();
        try{
        	performanceManageEvaService.updateComments(comments);
        	
        	map.put("msg", "提交成功");
        	map.put("code", "1");
        }catch(Exception e){
        	map.put("msg", "提交失败");
        	map.put("code", "0");
        }
    	
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 审批页面批量导出
     * @author: xuexuan
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
        List<PerformanceManageEvaBean> data_groupEva = performanceManageEvaService.processingResultList(null, null, null, null, null);
        List<Map<String, Object>> data_approval = performanceManageEvaService.approvalList();
        // 创建文件
        XSSFWorkbook book = new XSSFWorkbook();
        approvalSheet(book, data_approval);
        performanceService.createSheetDetailList(book, "performance detail", data_groupEva);
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
     * 创建审批工作簿
     * @author: xuexuan
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
                if ("State".equals(approvalContent[c])) {
                    cell.setCellValue(Constants.APPROVAL_STATE.get(map.get(approvalContent[c])));
                } else {
                    cell.setCellValue((String) map.get(approvalContent[c]));
                }
            }
            approval_r++;
        }
    }
}
