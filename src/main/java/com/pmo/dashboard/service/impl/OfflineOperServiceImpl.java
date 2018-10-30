package com.pmo.dashboard.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pmo.dashboard.dao.CSDeptMapper;
import com.pmo.dashboard.dao.ChinaWorkHourMapper;
import com.pmo.dashboard.dao.CurrencysMapper;
import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.dao.HKWorkHourMapper;
import com.pmo.dashboard.dao.MLWorkHourMapper;
import com.pmo.dashboard.dao.OfflineOperMapper;
import com.pmo.dashboard.dao.UserMapper;
import com.pmo.dashboard.dao.WorkHourMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.Currencys;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HKWorkHour;
import com.pmo.dashboard.entity.MLWorkHour;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.OfflineOperCondition;
import com.pmo.dashboard.entity.OperSummary;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.WorkHour;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.OfflineOperService;

@Service
public class OfflineOperServiceImpl implements OfflineOperService {
	@Resource
	private OfflineOperMapper offlineOperMapper;
	
	@Resource
    private CSDeptMapper csDeptMapper;
	
	@Resource
    private EmployeeMapper employeeMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	ChinaWorkHourMapper chinaWorkHourMapper;
	
	@Resource
	HKWorkHourMapper HKWorkHourMapper;
	
	@Resource
	MLWorkHourMapper MLWorkHourMapper;
	
	@Resource
	private CurrencysMapper currencysMapper;
	
	@Override
	public boolean delete(String id) {
		if(offlineOperMapper.deleteByPrimaryKey(id)>0){
            return true;
        }
		return false;
	}

	@Override
	public boolean insert(OfflineOper offlineOper) {
		if(offlineOperMapper.insertSelective(offlineOper)>0){
            return true;
        }
		return false;
	}

