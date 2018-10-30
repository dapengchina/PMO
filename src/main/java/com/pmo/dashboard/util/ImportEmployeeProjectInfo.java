package com.pmo.dashboard.util;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pom.dashboard.service.EmployeeService;

@Component
public class ImportEmployeeProjectInfo {
	private String importMonthDataFilePath;   // 月滚汇总表文件路径
	private String importPrjectInfoFilePath;  // 项目信息文件路径
	private String importErrorFile; // 倒入出错员工eHR号

	@Resource
	private EmployeeService employeeService;

	@Value("${importMonthDataFilePath}")
	public void setImportMonthDataFilePath(String importMonthDataFilePath) {
		this.importMonthDataFilePath = importMonthDataFilePath;
	}

	public String getImportMonthDataFilePath() {
		return importMonthDataFilePath;
	}

	public String getImportPrjectInfoFilePath() {
		return importPrjectInfoFilePath;
	}

	@Value("${importPrjectInfoFilePath}")
	public void setImportPrjectInfoFilePath(String importPrjectInfoFilePath) {
		this.importPrjectInfoFilePath = importPrjectInfoFilePath;
	}

	public String getImportErrorFile() {
		return importErrorFile;
	}

	@Value("${importErrorFile}")
	public void setImportErrorFile(String importErrorFile) {
		this.importErrorFile = importErrorFile;
	}

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/spring-mybatis.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean(EmployeeService.class);
		// System.out.println(employeeService.getAllEmployee().size()); // Invalid bound
		ImportEmployeeProjectInfo employeeInfo = (ImportEmployeeProjectInfo) context
				.getBean(ImportEmployeeProjectInfo.class);
		System.out.println("月滚汇总表文件:\t"+employeeInfo.getImportMonthDataFilePath());
		String importMonthDataFilePath = employeeInfo.getImportMonthDataFilePath();
		System.out.println("项目信息文件:\t"+employeeInfo.getImportPrjectInfoFilePath());
		
		
		String importPrjectInfoFilePath = employeeInfo.getImportPrjectInfoFilePath();
		Map<String, String> projectInfos = importProjectInfo(importPrjectInfoFilePath);
		List<Employee> employeeList = importEmployeeInfo(importMonthDataFilePath);
		
//		System.out.println(projectInfos.size());
//		System.out.println(employeeList.size());
		
