package test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.dao.CurrencysMapper;
import com.pmo.dashboard.dao.OfflineOperMapper;
import com.pmo.dashboard.entity.OfflineOperCondition;
import com.pmo.dashboard.entity.OperSummary;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.OfflineOperService;
import com.pom.dashboard.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
//		,"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" 
//		,"classpath:/conf/spring-mvc.xml"
		})
@WebAppConfiguration
public class TestOperSummary {
	final static int PAGESIZE = 10, PAGENUMBER = 1;
	
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
	
	public void testJson() throws Exception {
		OperSummary o = new OperSummary();
		o.setId("1");
		o.setDepartmentName("fengkongjiaofubu");
		o.setRemark("zonghe");
		o.setType("shouru1");
		Map<String,BigDecimal> month = new HashMap<String,BigDecimal>();
		month.put("month1", BigDecimal.ONE);
		month.put("month2", BigDecimal.TEN);
		o.setMonth(month);
		System.out.println(objectMapper.writeValueAsString(o));
	}
	
	@Test
	public void querySummaryService() throws Exception{
		User user = new User();
		
		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b"); // 张培 12
//		//// user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb"); // 梁嘉杰 9
//		// user.setUserId("20f1aeff297d49d4b3c42877687a7076"); // 张盛 9,12
//		user.setUserType("5");

//		user.setUserType("3");
//		user.setUserId("c7b38226545c45e598f16a33031f85aa"); //
		// c7b38226545c45e598f16a33031f85aa 李佳洲
//		user.setCsdeptId("9,12");
//
		
//		user.setUserType("1");
//		user.setUserId("1573"); // 风控数据事业部 潘亮
//		user.setBu("风控数据事业部");
		
//		user.setUserId("1");  // admin
		
		user = this.userService.queryUserById(user.getUserId());
		List<OperSummary> rtn = offlineOperService.querySummary(user);
		if(rtn != null) {
//			System.out.println(rtn.size());
			for(OperSummary o :rtn) {
				System.out.println(objectMapper.writeValueAsString(o));
			}
		}
		
	}
	
//	@Test
	public void querySummaryMapper() throws Exception {
	//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培  12
	//////	user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰 9
	////	user.setUserId("20f1aeff297d49d4b3c42877687a7076");  // 张盛  9,12
//		user.setUserId("a42f87d13fff455da434649ab3c8f876");  // 叶海伦  
	//	user.setUserType("5");
		
	//	user.setUserType("3");  
	////	user.setUserId("c7b38226545c45e598f16a33031f85aa"); // c7b38226545c45e598f16a33031f85aa 李佳洲
	//	user.setCsdeptId("9,12");
		
	//	user.setUserType("1"); 
	////	user.setUserId("1573"); // 风控数据事业部  潘亮
	//	user.setBu("风控数据事业部");
		
		OfflineOperCondition condition = new OfflineOperCondition();
		condition.setYear("2018");
		condition.setMonth("6");
		condition.setRmId("cb00bad3f16a4e8baf450e7b88af7c4b"); 
//		condition.setCsdeptid("12");
		List<OperSummary> list = offlineOperMapper.querySummary(condition);
		System.out.println(list.size());
		if(list.get(0) == null ) {
			System.out.println("no data.");
		}else {
//			System.out.println(objectMapper.writeValueAsString(list.get(0)));
			System.out.println(list.size());
		}
		
	}
}
