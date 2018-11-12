package com.pmo.dashboard.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.dao.EmployeeImpplanMapper;
import com.pmo.dashboard.dao.EmployeeKeyeventMapper;
import com.pmo.dashboard.dao.EmployeeKpoMapper;
import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.dao.EmployeeperforgoalMapper;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeImpplan;
import com.pmo.dashboard.entity.EmployeeKeyevent;
import com.pmo.dashboard.entity.EmployeeKpo;
import com.pmo.dashboard.entity.Employeeperforgoal;
import com.pmo.dashboard.entity.PerformanceEmpProcessBean;
import com.pmo.dashboard.util.DateUtils;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.EmployeeperforgoalService;
import com.pom.dashboard.service.PerformanceProgressService;


@Service
public class EmployeeperforgoalServiceImpl implements EmployeeperforgoalService{

	
	@Resource
	private EmployeeperforgoalMapper employeeperforgoalMapper;
	
	@Resource
	private EmployeeKpoMapper employeeKpoMapper;
	
	@Resource
	private EmployeeKeyeventMapper employeeKeyeventMapper;
	
	@Resource
	private EmployeeImpplanMapper employeeImpplanMapper;
	
	@Resource
    private EmployeeMapper employeeMapper;
	
	@Resource
	private PerformanceProgressService performanceProgressService;
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Employeeperforgoal getEmpPerforgoal(Employeeperforgoal epg) {
		return employeeperforgoalMapper.selectEmpPerforgoal(epg);
	}