		importEmployeeProject2DB(projectInfos, employeeList, employeeService, employeeInfo.getImportErrorFile());
		System.out.println("倒入完成");
	}

	/**
	 * 倒入员工项目信息到数据库
	 * @param prjectInfos
	 * @param list
	 * @param employeeService
	 * @param importErrorFile
	 * @throws IOException
	 */
	private static void importEmployeeProject2DB(Map<String, String> prjectInfos, List<Employee> list,
			EmployeeService employeeService, String importErrorFile) throws IOException {
		String now = LocalDateTime.now().toString().replace(":", "").substring(0, 17);
		String errorFileName = importErrorFile + "-" + now + ".txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(errorFileName));
		int row = 0;
		int total = list.size();
		for (Employee employee : list) {
			EmployeePageCondition employeePageCondition = new EmployeePageCondition();
			employeePageCondition.seteHr(employee.geteHr());
			List<Employee> dbEmps = employeeService.queryEmployeeList(employeePageCondition);
			if (dbEmps.size() == 1) {
				employee.setEmployeeId(dbEmps.get(0).getEmployeeId());
				employee.setChsoftiProStartdate(prjectInfos.get(employee.getChsoftiProNumber()));
				employeeService.importEmployeeProject(employee);
			} else if (dbEmps.size() == 0) {
				writer.write(employee.geteHr());
				writer.newLine();
			}
			row++;
			if (row % 100 == 0) {
				System.out.println("共" + total + "行数据,正在倒入" + row + "行数据");
			}
		}
		writer.close();
	}

	/**
	 * 倒入项目基本信息到Map
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static Map<String, String> importProjectInfo(String filePath) throws IOException {
		final int PROJECT_STAER_ROW = 1; // 项目基本信息.xlsx 数据起始行
		InputStream is = new FileInputStream(filePath);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Employee employee = null;
		// List<Employee> list = new ArrayList<Employee>();
		Map<String, String> map = new HashMap();
		// Read the Sheet
		XSSFSheet xssfSheet = xssfWorkbook.getSheet("项目基本信息");
		if (xssfSheet == null) {
			return map;
		}
		for (int rowNum = PROJECT_STAER_ROW; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				String chsoftiProNumber = getValue(xssfRow.getCell(1)); // 项目编号
				String chsoftiProStartdate = getValue(xssfRow.getCell(7)); // 项目开始时间
				if (StringUtils.isNotBlank(chsoftiProNumber)) {
					map.put(chsoftiProNumber, chsoftiProStartdate);
				}
			}
		}
		xssfWorkbook.close();
		is.close();
		return map;
	}

	// public static void importFileJxl(String filePath) {
	// final int SHEETINDEX = 1; // 过程数据
	// List<Employee> list = new ArrayList<Employee>();
	// try {
	// Workbook rwb = Workbook.getWorkbook(new File(filePath));
	// Sheet rs = rwb.getSheet(SHEETINDEX);
	// int clos = rs.getColumns();
	// int rowCount = rs.getRows();
	//
	// // for (int j = 0; j < rs.getRows(); j++) {
	// // Cell[] cells = rs.getRow(j);
	// // for(int k=0;k<cells.length;k++) {
	// //// sb.append(cells[k].getContents());
	// // }
	// // }
	//
	// for (int row = 1; row <= 2; row++) {
	// Employee employee = new Employee();
	//
	// // String employeeId = Utils.getUUID();
	// String value = rs.getCell(5, row).getContents();
	// System.out.println(value);
	// employee.setEmployeeId(rs.getCell(5, row).getContents());
	//
	// /*
	// * list.add(new
	// * Employee(employeeId,hsbcStaffId,staffName,ln,staffRegion,staffLocation,
	// * locationType,onShoreOrOffShore,sow,sowExpiredDate,staffCategory,
	// * engagementType,
	// * hsbcDoj,experienceONHSBC,graduationDate,totalExperience,billingEntity,
	// * billCurrency,
	// * billRate,resourceStatus,mentionLWD,reasonForTermination,eHr,nicheSkill,
	// * hsbcProjectId,role,skill,csSubDeptId));
	// */
	//
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return;
	// }

	/**
	 * 倒入员工项目信息到List
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static List<Employee> importEmployeeInfo(String filePath) throws IOException {
		final int MONTH_STAER_ROW = 2; // 月滚汇总表.xlsx 数据起始行
		InputStream is = new FileInputStream(filePath);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Employee employee = null;
		List<Employee> list = new ArrayList<Employee>();
//		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
//			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			XSSFSheet xssfSheet = xssfWorkbook.getSheet("过程数据");
			if (xssfSheet == null) {
				return list;
			}
			// 
			for (int rowNum = MONTH_STAER_ROW; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					employee = new Employee();
					XSSFCell no = xssfRow.getCell(0);
					XSSFCell name = xssfRow.getCell(1);
					XSSFCell age = xssfRow.getCell(2);

					// student.setScore(Float.valueOf(getValue(score)));
					employee.seteHr(getValue(xssfRow.getCell(5))); // E 中软工号
					employee.setStaffName(getValue(xssfRow.getCell(6))); // 姓名
					employee.setChsoftiProNumber(getValue(xssfRow.getCell(7))); // 项目编号
					employee.setChsoftiProName(getValue(xssfRow.getCell(8))); // 项目名称

					list.add(employee);
				}
			}
//		}
		xssfWorkbook.close();
		is.close();
		return list;
	}

	@SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

}
