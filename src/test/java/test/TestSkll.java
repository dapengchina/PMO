package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.dao.EmployeeSkillMapper;
import com.pmo.dashboard.entity.EmployeeSkill;
import com.pmo.dashboard.service.impl.EmployeeServiceImpl;
import com.pom.dashboard.service.EmployeeSkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
		})
@WebAppConfiguration
public class TestSkll {
	@Resource
	private EmployeeSkillService employeeSkillService;
	
	@Resource
	private EmployeeSkillMapper employeeSkillMapper;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
//	@Test
	public void query() throws Exception  {
		String eHr = "E000830374";
		System.out.println(employeeSkillService.query(null).size());
	}
	
//	@Test
	public void detail() throws Exception  {
		String eHr = "E000830374";
		System.out.println(objectMapper.writeValueAsString(employeeSkillService.detail(eHr)));
	}
	
////	@Test
//	public void toEdit() throws Exception  {
//		String eHr = "E000830374";
//		eHr="E000240803";
//		System.out.println(objectMapper.writeValueAsString(employeeSkillService.toEdit(eHr)));
//	}
	
//	@Test
	public void queryEhrIds() throws Exception  {
		String eHr = "E000830374";
		eHr="E000240803";
		eHr="n";
		List<String> ids = employeeSkillMapper.queryEhrIds(eHr);
		System.out.println(ids.size());
	}
	
	@Test
	public void haveSkill() throws Exception  {
		String eHr = "E000830374";
		eHr="E000240803";
		eHr="E000826021";
		EmployeeSkill skill =new EmployeeSkill();
		skill.setEmployeeId(eHr);
		skill.setCapparamId("23-");
		System.out.println(employeeSkillService.haveSkill(skill));
	}
	
//	@Test
	public void toBatch() throws Exception  {
		String eHr = "E000830374";
		eHr="E000240803";
//		System.out.println(employeeSkillService.toBatch ().size());
		System.out.println(objectMapper.writeValueAsString(employeeSkillService.toBatch()));
	}
}
