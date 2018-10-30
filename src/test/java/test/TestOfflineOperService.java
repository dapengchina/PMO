package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.dao.CurrencysMapper;
import com.pmo.dashboard.dao.OfflineOperMapper;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.OfflineOperCondition;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.OfflineOperService;
import com.pom.dashboard.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/conf/spring-mybatis.xml"
		// ,"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"
		// ,"classpath:/conf/spring-mvc.xml"
})
@WebAppConfiguration
public class TestOfflineOperService {
	final static int PAGESIZE = 10, PAGENUMBER = 1;
	final static String YEAR = "" + LocalDate.now().getYear(), MONTH = "" + LocalDate.now().getMonthValue();
	@Resource
	private OfflineOperService offlineOperService;

	@Resource
	private OfflineOperMapper offlineOperMapper;

	@Resource
	private EmployeeService employeeService;

	@Resource
	private UserService userService;

	@Resource
	private CurrencysMapper currencysMapper;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void queryByRM() throws Exception {
		OfflineOperCondition condition = new OfflineOperCondition();
		condition.seteHr("E111111111");  // E100044240 E100044240
//		condition.setStaffName("白世铭");   //白世铭   汤俊承 
//		condition.setStaffName("汤俊承");
		
//		condition.seteHr("E000814351");
////		condition.setStaffName(" 王廣智");
//		condition.setStaffName("粱志建"); 
		 
		
		User user = new User();

		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b"); // 张培 12
////		//// user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb"); // 梁嘉杰 9
////		// user.setUserId("20f1aeff297d49d4b3c42877687a7076"); // 张盛 9,12
//		user.setUserId("a42f87d13fff455da434649ab3c8f876"); // 叶海伦
		user.setUserType("5");

//		user.setUserType("3");
//		// user.setUserId("c7b38226545c45e598f16a33031f85aa"); //
//		// c7b38226545c45e598f16a33031f85aa 李佳洲
//		user.setCsdeptId("9,12");

//		user.setUserType("1");
//		user.setUserId("1573"); // 风控数据事业部 潘亮
//		user.setBu("风控数据事业部");

		List<OfflineOper> list = offlineOperService.query(condition, user, 2 << 16, PAGENUMBER); // PAGESIZE
		PageInfo<OfflineOper> page = new PageInfo(list);

//		System.out.println(list.size());
//		System.out.println(page.getTotal());

		if(list.size()>0) {
			OfflineOper o = list.get(0);
			System.out.println(objectMapper.writeValueAsString(o));
		}
		
		
	}

	public void rmCount() {
		OfflineOper condition = new OfflineOper();
		condition.setYear(YEAR);
		condition.setMonth(MONTH);
		String rmId = "cb00bad3f16a4e8baf450e7b88af7c4b";
		condition.setRmId(rmId);
		;
		int count = offlineOperMapper.rmCount(condition);
		System.out.println(count);
	}

	public void queryFromEmployeeByRM() {

		OfflineOper condition = new OfflineOper();
		condition.setRmId("cb00bad3f16a4e8baf450e7b88af7c4b");

		// user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b"); // 张培
		// user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb"); // 梁嘉杰
		// user.setUserType("5");

		List<OfflineOper> list = offlineOperMapper.queryFromEmployeeByRM(condition);
		System.out.println(list.size());
	}

	public void employeeCount() {
		OfflineOper condition = new OfflineOper();
		String employeeId = "cb00bad3f16a4e8baf450e7b88af7c4b";
		condition.setEmployeeId(employeeId);
		int count = offlineOperMapper.employeeCount(condition);
		System.out.println(count);
	}

	public void queryRMFromDept() throws Exception {
		OfflineOper condition = new OfflineOper();
		condition.setEmployeeId("04fcb811aeae4808bb303aaf2cabba52");
		OfflineOper o = offlineOperMapper.queryByEmployeeID(condition);
		System.out.println(o);
		System.out.println(objectMapper.writeValueAsString(o));
	}

	private void initOfflineOper(OfflineOper data, User user) {
		Employee e = employeeService.queryEmployeeById(data.getEmployeeId());
		user = userService.queryUserById(user.getUserId());

		// data.setYear(YEAR);
		// data.setMonth(MONTH);
		data.setStaffName(e.getStaffName());
		data.seteHr(e.geteHr());
		data.setEmployeeId(e.getEmployeeId());
		data.setCsSubdeptId(e.getCsSubDept());
		data.setRmId(user.getUserId());
		data.setChsoftiAwHours(new BigDecimal("133"));
//		if ("".equals(user.getUserId())) {
//
//		}

		if (" 王廣智".equals(e.getStaffName())) {
			data.setChsoftiIwHours(new BigDecimal("19")); // 27 19
		} else {
			data.setChsoftiIwHours(new BigDecimal("27")); // 27 19
		}

		data.setChsoftiOtHours(new BigDecimal("8"));
		data.setChsoftiToHours(new BigDecimal("9"));
		data.setChsoftiApwHours(new BigDecimal("10"));
		data.setChsoftiInfTravel(new BigDecimal("11"));
		data.setChsoftiInfEquipment(new BigDecimal("12"));
		data.setChsoftiInfSub(new BigDecimal("13"));
		data.setRemark("test");
	}