	@Override
	public OfflineOper selectById(String id) {
		return offlineOperMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(OfflineOper offlineOper) {
		if(offlineOperMapper.updateByPrimaryKeySelective(offlineOper)>0){
            return true;
        }
		return false;
	}
	
//	@Override
//	public void export(User user) {
//		final int COLUMNS = 37 ; // 37列
//		XSSFWorkbook workBook = new XSSFWorkbook();
//	    XSSFSheet sheet = workBook.createSheet();
//	    workBook.setSheetName(0,"过程数据");
//	    XSSFRow titleRow = sheet.createRow(0);
//	    
//		if("5".equals(user.getUserType())) { // RM
//			
//		}else if("3".equals(user.getUserType())) {  // 交付部经理
//			
//		}else if("1".equals(user.getUserType())){ // 事业部经理
//			
//		}
//	}

	@Override
	public List<OfflineOper> exportOfflieOperData(User user) {
		OfflineOperCondition condition = new OfflineOperCondition() ;
		condition.setYear(""+LocalDate.now().getYear());
		condition.setMonth(""+LocalDate.now().getMonthValue());
		List<OfflineOper> rtn = null ;
		Set<User> rmSet = new HashSet();
		if(user.isRM()) { // RM
			condition.setRmId(user.getUserId());
			rtn = offlineOperMapper.queryByRM(condition) ;
		}else if(user.isSubDept()) {  // 交付部经理
			List<CSDept> csDepts = csDeptMapper.queryCSDeptByIds(user.getCsdeptId().split(","));  // 交付部经理所在的部门
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			rtn = offlineOperMapper.queryBySubDept(condition) ;
		}else if(user.isDept()){ // 事业部经理
			List<CSDept> csDepts = csDeptMapper.queryCSSubDeptNameByCsBuName(user.getBu());  // csBuName 根据事业部名称查
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			rtn = offlineOperMapper.queryByDept(condition) ;
		}else if(user.isAdmin()) { // admin
			rtn = offlineOperMapper.queryAllStaff(condition) ;
		}
		for(OfflineOper offlineOper :rtn) {
			workHour(offlineOper);
			String billRate = employeeMapper.queryEmployeeById(offlineOper.getEmployeeId()).getBillRate();
			offlineOper.setBillRate( StringUtils.isBlank(billRate)?"0":billRate);
		}
		return rtn ;
	}
	
	@Override
	public List<OfflineOper> query(OfflineOperCondition condition, User user,int pageSize,int pageNumber) {
		//1. count
//		int rmCount = offlineOperMapper.rmCount(condition);
//		if( 0 == rmCount) {
//		}
		condition.setYear(""+LocalDate.now().getYear());
		condition.setMonth(""+LocalDate.now().getMonthValue());
		List<OfflineOper> rtn = null ;
		if(user.isRM()) { // RM
			condition.setRmId(user.getUserId());
//			String[] rmIDs = {user.getUserId()};
//			condition.setRmIDs(rmIDs); 
			//第一个参数当前页码，第二个参数每页条数
			
//			int count = offlineOperMapper.rmCount(condition);
			//如果是RM pageSize给为:200
			//pageSize = 200;
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = offlineOperMapper.queryByRM(condition) ;
//			if(0 == count) {
//				PageHelper.startPage(pageNumber,pageSize); 
//				rtn = offlineOperMapper.queryFromEmployeeByRM(condition);
//			}else {
//				PageHelper.startPage(pageNumber,pageSize); 
//				rtn = offlineOperMapper.queryByRM(condition) ;
//			}
		}else if(user.isSubDept()) {  // 交付部经理
			List<CSDept> csDepts = csDeptMapper.queryCSDeptByIds(user.getCsdeptId().split(","));  // 交付部经理所在的部门
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			//第一个参数当前页码，第二个参数每页条数
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = offlineOperMapper.queryBySubDept(condition) ;
		}else if(user.isDept()){ // 事业部经理
			List<CSDept> csDepts = csDeptMapper.queryCSSubDeptNameByCsBuName(user.getBu());  // csBuName 根据事业部名称查
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			//第一个参数当前页码，第二个参数每页条数
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = offlineOperMapper.queryByDept(condition) ;
		}else if(user.isAdmin()) {
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = offlineOperMapper.queryAllStaff(condition) ;
		}
		for(OfflineOper offlineOper :rtn) {
			workHour(offlineOper);
			Employee e = employeeMapper.queryEmployeeById(offlineOper.getEmployeeId());
//			offlineOper.setBillRate(employeeMapper.getBillRate(e));
			offlineOper.setBillRate( StringUtils.isBlank(e.getBillRate())?"0":e.getBillRate());
			Currencys currencys = currency(offlineOper, e);   // 员工汇率
			offlineOper.setExRate(currencys.getExRate()); 
		}
		return rtn ;
	}
	
	@Override
	public boolean save(OfflineOper offlineOper, User user) {
		if(!"5".equals(user.getUserType())) { // ! RM
			offlineOper.setOperatorId(user.getUserId());
		}
		if(StringUtils.isBlank(offlineOper.getRmName())) { 
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userid", offlineOper.getRmId());
			List<User> users = userMapper.getUser(userMap);
			if(users.size()>0) {
				offlineOper.setRmName(users.get(0).getNickname());  // userName 是 EHR，中文名是 NICKNAME
			}
		} 
		
		offlineOper = workHour(offlineOper);
		Employee employee = employeeMapper.queryEmployeeById(offlineOper.getEmployeeId()) ;
		
		int rtnCount = 0;
		int count = offlineOperMapper.employeeCount(offlineOper);
		if(1 == count) {
			if(null == offlineOper.getId()) {
				OfflineOper rtn = offlineOperMapper.queryByEmployeeID(offlineOper);
				if(rtn != null) {
					offlineOper.setId(rtn.getId());
				}
			}
			offlineOper.setCreateUpdate(new Date());
			calculte(offlineOper, employee);
			rtnCount = offlineOperMapper.updateByPrimaryKeySelective(offlineOper) ;
		}else {
			offlineOper.setCreateDate(new Date());
			offlineOper.setId(Utils.getUUID());
			calculte(offlineOper, employee);
			rtnCount = offlineOperMapper.insertSelective(offlineOper) ;
		}
		return rtnCount>0? true : false  ;
	}
	
	private boolean checkCalculte(OfflineOper offlineOper,Employee employee) {
		if(null == offlineOper.getChsoftiMskHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiAwHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiIwHours()) {
			return false;
		}
		
		if(null == offlineOper.getChsoftiOtHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiToHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiApwHours()) {
			return false;
		}
		
		if(null == offlineOper.getChsoftiInfTravel()) {
			return false;
		}
		if(null == offlineOper.getChsoftiInfEquipment()) {
			return false;
		}
		if(null == offlineOper.getChsoftiInfSub()) {
			return false;
		}
		
		String emploeeBill = employee.getBillRate() ;
		if(StringUtils.isBlank(emploeeBill)) {
			return false;
		}
		return true;
	}
	
	private Currencys currency(OfflineOper o, Employee e) {
		Currencys currencyCondition = new Currencys();
		currencyCondition.setYear(o.getYear());
		currencyCondition.setMonth(o.getMonth());
		String staffLocation = e.getStaffLocation()  ;
		staffLocation = ( null == staffLocation ) ? "China":staffLocation ;
		if("China".equals(staffLocation)  ) {
			staffLocation = "北京";
		}else if("HK".equals(staffLocation)){
			staffLocation = "HK";
		}else if("Malaysia".equals(staffLocation)){
			staffLocation = "马来";
		}
		currencyCondition.setPlaceWork(staffLocation);
		Currencys currencys = currencysMapper.queryCurrency(currencyCondition);
		if(null == currencys.getExRate()) {
			currencys.setExRate(BigDecimal.ZERO);
		}
		return currencys;
	}
	
	private void calculte(OfflineOper oper,Employee employee) {
		Currencys currencys = currency(oper, employee);   // 员工汇率
		oper.setExRate(currencys.getExRate()); 
		if(!checkCalculte(oper, employee)) {
			return ;
		}
		/**
		 *  CHSOFTI_IFAW	　		实际工时收入=				员工单价*中软实际工时
			CHSOFTI_INVALID	　		无效工时收入=				员工单价*中软无效工时
			CHSOFTI_INF_OT	　		加班费工时收入=			员工单价*中软加班费工时
			CHSOFTI_INF_PT	　		调休工时收入=				员工单价*中软调休工时
			CHSOFTI_INF_AD	　		调整上月工时收入=			员工单价*中软调整上月工时
			CHSOFTI_INF_TOTAL	　	月收入合计=				实际工时收入+无效工时收入+加班费工时收入+调休工时收入+调整上月工时收入+差旅收入+付费设备收入+分包收入
			CHSOFTI_INF_CURRENT	　	当月有效收入=				月收入合计-无效工时收入			
			CHSOFTI_EFFECTIVE_NR	有效NR=					当月有效收入/1.06
			CHSOFTI_EFFECTIVE_ST	当月有效人力=				中软实际工时/中软月标准工时
			CHSOFTI_INVALID_ST	　	当月无效人力=				中软无效工时/中软月标准工时
			
			•	月收入合计(原币种)=实际工时收入+无效工时收入+加班费工时收入+调休工时收入+调整上月工时收入+差旅收入+付费设备收入+分包收入  CHSOFTI_INF_TOTAL
			•	月收入合计(人民币)=月收入合计(原币种)*汇率   			CHSOFTI_INF_RMBTOTAL
			•	当月有效收入=(月收入合计原币种-无效工时收入)*汇率		CHSOFTI_INF_CURRENT

		 */
//		oper.set
		// 员工单价  BILL_RATE , BILL_CURRENCY 货币类型
		
		BigDecimal billRate = new BigDecimal(oper.getBillRate());	// 员工单价
		oper.setChsoftiIfaw( billRate.multiply( oper.getChsoftiAwHours()).setScale(2, BigDecimal.ROUND_HALF_UP)) ; 		//实际工时收入 
		oper.setChsoftiInvalid( billRate.multiply( oper.getChsoftiIwHours()).setScale(2, BigDecimal.ROUND_HALF_UP)) ; 	//无效工时收入
		oper.setChsoftiInfOt( billRate.multiply( oper.getChsoftiOtHours()).setScale(2, BigDecimal.ROUND_HALF_UP)) ; 	//加班费工时收入 
		oper.setChsoftiInfPt( billRate.multiply( oper.getChsoftiToHours()).setScale(2, BigDecimal.ROUND_HALF_UP)) ; 	//调休工时收入 
		oper.setChsoftiInfAd( billRate.multiply( oper.getChsoftiApwHours()).setScale(2, BigDecimal.ROUND_HALF_UP)) ; 	//调整上月工时收入
		
		
		
		oper.setChsoftiInfTotal(oper.getChsoftiIfaw().add(oper.getChsoftiInvalid()).add(oper.getChsoftiInfOt())
				.add(oper.getChsoftiInfPt()).add(oper.getChsoftiInfAd()).add(oper.getChsoftiInfTravel())
				.add(oper.getChsoftiInfEquipment()).add(oper.getChsoftiInfSub()));
		oper.setChsoftiInfRmbtotal(oper.getChsoftiInfTotal().multiply( currencys.getExRate()).setScale(2, BigDecimal.ROUND_HALF_UP)) ;
		oper.setChsoftiInfCurrent(oper.getChsoftiInfTotal().subtract(oper.getChsoftiInvalid()).multiply( currencys.getExRate()).setScale(2, BigDecimal.ROUND_HALF_UP)) ;
		oper.setChsoftiEffectiveNr(oper.getChsoftiInfCurrent().divide(new BigDecimal("1.06"),2 , BigDecimal.ROUND_HALF_UP));
		if(oper.getChsoftiMskHours().compareTo(BigDecimal.ZERO)!=0) {
			oper.setChsoftiEffectiveSt(oper.getChsoftiAwHours().divide(oper.getChsoftiMskHours(),2 , BigDecimal.ROUND_HALF_UP));
			oper.setChsoftiInvalidSt(oper.getChsoftiIwHours().divide(oper.getChsoftiMskHours(),2, BigDecimal.ROUND_HALF_UP ));
		}
		return ;
	}
	
	private BigDecimal getWorkHour(final String employeeId,final String year,final String month) {
//		标准工时
		String  location = "China";
		if(StringUtils.isNotBlank(employeeId)) {
			location = employeeMapper.queryEmployeeById(employeeId).getStaffLocation() ;
		}
		BigDecimal v = BigDecimal.ZERO;
		if(StringUtils.isNotBlank(location) ) {
			WorkHourMapper workHourMapper = chinaWorkHourMapper ;
			WorkHour workHour = new ChinaWorkHour() ;
			if("HK".equals(location)) {
				workHourMapper = HKWorkHourMapper ;
				workHour = new HKWorkHour() ;
			}else if("Malaysia".equals(location)) { 
				workHourMapper = MLWorkHourMapper ;	
				workHour = new MLWorkHour() ;
			}
			workHour.setYear(year);
			workHour.setMonth(month+"月");
			v = workHourMapper.queryWorkHour(workHour); 
		}
		return v==null ? BigDecimal.ZERO:v;
	}
	
	private OfflineOper workHour(OfflineOper offlineOper) {
//		String  location = employeeMapper.queryEmployeeById(offlineOper.getEmployeeId()).getStaffLocation() ;
//		if(StringUtils.isNotBlank(location) ) {
//			WorkHourMapper workHourMapper = chinaWorkHourMapper ;
//			WorkHour workHour = new ChinaWorkHour() ;
//			if("HK".equals(location)) {
//				workHourMapper = HKWorkHourMapper ;
//				workHour = new HKWorkHour() ;
//			}else if("Malaysia".equals(location)) { 
//				workHourMapper = MLWorkHourMapper ;	
//				workHour = new MLWorkHour() ;
//			}
//			workHour.setYear(offlineOper.getYear());
//			workHour.setMonth(offlineOper.getMonth()+"月");
//			offlineOper.setChsoftiMskHours( workHourMapper.queryWorkHour(workHour)); 
//		}
		offlineOper.setChsoftiMskHours( getWorkHour(offlineOper.getEmployeeId(),offlineOper.getYear(),offlineOper.getMonth()));
		return offlineOper;
	}
	
	private List<OperSummary> summaryData(User user,String[] ids)  {
		List<OperSummary> returnLIst = new ArrayList<OperSummary>();
		final int YEAR = LocalDate.now().getYear();
		final int MONTH = LocalDate.now().getMonthValue();
		final int LENGTH = Constants.SUMMARY_TYPES.length ;
		if(user.isRM()) {
			ids = new String[] {""};
		}
		int count = 0;
		for(String id :ids) {
			List<OperSummary> rtn = new ArrayList<OperSummary>();
			for(int i=0;i< LENGTH;i++) {
				OperSummary r = new OperSummary();
				r.setId(""+(count*LENGTH+i));
				if(user.isRM()) {
					r.setDepartmentName(user.getNickname());
				}else {
					CSDept csDept = csDeptMapper.queryCSDeptById(id) ;
					r.setDepartmentName(csDept.getCsSubDeptName());
				}
				r.setType(Constants.SUMMARY_TYPES[i]);
				r.setRemark(Constants.SUMMARY_REMARKS[i]);
				rtn.add(r);
			}
			
			for (int i = 1; i <= MONTH; i++) {
				WorkHour wh = new WorkHour();
				wh.setYear(""+YEAR);
				wh.setMonth(""+i +"月");
				BigDecimal workDay = chinaWorkHourMapper.queryMonth(wh).getStandardWorkday();
				OfflineOperCondition condition = new OfflineOperCondition();
				condition.setYear(""+YEAR);
				condition.setMonth(""+i);
				if(user.isRM()) {
					condition.setRmId(user.getUserId()); 
				}
				if(StringUtils.isNotBlank(id)) {
					condition.setCsdeptid(id);
				}
				
				List<OperSummary> list = offlineOperMapper.querySummary(condition);
				// 转置 row ==> column
				if(list.get(0) != null) {
					OperSummary o = list.get(0) ;
					Class clazz = o.getClass(); 
					for(int j=0; j< LENGTH;j++) {
						OperSummary r = rtn.get(j);
						Map<String,BigDecimal> months ; 
						if(r.getMonth()!=null) {
							months = r.getMonth();
						}else {
							months = new HashMap<String,BigDecimal>();
						}
						try {
							Method m1 = clazz.getMethod(Constants.SUMMARY_METHODS[j]);
							BigDecimal value = (BigDecimal) m1.invoke(o); 
							if("getEffectiveHuman".equals(Constants.SUMMARY_METHODS[j]) && BigDecimal.ZERO.compareTo(workDay)!=0) {
								value = value.divide(workDay,2 , BigDecimal.ROUND_HALF_UP);
							}
							months.put("month"+i, value);
							r.setMonth(months);
							if(null != value) {
								r.setYearTotal(r.getYearTotal().add(value));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			returnLIst.addAll(rtn);
			count++;
		}
		return returnLIst;
	}
	
	@Override
	public List<OperSummary> querySummary(User user)  {
		List<OperSummary> rtn = null ;
//		for(int i=0;i< LENGTH;i++) {
//			OperSummary r = new OperSummary();
//			r.setId(""+i);
//			r.setDepartmentName(user.getNickname());
//			r.setType(TYPES[i]);
//			r.setRemark(REMARKS[i]);
//			rtn.add(r);
//		}
//		rtn.addAll(rtn);
//		PageHelper.startPage(pageNumber,pageSize); 	
			
		if(user.isRM()) { // RM
			rtn = this.summaryData(user,null);
		}else if(user.isSubDept()) {  // 交付部经理
			OfflineOperCondition condition = new OfflineOperCondition();
			List<CSDept> csDepts = csDeptMapper.queryCSDeptByIds(user.getCsdeptId().split(","));  // 交付部经理所在的部门
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
//			condition.setIds(ids);
			rtn = this.summaryData(user,ids);
			
		}else if(user.isDept()){ // 事业部经理
			OfflineOperCondition condition = new OfflineOperCondition();
			List<CSDept> csDepts = csDeptMapper.queryCSSubDeptNameByCsBuName(user.getBu());  // csBuName 根据事业部名称查
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			rtn = this.summaryData(user,ids);
		}else if(user.isAdmin()) { // admin
			OfflineOperCondition condition = new OfflineOperCondition();
			List<CSDept> csDepts = csDeptMapper.queryAllCSDept();  
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			rtn = this.summaryData(user,ids);
		}
		return rtn;
	}
}
