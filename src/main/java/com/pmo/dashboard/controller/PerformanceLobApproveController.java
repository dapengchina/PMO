package com.pmo.dashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.dao.PerformanceEmpPBCMapper;
import com.pmo.dashboard.entity.*;
import com.pmo.dashboard.entity.vo.EmployeePerforGoalVo;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pmo.dashboard.util.DateUtils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeImpplanService;
import com.pom.dashboard.service.EmployeeInfoService;
import com.pom.dashboard.service.EmployeeKeyeventService;
import com.pom.dashboard.service.EmployeeKpoService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeperforgoalService;
import com.pom.dashboard.service.PerformanceEmpHistoryService;
import com.pom.dashboard.service.PerformanceLobApproveService;
import com.pom.dashboard.service.PerformanceManageEvaService;
import com.pom.dashboard.service.PerformanceMatrixService;
import com.pom.dashboard.service.PerformanceProgressService;
import com.pom.dashboard.service.PerformanceResultService;
import com.pom.dashboard.service.PerformanceService;
import com.pom.dashboard.service.UserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/performanceLobApprove")
public class PerformanceLobApproveController {
	
    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(PerformanceLobApproveController.class);

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
    
    @Resource
	private PerformanceProgressService progressService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private PerformanceLobApproveService performanceLobApproveService;



    /**
     * LOB-审批-审批详情页面-员工详情页面
     * @param bu
     * @param model
     * @return
     */
    @RequestMapping("/lobDetailPage1/{employeeid}")
    public String lobDetailPage1(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/lob/performanceLobDetail1";
    }
    
    /**
     * LOB-绩效结果-当期绩效-员工详情页面
     * @param bu
     * @param model
     * @return
     */
    @RequestMapping("/lobDetailPage2/{employeeid}")
    public String lobDetailPage2(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/lob/performanceLobDetail2";
    }
    