	// public void queryCurrency() {
	// Currencys c = new Currencys();
	// c.setYear(YEAR);
	// c.setMonth(""+3);
	// c.setPlaceWork("HK");
	// c = currencysMapper.queryCurrency(c) ;
	// System.out.println(c.getExRate());
	// }
	
//	@Test
//	public void queryBySingleSubDept() {
//		OfflineOperCondition condition = new OfflineOperCondition();
//		condition.setYear(YEAR);
//		condition.setMonth(MONTH);
//		condition.setCsdeptid("9");
//		List<OfflineOper> list = offlineOperMapper.queryBySingleSubDept(condition);
//		System.out.println(list.size());
//	}

//	@Test
	public void saveYear() {
		final String YEAR = "2018";
		final int MONTH = LocalDate.now().getMonthValue();
		final String STAFFNAME = "武建海";  
			// "白世铭" "cb00bad3f16a4e8baf450e7b88af7c4b" ||    " 王廣智" "a42f87d13fff455da434649ab3c8f876"
		final String EMPLOYEEID = "1018"; 
				// "04fcb811aeae4808bb303aaf2cabba52" 白世铭  || "1024" 王涛   rm 张培
				// || "1004"  王廣智 rm  叶海伦
		        // || 1018     武建海     rm  张盛
			
		final String RMID = "20f1aeff297d49d4b3c42877687a7076";  
			//"cb00bad3f16a4e8baf450e7b88af7c4b"  张培 || "a42f87d13fff455da434649ab3c8f876" 叶海伦
		    // 20f1aeff297d49d4b3c42877687a7076     张盛  

		for (int i = 1; i <= MONTH; i++) {
			OfflineOperCondition condition = new OfflineOperCondition();
			condition.setYear(YEAR);
			condition.setMonth("" + i);

			OfflineOper data = new OfflineOper();
			data.setYear(YEAR);
			data.setMonth("" + i);

			data.setEmployeeId(EMPLOYEEID); // 白世铭
			data.setStaffName(STAFFNAME);
			User user = new User();
			user.setUserId(RMID); // 张培 12
			user.setUserType("5");
			condition.setStaffName(STAFFNAME);

			// data.setEmployeeId("1004"); // 王廣智
			// data.setStaffName(" 王廣智");
			// User user = new User();
			// user.setUserId("a42f87d13fff455da434649ab3c8f876"); // 叶海伦
			// user.setUserType("5");
			// condition.setStaffName(" 王廣智");

			List<OfflineOper> list = offlineOperMapper.queryOfflineOper(condition);
			for (OfflineOper o : list) {
				offlineOperService.delete(o.getId());
			}
			initOfflineOper(data, user);
			System.out.println(offlineOperService.save(data, user));
		}
	}

//	@Test
	public void save() {
		OfflineOperCondition condition = new OfflineOperCondition();
		condition.setYear("2018");
		condition.setMonth("4");

		OfflineOper data = new OfflineOper();
		data.setYear("2018");
		data.setMonth("4");

		data.setEmployeeId("04fcb811aeae4808bb303aaf2cabba52"); // 白世铭
		data.setStaffName("白世铭");
		User user = new User();
		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b"); // 张培 12
		user.setUserType("5");
		condition.setStaffName("白世铭");

		// data.setEmployeeId("1004"); // 王廣智
		// data.setStaffName(" 王廣智");
		// User user = new User();
		// user.setUserId("a42f87d13fff455da434649ab3c8f876"); // 叶海伦
		// user.setUserType("5");
		// condition.setStaffName(" 王廣智");

		// List<OfflineOper> list = offlineOperService.query(condition, user, PAGESIZE,
		// PAGENUMBER); //
		List<OfflineOper> list = offlineOperMapper.queryOfflineOper(condition);
		for (OfflineOper o : list) {
			offlineOperService.delete(o.getId());
		}
		// user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b"); // 张培 12
		////// user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb"); // 梁嘉杰 9
		//// user.setUserId("20f1aeff297d49d4b3c42877687a7076"); // 张盛 9,12
		// user.setUserType("5");

		// user.setUserType("3");
		//// user.setUserId("c7b38226545c45e598f16a33031f85aa"); //
		// c7b38226545c45e598f16a33031f85aa 李佳洲
		// user.setCsdeptId("9,12");

		// user.setUserType("1");
		//// user.setUserId("1573"); // 风控数据事业部 潘亮
		// user.setBu("风控数据事业部");

		initOfflineOper(data, user);
		System.out.println(offlineOperService.save(data, user));

	}
}
