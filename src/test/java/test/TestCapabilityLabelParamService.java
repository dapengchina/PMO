package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.CapabilityLabelParam;
import com.pom.dashboard.service.CapabilityLabelParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
		})
@WebAppConfiguration
public class TestCapabilityLabelParamService {
	@Resource
	CapabilityLabelParamService capabilityLabelParamService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
//	@Test
	public void maxSubCate() {
		String majorcateId = "001";
		String liushuihao = null;
		System.out.println(capabilityLabelParamService.maxSubCate(majorcateId));
	}
	
	@Test
	public void query() throws Exception {
		CapabilityLabelParam c = new CapabilityLabelParam();
		System.out.println(objectMapper.writeValueAsString(capabilityLabelParamService.query(c)));
	}
	
	@Test
	public void queryChilds() throws Exception {
//		CapabilityLabelParam c = new CapabilityLabelParam();
		System.out.println(objectMapper.writeValueAsString(capabilityLabelParamService.queryChilds()));
	}
}
;