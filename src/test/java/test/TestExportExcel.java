package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.ExportOfflineOperService;
import com.pom.dashboard.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
//		,"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" 
//		,"classpath:/conf/spring-mvc.xml"
		})
@WebAppConfiguration
public class TestExportExcel {
//	@Resource
//	private OfflineOperService offlineOperService;
//	
//	@Resource
//	private CSDeptService csDeptService;
//	
//	@Resource
//    private EmployeeService employeeService;
//	
	@Resource
	private UserService userService;
//	
//	@Resource
//	private CurrencysMapper currencysMapper;
	
	@Resource
	private ExportOfflineOperService exportOfflineOperService;
	
	@Test
	public void exportSummary() {
		User user = new User();
//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培  12
		
//		user.setUserId("c7b38226545c45e598f16a33031f85aa");  //jack li
		
//		user.setUserId("1573"); // 风控数据事业部  潘亮
		
		user.setUserId("1"); //admin
		
		user = userService.queryUserById(user.getUserId());
		System.out.println(exportOfflineOperService.exportSummary("交付部汇总（SEA）",user));
	}
	
	
//	@Test
	public void exportOfflie() {
		User user = new User();
//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培  12
////		user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰 9
//		user.setUserId("20f1aeff297d49d4b3c42877687a7076");  // 张盛  9,12
//		user.setUserId("a42f87d13fff455da434649ab3c8f876");  // 叶海伦
//		user.setUserType("5");
		
//		user.setUserType("3");
//		user.setUserId("c7b38226545c45e598f16a33031f85aa");
//		user.setCsdeptId("9,12");
		
//		user.setUserType("1"); 
//		user.setUserId("1573"); // 风控数据事业部  潘亮
//		user.setBu("风控数据事业部");
		
//		user.setUserType("0");
		user.setUserId("1");
		
		user = userService.queryUserById(user.getUserId());
		
		System.out.println(exportOfflineOperService.exportOfflineOper("过程数据",user));
	}
	
}
