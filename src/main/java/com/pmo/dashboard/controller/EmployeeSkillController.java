package com.pmo.dashboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.CapabilityLabelParam;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeSkill;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.CapabilityLabelParamService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeSkillService;

@Controller
@RequestMapping(value = "/skill")
public class EmployeeSkillController {
	private static Logger logger = LoggerFactory.getLogger(EmployeeSkillController.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Resource
	private EmployeeSkillService employeeSkillService;

	@Resource
	private EmployeeService employeeService;

	@Resource
	private CapabilityLabelParamService capabilityLabelParamService;

	@RequestMapping("/listPage")
	public String listPage(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("loginUser");
		return "skill/employeeSkillList";
	}

	@RequestMapping("/query")
	@ResponseBody
	public String query(int pageSize, int pageNumber, EmployeeSkill condition, HttpServletRequest request)
			throws JsonProcessingException {
		PageHelper.startPage(pageNumber, pageSize);
		User user = (User) request.getSession().getAttribute("loginUser");
		List<EmployeeSkill> data = employeeSkillService.query(condition);
		PageInfo<OfflineOper> page = new PageInfo(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}

	@RequestMapping("/queryById")
	@ResponseBody
	public Object queryById(String id) {
		return employeeSkillService.select(id);
	}

	@RequestMapping("/skills")
	@ResponseBody
	public Object skill(String id) {
		return employeeSkillService.skills();
	}

	@RequestMapping("/detail/{eHr}")
	@ResponseBody
	public String detail(@PathVariable String eHr) throws JsonProcessingException {
		return objectMapper.writeValueAsString(employeeSkillService.detail(eHr));
	}

	@RequestMapping("/toEdit/{eHr}")
	@ResponseBody
	public String toEdit(@PathVariable String eHr) throws JsonProcessingException {
		return objectMapper.writeValueAsString(employeeSkillService.toEdit(eHr));
	}

	@RequestMapping(value = { "/save" })
	@ResponseBody
	public String save(EmployeeSkill skill, HttpServletRequest request) throws JsonProcessingException {
		User user = (User) request.getSession().getAttribute("loginUser");
		skill.setOperateId(user.getUserId());
		boolean rtn;
		if ("0".equals(skill.getStatus())) {
			rtn = employeeSkillService.insert(skill);
		} else {
			rtn = employeeSkillService.update(skill);
		}
		return objectMapper.writeValueAsString(rtn ? "1" : "0");
	}

	@RequestMapping(value = { "/delete/{id}" })
	@ResponseBody
	public String delete(@PathVariable String id, HttpServletRequest request) throws JsonProcessingException {
		User user = (User) request.getSession().getAttribute("loginUser");
		boolean rtn;
		rtn = employeeSkillService.delete(id);
		return objectMapper.writeValueAsString(rtn ? "1" : "0");
	}

	@RequestMapping("/toBatch")
	@ResponseBody
	public String toBatch() throws JsonProcessingException {
		return objectMapper.writeValueAsString(employeeSkillService.toBatch());
	}

	@RequestMapping(value = { "/batch" })
	@ResponseBody
	public String batch(EmployeeSkill skill, HttpServletRequest request) throws JsonProcessingException {
		User user = (User) request.getSession().getAttribute("loginUser");
		skill.setOperateId(user.getUserId());
		boolean rtn;
		rtn = employeeSkillService.batch(skill);
		return objectMapper.writeValueAsString(rtn ? "1" : "0");
	}

	@RequestMapping(value = "/skillUpload", method = RequestMethod.POST)
	public void skillUpload(@RequestParam MultipartFile myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User) request.getSession().getAttribute("loginUser");
		String operateId = user.getUserId();
		String tmpdir = System.getProperty("java.io.tmpdir");
		String now = LocalDateTime.now().toString().replace(":", "");
		String fileName = now + "-" + myfiles.getOriginalFilename();
		File excelFile = new File(tmpdir, fileName);
		FileUtils.copyInputStreamToFile(myfiles.getInputStream(), excelFile);
		List<EmployeeSkill> skillList = importSkill(excelFile, operateId);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print("<title>Batch Upload</title>");
		if(skillList.size()==0) {
			writer.println("The excel file hasn't  found skill 'sheet' or it hasn't data." + "<br/>");
		}else {
			importSkill2DB(skillList, writer);
		}
		excelFile.delete();
		// return "redirect:listPage";
	}

	private List<EmployeeSkill> importSkill(File file, String operateId) throws IOException {
		final int MONTH_STAER_ROW = 1; // skill.xlsx 数据起始行
		InputStream is = new FileInputStream(file);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List<EmployeeSkill> list = new ArrayList<EmployeeSkill>();
		XSSFSheet xssfSheet = xssfWorkbook.getSheet("skill"); // xssfWorkbook.getSheet("skill");

		if (xssfSheet == null) {
			return list;
		}
		for (int rowNum = MONTH_STAER_ROW; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {

				EmployeeSkill skill = new EmployeeSkill();
				skill.setEmployeeId(getValue(xssfRow.getCell(0)));
				skill.seteHr(skill.getEmployeeId());
				skill.setParamName(getValue(xssfRow.getCell(1)));
				skill.setAbilityLevel(getValue(xssfRow.getCell(2)));
				skill.setOfficialAccreditation(getValue(xssfRow.getCell(3)).equalsIgnoreCase("yes")?"1":"0");
				skill.setAuthenticationName(getValue(xssfRow.getCell(4)));
				skill.setWorkExperience(getValue(xssfRow.getCell(5)));
				String t = skill.getWorkExperience();
				if(t.indexOf(".")>0) {
					t = t.substring(0,t.indexOf("."));
					skill.setWorkExperience(t);
				}
				skill.setMainAbility("1");
				skill.setOperateId(operateId);
				list.add(skill);
			}
		}
		xssfWorkbook.close();
		is.close();
		return list;
	}

	private void importSkill2DB(List<EmployeeSkill> list, PrintWriter writer) throws IOException {
		String now = LocalDateTime.now().toString().replace(":", "").substring(0, 17);
		// String errorFileName = importErrorFile + "-" + now + ".txt";
		// BufferedWriter writer = new BufferedWriter(new FileWriter(errorFileName));
		// List<String> errorList = new ArrayList<String>();
		int row = 0;
		int total = list.size();
		for (EmployeeSkill skill : list) {
			List<Employee> employeeList = employeeService.selectByEhr(skill.geteHr());
			if (employeeList.size() == 0) {
				writer.println("eHr: "+skill.geteHr() + " not found." + "<br/>");
				continue;
			}
			CapabilityLabelParam p = new CapabilityLabelParam();
			p.setParamName(skill.getParamName());
			List<CapabilityLabelParam> r = capabilityLabelParamService.query(p);
			if (r.size() == 0) {
				writer.println(skill.geteHr() + ": ABILITY Name '"+ skill.getParamName() +"'  not found." + "<br/>");
				continue;
			}

			EmployeeSkill condition = new EmployeeSkill();
			condition.seteHr(skill.geteHr());
			condition.setMainAbility("1");
			List<EmployeeSkill> dbSkills = employeeSkillService.query(condition);
			employeeSkillService.cleanMainSkill(skill.geteHr());
			if (dbSkills.size() == 1 ) {
				if (dbSkills.get(0).getId() != null) {
					skill.setId(dbSkills.get(0).getId());
					employeeSkillService.update(skill);
				} else {
					// errorList.add(skill.geteHr());
					employeeSkillService.insert(skill);
				}
			}
			row++;
			if (row % 100 == 0) {
				writer.print("A total of " + total + " rows of data are being poured into " + row + " of data." + "<br/>");
			}
		}
		writer.print("Pour complete, a total of "+ row + " rows of data." + "<br/>");
		writer.print("<input type='button' onclick='javascript:window.close()' value='Close'/>");
		// writer.close();
	}

	@SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow == null) {
			return "";
		}
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}
}