	@Override
	@Transactional
	public int saveEmpPerforgoal(String employeeid,JSONArray data1, JSONArray data2, JSONArray data3,String state) throws ParseException {
		try{
			
			/**
			 * 处理保存之前先删除所有相关数据
			 */
			//删除重点工作，员工当年当季度数据
			EmployeeKpo d1 = new EmployeeKpo();
			d1.setEmployeeid(employeeid);
			d1.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
			d1.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
			employeeKpoMapper.delete(d1);
			
			//删除关键事件，员工当年当季度数据
			EmployeeKeyevent d2 = new EmployeeKeyevent();
			d2.setEmployeeid(employeeid);
			d2.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
			d2.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
			employeeKeyeventMapper.delete(d2);
			
			//删除个人能力提升计划，员工当年当季度数据
			EmployeeImpplan d3 = new EmployeeImpplan();
			d3.setEmployeeid(employeeid);
			d3.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
			d3.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
			employeeImpplanMapper.delete(d3);
			
			
			
			
			/**
			 * 处理保存重点工作数据
			 */
			
			for(int i=0;i<data1.length();i++) {
			     JSONObject job = data1.getJSONObject(i);
			     String id =  Utils.getUUID();
			     String index = (String)job.get("index");//序号
			     String description = (String)job.get("description");//重点工作内容描述
			     String weightrate = (String)job.get("weightrate");//权重
			     String phasegoal = (String)job.get("phasegoal");//阶段目标
			     String keyaction = (String)job.get("keyaction");//关键举措
			     
			     EmployeeKpo eo = new EmployeeKpo();
			     eo.setId(id);
			     eo.setEmployeeid(employeeid);
			     eo.setIndex(index!=""?Integer.parseInt(index):-1);
			     eo.setDescription(description!=""?description:"");
			     eo.setWeightrate(weightrate!=""?weightrate:"");
			     eo.setPhasegoal(phasegoal!=""?phasegoal:"");
			     eo.setKeyaction(keyaction!=""?keyaction:"");
			     eo.setCreatedate(new Date());
			     
			     employeeKpoMapper.insert(eo);
			}
			
			/**
			 * 处理保存关键事件数据
			 */
			for(int j=0;j<data2.length();j++) {
			     JSONObject job = data2.getJSONObject(j);
			     String id =  Utils.getUUID();
			     String index = (String)job.get("index");//序号
			     String description = (String)job.get("description");//重点工作内容描述
			     String weightrate = (String)job.get("weightrate");//权重
			     String phasegoal = (String)job.get("phasegoal");//阶段目标
			     String keyaction = (String)job.get("keyaction");//关键举措
			     
			     EmployeeKeyevent ek = new EmployeeKeyevent();
			     ek.setId(id);
			     ek.setEmployeeid(employeeid);
			     ek.setIndex(index!=""?Integer.parseInt(index):-1);
			     ek.setDescription(description!=""?description:"");
			     ek.setWeightrate(weightrate!=""?weightrate:"");
			     ek.setPhasegoal(phasegoal!=""?phasegoal:"");
			     ek.setKeyaction(keyaction!=""?keyaction:"");
			     ek.setCreatedate(new Date());
			     
			     employeeKeyeventMapper.insert(ek);
			}
			
			/**
			 * 处理保存个人能力提升计划数据
			 */
			for(int k=0;k<data3.length();k++) {
			     JSONObject job = data3.getJSONObject(k);
			     String id =  Utils.getUUID();
			     String keyability = (String)job.get("keyability");//待提高能力或经验
			     String action = (String)job.get("action");//实施活动或衡量标准
			     String supportor = (String)job.get("supportor");//所需支持人
			     String dealine = (String)job.get("dealine");//计划完成时间
			     
			     
			     EmployeeImpplan ep = new EmployeeImpplan();
			     ep.setId(id);
			     ep.setEmployeeid(employeeid);
			     ep.setAction(action!=""?action:"");
			     ep.setKeyability(keyability!=""?keyability:"");
			     ep.setSupportor(supportor!=""?supportor:"");
			     if(dealine!=null && !"".equals(dealine) && !dealine.equals("null")){
			    	 ep.setDealine(sf.parse(dealine));
			     }
			     ep.setCreatedate(new Date());
			     
			     employeeImpplanMapper.insert(ep);
			}
			
			
			/**
			 * 查询员工绩效目标设定总表(查询当年当季度的数据)
			 */
			Employeeperforgoal epg = new Employeeperforgoal();
			//获取当年当季度的开始日期并赋值
			epg.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
			//获取当年当季度的结束日期并赋值
			epg.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
			//员工ID
			epg.setEmployeeid(employeeid);
			Employeeperforgoal employperforgoal = employeeperforgoalMapper.selectEmpPerforgoal(epg);
			Employee emp = employeeMapper.queryEmployeeById(employeeid);
			if(employperforgoal==null){
				//保存员工绩效总表，当年当季度的数据
				Employeeperforgoal per1 = new Employeeperforgoal();
				per1.setId(Utils.getUUID());
				per1.setEmployeeid(employeeid);
				per1.setStaffname(emp.getStaffName());
				per1.setStaffid(emp.getHsbcStaffId());
				per1.setEhr(emp.geteHr());
				per1.setDepartment(emp.getCsSubDept());
				per1.setPosition(emp.getRole());
				per1.setAssessmensupervisor(emp.getRmUserId());
				per1.setCreatedate(new Date());
				per1.setState(state);
				employeeperforgoalMapper.insert(per1);
			}else{
				//修改员工绩效总表，当年当季度的数据状态
				Employeeperforgoal per2 = new Employeeperforgoal();
				per2.setEmployeeid(employeeid);
				per2.setCurrentQuarterStartDate(DateUtils.format(DateUtils.getThisQuarter().getStart()));
				per2.setCurrentQuarterEndDate(DateUtils.format(DateUtils.getThisQuarter().getEnd()));
				per2.setState(state);//状态
				employeeperforgoalMapper.updateState(per2);
			}
			
			/**
			 * 保存员工考评进度
			 */
			PerformanceEmpProcessBean pb = new PerformanceEmpProcessBean();
			Employee emp2 = employeeMapper.queryEmployeeById(emp.getRmUserId());
			pb.setId(Utils.getUUID());
			pb.setEmployeeid(employeeid);
			pb.setProcessid(SysConstant.PROCESS_TYPE1);
			pb.setOwner(emp2.getStaffName());
			pb.setCreatedate(new Date());
			pb.setState(SysConstant.PENDING_APPROVAL);
			performanceProgressService.saveProcess(pb);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}

}