    /**
     * LOB-绩效结果-历史绩效-员工详情页面
     * @param bu
     * @param model
     * @return
     */
    @RequestMapping("/lobDetailPage3/{employeeid}")
    public String lobDetailPage3(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/lob/performanceLobDetail3";
    }

    
    /**
     * LOB-HR Report-员工详情页面
     * @param bu
     * @param model
     * @return
     */
    @RequestMapping("/lobDetailPage4/{employeeid}")
    public String lobDetailPage4(HttpServletRequest request,@PathVariable("employeeid") String employeeid,Model model){
    	model.addAttribute("employeeid", employeeid);
    	return "performance/lob/performanceLobDetail4";
    }
    
    
    /**
     * LOB-员工详情页面数据
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
		
		//查询绩效目标流程表，获取绩效目标审批comments
		PerformanceEmpProcessBean pepb = new PerformanceEmpProcessBean();
		pepb.setEmployeeid(employeeid);
		pepb.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
		pepb.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
		List<PerformanceEmpProcessBean> processList = progressService.queryPerformanceProgressList(pepb);
		
		if(processList!=null && processList.size()>0){
			map.put("processcomments", processList.get(0).getRemark());
		}else{
			map.put("processcomments", "");
		}
	    
		map.put("comments", pv!=null?pv.getResult_Comments():"");
		map.put("finalResult", pv!=null?pv.getResult():"");
		map.put("initialevaluation", pv!=null?pv.getDirect_Supervisor_Assessment_Result():"");
		map.put("selfassessment", reperfor!=null?reperfor.getSelfassessment():"");
		map.put("state", reperfor!=null?reperfor.getState():"");
		map.put("data", data1);
		map.put("plan", planList);
		
		return objectMapper.writeValueAsString(map);
    }
    
    
    
    /**
     * LOB-HR Report
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping("/hrreport")
    @ResponseBody
    public Map<String, Object> hrreport(@RequestParam int pageSize, @RequestParam int pageNumber) {
        // 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        // 查询数据,条件：年-季-finalize
        List<PerformanceManageEvaBean> data = performanceLobApproveService.hrReport();
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
    
    
    
    @RequestMapping("/report/percentage")
    @ResponseBody
    public Map<String, Object> percentage() {
        List<Map<String, Object>> list = performanceLobApproveService.groupStatByResultFinalize();
        // 计算百分比
        Map<String, Object> rtn = performanceLobApproveService.percentage(list);
        return rtn;
    }
    
    
    
    
//  @RequestMapping(value = "/getPerformanceLobApproveProportions",method = RequestMethod.POST)
//  @ResponseBody
//  public PerformanceLobApproveProportions getPerformanceLobApproveProportions(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      PerformanceLobApprove performanceLobApprove = performanceLobApproveService.getPerformanceLobApprove(condition);
//      return performanceLobApprove.getPerformanceLobApproveProportions();
//  }

//  @RequestMapping(value = "/getPerformanceLobReportProportions",method = RequestMethod.POST)
//  @ResponseBody
//  public PerformanceLobApproveProportions getPerformanceLobReportProportions(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      PerformanceLobApproveProportions performanceLobApproveProportions = performanceLobApproveService.getPerformanceLobReportProportions(condition);
//      return performanceLobApproveProportions;
//  }

//  @RequestMapping(value = "/performanceLobStatusToApprove",method = RequestMethod.POST)
//  @ResponseBody
//  public int performanceLobStatusToApprove(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      condition.setState("1"); // TODO: 0: pending 1: approve 2: reject
//      int updateResult = performanceLobApproveService.updateState(condition);
//      return updateResult;
//  }

//  @RequestMapping(value = "/performanceLobStatusToReject",method = RequestMethod.POST)
//  @ResponseBody
//  public int performanceLobStatusToReject(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      condition.setState("2"); // TODO: 0: pending 1: approve 2: reject
//      int updateResult = performanceLobApproveService.updateState(condition);
//      return updateResult;
//  }

//  @RequestMapping(value = "/updateApprovalFeedback",method = RequestMethod.POST)
//  @ResponseBody
//  public int updateApprovalFeedback(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      int updateResult = performanceLobApproveService.updateResultComments(condition);
//      return updateResult;
//  }

//  @RequestMapping(value = "/getPerformanceLobApprovePerformances",method = RequestMethod.POST)
//  @ResponseBody
//  public List<PerformanceLobApprovePerformances> getPerformanceLobApprovePerformances(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      PerformanceLobApprove performanceLobApprove = performanceLobApproveService.getPerformanceLobApprove(condition);
//      return performanceLobApprove.getPerformanceLobApprovePerformances();
//  }

//  @RequestMapping(value = "/getPerformanceLobApproveReport", method = RequestMethod.POST)
//  @ResponseBody
//  public String getPerformanceLobApproveReport(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      Page<Object> p = PageHelper.startPage(condition.getPageNumber(), condition.getPageSize());
//
//      List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveReport(condition);
//      int count = performanceLobApproveService.getPerformanceLobApproveDetailsCount(condition);
//
//      Map<String,Object> map = new HashMap<String,Object>();
//
//      PageInfo<PerformanceLobDetails> page = new PageInfo<>(performanceLobDetails);
//      map.put("total", count);
//      map.put("rows", performanceLobDetails);
//
//      ObjectMapper objectMapper = new ObjectMapper();
//      String rtn = objectMapper.writeValueAsString(map);
//      logger.debug("getPerformanceLobApproveReport rtn:" + rtn);
//      return rtn;
//  }

//  @RequestMapping(value = "/getPerformanceLobDetails", method = RequestMethod.POST)
//  @ResponseBody
//  public String getPerformanceLobApproveDetails(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition) throws JsonProcessingException {
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      Page<Object> p = PageHelper.startPage(condition.getPageNumber(), condition.getPageSize());
//
//      List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveDetails(condition);
//      int count = performanceLobApproveService.getPerformanceLobApproveDetailsCount(condition);
//
//      Map<String,Object> map = new HashMap<String,Object>();
//
//      PageInfo<PerformanceLobDetails> page = new PageInfo<>(performanceLobDetails);
//      map.put("total", count);
//      map.put("rows", performanceLobDetails);
//
//      ObjectMapper objectMapper = new ObjectMapper();
//      String rtn = objectMapper.writeValueAsString(map);
//      request.setAttribute("du", condition.getDu());
//      logger.debug("getPerformanceLobApproveDetails rtn:" + rtn);
//      return rtn;
//  }

//  @RequestMapping(value = "/getDuListByBu", method = RequestMethod.POST)
//  @ResponseBody
//  public List<String> getDuListByBu(final HttpServletRequest request, final HttpServletResponse response, @RequestBody PerformanceLobQueryCondition condition){
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//      List<String> duList = performanceLobApproveService.getDuListByBu(condition);
//      return duList;
//  }
//
//  @RequestMapping(value = "/exportLobHRReport")
//  @ResponseBody
//  public void exportHRReport(final HttpServletRequest request, final HttpServletResponse response){
//      PerformanceLobQueryCondition condition = new PerformanceLobQueryCondition();
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//
//      //获取数据
//      List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveReport(condition);
//
//      //excel标题
//      String[] title = {"E-HR编号", "LOB工号", "姓名", "入职时间", "职务", "LOB", "BU", "交付部", "归属地", "是否骨干", "是否参评", "直接主管", "初评(依据客户反馈)", "部门集体评议结果", "集体评议主管", "A/C/D人员绩效事实", "是否绩效跳变", "备注"};
//
//      //excel文件名
//      String fileName = "HR Report" + System.currentTimeMillis() + ".xls";
//
//      //sheet名
//      String sheetName = "performance list";
//      String[][] content = new String[performanceLobDetails.size()][];
//
//      for (int i = 0; i < performanceLobDetails.size(); i++) {
//          content[i] = new String[title.length];
//          PerformanceLobDetails obj = performanceLobDetails.get(i);
//          content[i][0] = obj.getEhr();
//          content[i][1] = obj.getLobId();
//          content[i][2] = obj.getStaffName();
//          content[i][3] = obj.getOnBoardDate();
//          content[i][4] = obj.getRole();
//          content[i][5] = obj.getLob();
//          content[i][6] = obj.getBu();
//          content[i][7] = obj.getDu();
//          content[i][8] = obj.getLocation();
//          // TODO:
//          content[i][9] = "1".equals(obj.getBackbone()) ? "是":"否";
//          content[i][10] ="1".equals(obj.getAssessed()) ? "是":"否";
//          content[i][11] = obj.getDirectSupervisor();
//          content[i][12] = obj.getPreAssessmentResult();
//          content[i][13] = obj.getGroupAssessmentResult();
//          content[i][14] = obj.getGroupAssessmentManager();
//          content[i][15] = obj.getPerformanceFacts();
//          content[i][16] = "1".equals(obj.getPerformanceSkip()) ? "是":"否";
//          content[i][17] = obj.getRemark();
//      }
//
//      //创建HSSFWorkbook
//      HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
//
//      //响应到客户端
//     try {
//         this.setResponseHeader(response, fileName);
//         OutputStream os = response.getOutputStream();
//         wb.write(os);
//         os.flush();
//         os.close();
//     } catch (Exception e) {
//         e.printStackTrace();
//     }
//  }
//
//  @RequestMapping(value = "/getPerformanceLobApproveDetailsExport")
//  @ResponseBody
//  public void getPerformanceLobApproveDetailsExport(final HttpServletRequest request, final HttpServletResponse response) {
//      PerformanceLobQueryCondition condition = new PerformanceLobQueryCondition();
//      condition.setYear(getCurrentQuarter().get("year"));
//      condition.setQuarter(getCurrentQuarter().get("quarter"));
//
//      //获取数据
//      List<PerformanceLobDetails> performanceLobDetails = performanceLobApproveService.getPerformanceLobApproveDetails(condition);
//
//      //excel标题
//      String[] title = {"E-HR编号", "LOB工号", "姓名", "入职时间", "职务", "LOB", "BU", "交付部", "归属地", "是否骨干", "是否参评", "直接主管", "客户反馈", "部门集体评议结果", "集体评议主管", "A/C/D人员绩效事实", "是否绩效跳变", "备注", "上季度绩效", "上上季度绩效", "上上上季度绩效"};
//
//      //excel文件名
//      String fileName = "HR Report" + System.currentTimeMillis() + ".xls";
//
//      //sheet名
//      String sheetName = "performance list";
//      String[][] content = new String[performanceLobDetails.size()][];
//
//      for (int i = 0; i < performanceLobDetails.size(); i++) {
//          content[i] = new String[title.length];
//          PerformanceLobDetails obj = performanceLobDetails.get(i);
//          content[i][0] = obj.getEhr();
//          content[i][1] = obj.getLobId();
//          content[i][2] = obj.getStaffName();
//          content[i][3] = obj.getOnBoardDate();
//          content[i][4] = obj.getRole();
//          content[i][5] = obj.getLob();
//          content[i][6] = obj.getBu();
//          content[i][7] = obj.getDu();
//          content[i][8] = obj.getLocation();
//          // TODO:
//          content[i][9] = "1".equals(obj.getBackbone()) ? "是":"否";
//          content[i][10] ="1".equals(obj.getAssessed()) ? "是":"否";
//          content[i][11] = obj.getDirectSupervisor();
//          content[i][12] = obj.getPreAssessmentResult();
//          content[i][13] = obj.getGroupAssessmentResult();
//          content[i][14] = obj.getGroupAssessmentManager();
//          content[i][15] = obj.getPerformanceFacts();
//          content[i][16] = "1".equals(obj.getPerformanceSkip()) ? "是":"否";
//          content[i][17] = obj.getRemark();
//          content[i][18] = obj.getPrevious1Quarter();
//          content[i][19] = obj.getPrevious2Quarter();
//          content[i][20] = obj.getPrevious3Quarter();
//      }
//
//      //创建HSSFWorkbook
//      HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
//
//      //响应到客户端
//      try {
//          this.setResponseHeader(response, fileName);
//          OutputStream os = response.getOutputStream();
//          wb.write(os);
//          os.flush();
//          os.close();
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//  }
//
//
//  public static Map<String, String> getCurrentQuarter() {
//      Calendar c = Calendar.getInstance();
//      int currentMonth = c.get(Calendar.MONTH) + 1;
//      String quarter = "";
//      Map<String, String> map = new HashMap<>();
//      map.put("year",String.valueOf(c.get(Calendar.YEAR)));
//      try {
//          if (currentMonth >= 1 && currentMonth <= 3)
//              quarter = "1";
//          else if (currentMonth >= 4 && currentMonth <= 6)
//              quarter = "2";
//          else if (currentMonth >= 7 && currentMonth <= 9)
//              quarter = "3";
//          else if (currentMonth >= 10 && currentMonth <= 12)
//              quarter = "4";
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//      map.put("quarter",quarter);
//      return map;
//  }
//
//  //发送响应流方法
//  public void setResponseHeader(HttpServletResponse response, String fileName) {
//      try {
//          try {
//              fileName = new String(fileName.getBytes(),"ISO8859-1");
//          } catch (UnsupportedEncodingException e) {
//              e.printStackTrace();
//          }
//          response.setContentType("application/octet-stream;charset=ISO8859-1");
//          response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
//          response.addHeader("Pargam", "no-cache");
//          response.addHeader("Cache-Control", "no-cache");
//      } catch (Exception ex) {
//          ex.printStackTrace();
//      }
//  }

}
