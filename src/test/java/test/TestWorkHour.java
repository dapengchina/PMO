package test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pmo.dashboard.dao.ChinaWorkHourMapper;
import com.pmo.dashboard.dao.HKWorkHourMapper;
import com.pmo.dashboard.dao.MLWorkHourMapper;
import com.pmo.dashboard.dao.WorkHourMapper;
import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.HKWorkHour;
import com.pmo.dashboard.entity.MLWorkHour;
import com.pmo.dashboard.entity.WorkHour;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
//		,"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" 
//		,"classpath:/conf/spring-mvc.xml"
		})
@WebAppConfiguration
public class TestWorkHour {
//	@Resource
//	private ChinaWorkHourService chinaWorkHourService;
	
	@Resource
	ChinaWorkHourMapper chinaWorkHourMapper;
	
	@Resource
	HKWorkHourMapper HKWorkHourMapper;
	
	@Resource
	MLWorkHourMapper MLWorkHourMapper;
	
	@Test
	public void queryWorkHour() {
		final String YEAR = "2018" ;
		final String MONTH = "4月" ;
		final int INDEX=3;
		WorkHour workHour = new ChinaWorkHour();
		WorkHourMapper mapper ;
		workHour.setYear(YEAR);
		workHour.setMonth(MONTH);
		mapper = chinaWorkHourMapper;
		BigDecimal b = mapper.queryWorkHour(workHour) ;
		System.out.println(b.toString());
		WorkHour thisMonth = mapper.queryMonth(workHour);
		System.out.println(thisMonth.getStandardWorkday()+"\t"+thisMonth.getStandardWorkhour());
		List<WorkHour> list = mapper.queryYear("2018");
		Collections.sort(list);
		thisMonth = list.get(INDEX);
		System.out.println(thisMonth.getMonth()+"\t"+thisMonth.getStandardWorkday()+"\t"+thisMonth.getStandardWorkhour());
//		for(WorkHour w :list) {
//			System.out.println(w.getMonth());
//		}
		System.out.println();
		
		workHour = new HKWorkHour();
		workHour.setYear(YEAR);
		workHour.setMonth(MONTH);
		mapper = HKWorkHourMapper;
		b = mapper.queryWorkHour(workHour);
		System.out.println(b.toString());
		thisMonth = mapper.queryMonth(workHour);
		System.out.println(thisMonth.getStandardWorkday()+"\t"+thisMonth.getStandardWorkhour());
		list = mapper.queryYear(YEAR);
		Collections.sort(list);
		thisMonth = list.get(INDEX);
		System.out.println(thisMonth.getMonth()+"\t"+thisMonth.getStandardWorkday()+"\t"+thisMonth.getStandardWorkhour());
//		for(WorkHour w :list) {
//			System.out.println(w.getMonth());
//		}
		System.out.println();
		
		workHour = new MLWorkHour();
		workHour.setYear(YEAR);
		workHour.setMonth(MONTH);
		mapper = MLWorkHourMapper;
		b = mapper.queryWorkHour(workHour);
		System.out.println(b.toString());
		thisMonth = mapper.queryMonth(workHour);
		System.out.println(thisMonth.getStandardWorkday()+"\t"+thisMonth.getStandardWorkhour());
		list = mapper.queryYear(YEAR);
		Collections.sort(list);
		thisMonth = list.get(INDEX);
		System.out.println(thisMonth.getMonth()+"\t"+thisMonth.getStandardWorkday()+"\t"+thisMonth.getStandardWorkhour());
//		for(WorkHour w :list) {
//			System.out.println(w.getMonth());
//		}
	}

}
