
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.OfflineOper;

@Controller
@RequestMapping(value="/testOper")
public class TestController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@RequestMapping(value= {"/test"},method=RequestMethod.POST)
	@ResponseBody
	public String test(@RequestBody OfflineOper offo,HttpServletRequest request) throws JsonProcessingException{
		System.out.println("dddddd");
		return objectMapper.writeValueAsString("0");
	}

}
