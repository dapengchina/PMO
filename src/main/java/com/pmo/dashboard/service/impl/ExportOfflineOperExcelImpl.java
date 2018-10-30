package com.pmo.dashboard.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pmo.dashboard.dao.ChinaWorkHourMapper;
import com.pmo.dashboard.dao.CurrencysMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Currencys;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.OperSummary;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.WorkHour;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.ExportOfflineOperService;
import com.pom.dashboard.service.OfflineOperService;
import com.pom.dashboard.service.UserService;

@Component
public class ExportOfflineOperExcelImpl implements  ExportOfflineOperService{
	
	@Resource
	private OfflineOperService offlineOperService;
	
	@Resource
	private CSDeptService csDeptService;
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private UserService userService;
	
	@Resource
	ChinaWorkHourMapper chinaWorkHourMapper;
	
//	public OfflineOperService getOfflineOperService() {
//		return offlineOperService;
//	}
//	public CSDeptService getCsDeptService() {
//		return csDeptService;
//	}
//	public EmployeeService getEmployeeService() {
//		return employeeService;
//	}
//	public UserService getUserService() {
//		return userService;
//	}
//	public CurrencysMapper getCurrencysMapper() {
//		return currencysMapper;
//	}


	@Resource
	private CurrencysMapper currencysMapper;
	
	private String exportFile;
	
//	public String getExportFile() {
//		return exportFile;
//	}
	@Value("${exportFile}")
	public void setExportFile(String exportFile) {
		this.exportFile = exportFile;
	}
	
	private String toValue(BigDecimal b) {
		if(null == b) {
			return "";
		}else if(BigDecimal.ZERO.compareTo(b)==0){
			return "";
		}
		return b.toPlainString() ;
	}
	
	public String exportOfflineOper(String sheetName, User user ) {
		List<String[]> dataList = exportOfflieOper(user);
		return exportOfflieOperExcel(dataList, exportFile, sheetName);
	}
	
	@Override
	public String exportSummary(String sheetName, User user) {
		final int summaryLength = 16 ;
		int thisYear = LocalDate.now().getYear();
		List<OperSummary> summaryList = offlineOperService.querySummary(user);
		List<WorkHour> workHourList = chinaWorkHourMapper.queryYear(""+thisYear);
		Collections.sort(workHourList);
		
		//export summary excel
//		int[] blues = {16,17,18,19,20,26,27,28,32};
//		String[] cellTitle = dataList.get(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String now = dateFormat.format(new Date());
		// 导出文件路径
		//String basePath = "D:/";
		// 文件名
		String exportFileName = exportFile +"_" + LocalDateTime.now().toString().replace(":", "") + ".xlsx";

		// 需要导出的数据
		// 声明一个工作薄
		XSSFWorkbook workBook = null;
		workBook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, sheetName);
		// 创建表格标题行 第一行
		XSSFRow firstRow = sheet.createRow(0);
		for(int i=3;i<16;i++) {
			sheet.setColumnWidth(i, 11 * 256);
		}
		
//		firstRow.setHeightInPoints(30);
		CellStyle whiteStyle = workBook.createCellStyle();
        whiteStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        whiteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		whiteStyle.setAlignment(HorizontalAlignment.LEFT);
		whiteStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
		
		whiteStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		whiteStyle.setBorderTop(CellStyle.BORDER_THIN);   
		whiteStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		whiteStyle.setBorderRight(CellStyle.BORDER_THIN);  
		
		Font titleFont = workBook.createFont();  
		titleFont.setFontName("微软雅黑"); 
		titleFont.setFontHeightInPoints((short)9);
//		titleFont.setBold(true);
		whiteStyle.setFont(titleFont);
		
		CellStyle noneButtomStyle = workBook.createCellStyle();
		noneButtomStyle.cloneStyleFrom(whiteStyle);
		noneButtomStyle.setBorderBottom(CellStyle.BORDER_NONE);   
		
		CellStyle none4Style = workBook.createCellStyle();
		none4Style.cloneStyleFrom(noneButtomStyle);
		none4Style.setBorderTop(CellStyle.BORDER_NONE);   
		none4Style.setBorderLeft(CellStyle.BORDER_NONE);   
		none4Style.setBorderRight(CellStyle.BORDER_NONE);   
		
//		noneStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());  // BLUE BLUE_GREY SKY_BLUE BLUE_LIGHT
//		noneStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Font dataFont = workBook.createFont();  
		dataFont.setFontName("微软雅黑"); 
		dataFont.setFontHeightInPoints((short)9);
		
		CellStyle numberStyle = workBook.createCellStyle();
		numberStyle.setFont(dataFont);
//		numberStyle.setDataFormat(XSSFDataFormat  .getBuiltinFormat("0.00"));
		numberStyle.setAlignment(HorizontalAlignment.RIGHT);
		numberStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		numberStyle.setBorderTop(CellStyle.BORDER_THIN);   
		numberStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		numberStyle.setBorderRight(CellStyle.BORDER_THIN);  
//		numberStyle.setDataFormat(XSSFDataFormat);    // .getBuiltinFormat("0.00")
		XSSFDataFormat df = workBook.createDataFormat();
		numberStyle.setDataFormat(df.getFormat("#,##0.00"));
		
		CellStyle dataStyle = workBook.createCellStyle();
		dataStyle.setFont(dataFont);
//		numberStyle.setDataFormat(XSSFDataFormat  .getBuiltinFormat("0.00"));
		dataStyle.setAlignment(HorizontalAlignment.LEFT);
//		dataStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);   
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);  
		
		BigDecimal wd = BigDecimal.ZERO;
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 1);   
		sheet.addMergedRegion(cra);  
		for (int i = 0; i < summaryLength; i++) {
			XSSFCell cell = firstRow.createCell(i);
//			cell.setCellValue(cellTitle[i]);
			cell.setCellStyle(whiteStyle);
			if(i<workHourList.size()) {
				wd = wd.add(workHourList.get(i).getStandardWorkday());
			}
			if(i>2 && i <15) {
//				cell = firstRow.createCell(i, Cell.CELL_TYPE_NUMERIC);
//				cell = firstRow.createCell(i, CellType.NUMERIC);
//				cell.set
				cell.setCellValue(toValue(workHourList.get(i-3).getStandardWorkday()));
				cell.setCellStyle(numberStyle);
			}else if(i==0) {
				cell.setCellValue("单位：k");
			}else if(i==2) {
				cell.setCellValue(""+thisYear+"年月工作日");
			}else if(i==15) {
//				cell = firstRow.createCell(i, CellType.NUMERIC);
				cell.setCellValue(""+toValue(wd.divide(new BigDecimal("12"),2, BigDecimal.ROUND_HALF_UP )));
				cell.setCellStyle(numberStyle);
			}
			
//			if(ArrayUtils.contains(blues,i) ) {
//				cell.setCellStyle(bluetyle);
//			}
		}
		XSSFRow titleRow = sheet.createRow(1);
		for (int i = 0; i < summaryLength; i++) {
			XSSFCell cell = titleRow.createCell(i);
			if(i>2 && i <15) {
				cell.setCellValue(workHourList.get(i-3).getMonth());
			}else if(i==0) {
				cell.setCellValue("xx交付部");
			}else if(i==1) {
				cell.setCellValue("类别");
			}else if(i==2) {
				cell.setCellValue("说明");
			}else if(i==15) {
				cell.setCellValue(""+thisYear+"年合计");
			}
			cell.setCellStyle(whiteStyle);
		}
		
//		 插入需导出的数据
		String lastName = "";
		for (int i = 0; i < summaryList.size(); i++) {
			XSSFRow row = sheet.createRow(i+2);
			OperSummary summary =  summaryList.get(i);
			for (int j = 0; j < summaryLength; j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellStyle(whiteStyle);
				if(j>2 && j <15) {
					cell = row.createCell(j, CellType.NUMERIC);
					cell.setCellStyle(numberStyle);
					cell.setCellValue(toValue(summary.getMonth().get("month"+(j-2))));
				}else if(j==0) {
					if(!lastName.equals(summary.getDepartmentName())) {
						cell.setCellStyle(noneButtomStyle);
						cell.setCellValue(summary.getDepartmentName());
					}else {
						cell.setCellStyle(none4Style);
					}
					
				}else if(j==1) {
					cell.setCellValue(Constants.SUMMARY_TYPES[i%Constants.SUMMARY_TYPES.length]);
				}else if(j==2) {
					cell.setCellValue(Constants.SUMMARY_REMARKS[i%Constants.SUMMARY_TYPES.length]);
				}else if(j==15) {
					cell = row.createCell(j, CellType.NUMERIC);
					cell.setCellStyle(numberStyle);
					cell.setCellValue(toValue(summary.getYearTotal()));
				}
				
			}
			lastName = summary.getDepartmentName();
		}
		
		// 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 16);
		
		File file = new File(exportFileName);
		// 文件输出流
		try {
			FileOutputStream outStream = new FileOutputStream(file);
			workBook.write(outStream);
			workBook.close();
			outStream.flush();
			outStream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
//		System.out.println("导出成功！" /* + basePath */ + exportFileName);
		return exportFileName;
	};
	
	private List<String[]> exportOfflieOper(User user) {
		List<String[]> dataList = new ArrayList<String[]>();
		
		dataList.add(Constants.CELLTITLES);
		
		OfflineOper condition = new OfflineOper();
		
		List<OfflineOper> list = offlineOperService.exportOfflieOperData(user) ; 
		
		for(OfflineOper o:list) {
			String[] row = new String[Constants.CELLTITLES.length];
			Employee e = employeeService.queryEmployeeById(o.getEmployeeId())  ;
			CSDept csdept = csDeptService.queryCSDeptById(e.getCsSubDept()) ; 
//			csDeptService.queryCSDeptById(e.getCsSubDeptName())
			
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
			
			row[0] = o.getMonth()+"月";
			row[1] = "汇丰业务线";
			row[2] = csdept == null ?"": csdept.getCsBuName();  // user.getBu() 
			row[3] = e.getCsSubDeptName();  // e.getCsSubDeptName()  user.getCsDept().getCsSubDeptName()
			row[4] = e.getEngagementType() ; 
			
			row[5] = o.geteHr();
			row[6] = o.getStaffName();
			row[7] = e.getChsoftiProNumber();
			row[8] = e.getChsoftiProName();
			row[9] = e.getStaffLocation();
			
			row[10] = e.getSkill();
			row[11] = e.getRole();
			row[12] = StringUtils.isBlank(e.getBillingCurrency()) ?currencys.getCurrency():e.getBillingCurrency();
			row[13] = toValue(currencys.getExRate());  //当月汇率
			row[14] = e.getBillRate();
//			
			row[15] = toValue( o.getChsoftiMskHours() );
			row[16] = toValue(o.getChsoftiAwHours() );
			row[17] = toValue(o.getChsoftiIwHours());
			row[18] = toValue(o.getChsoftiOtHours());
			row[19] = toValue(o.getChsoftiToHours());
//			
			row[20] = toValue(o.getChsoftiApwHours());
			row[21] = toValue(o.getChsoftiIfaw());
			row[22] = toValue(o.getChsoftiInvalid());
			row[23] = toValue(o.getChsoftiInfOt());
			row[24] = toValue(o.getChsoftiInfPt());
//			
			row[25] = toValue(o.getChsoftiInfAd());
			row[26] = toValue(o.getChsoftiInfTravel());
			row[27] = toValue(o.getChsoftiInfEquipment());
			row[28] = toValue(o.getChsoftiInfSub());
			row[29] = toValue(o.getChsoftiInfTotal());   // 收入合计-原币种
//			
			row[30] = toValue(o.getChsoftiInfTotal().multiply(currencys.getExRate()).setScale(2, BigDecimal.ROUND_HALF_UP));  //收入合计-RMB
			row[31] = toValue(o.getChsoftiInfCurrent()); 
			row[32] = toValue(o.getChsoftiEffectiveNr());
			row[33] = toValue(o.getChsoftiEffectiveSt());
			row[34] = toValue(o.getChsoftiInvalidSt());
//			
			if(null == o.getRmName()) {
				User rm = userService.queryUserById(e.getRmUserId());
				if(rm != null) {
					row[35] = rm.getNickname();
				}
				
			}else {
				row[35] = o.getRmName();
			}
			
			row[36] = o.getRemark();
			
			dataList.add(row) ;
		}
		return dataList;
		
	}
	

	private String exportOfflieOperExcel(List<String[]> dataList, String fileName, String sheetName)  {
		int[] blues = {16,17,18,19,20,26,27,28,32};
		String[] cellTitle = dataList.get(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String now = dateFormat.format(new Date());
		// 导出文件路径
		//String basePath = "D:/";
		// 文件名
		String exportFileName = fileName +"_" + LocalDateTime.now().toString().replace(":", "") + ".xlsx";

		// 需要导出的数据
		// 声明一个工作薄
		XSSFWorkbook workBook = null;
		workBook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, sheetName);
		// 创建表格标题行 第一行
		XSSFRow titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(30);
		CellStyle yellowStyle = workBook.createCellStyle();
        yellowStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        yellowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		yellowStyle.setAlignment(HorizontalAlignment.CENTER);
		yellowStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
		
		yellowStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		yellowStyle.setBorderTop(CellStyle.BORDER_THIN);   
		yellowStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		yellowStyle.setBorderRight(CellStyle.BORDER_THIN);  
		
		Font titleFont = workBook.createFont();  
		titleFont.setFontName("微软雅黑"); 
		titleFont.setFontHeightInPoints((short)9);
		titleFont.setBold(true);
		yellowStyle.setFont(titleFont);
		
		CellStyle bluetyle = workBook.createCellStyle();
		bluetyle.cloneStyleFrom(yellowStyle);
		bluetyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());  // BLUE BLUE_GREY SKY_BLUE BLUE_LIGHT
		bluetyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Font dataFont = workBook.createFont();  
		dataFont.setFontName("微软雅黑"); 
		dataFont.setFontHeightInPoints((short)9);
		
		CellStyle numberStyle = workBook.createCellStyle();
		numberStyle.setFont(dataFont);
//		numberStyle.setDataFormat(XSSFDataFormat  .getBuiltinFormat("0.00"));
		numberStyle.setAlignment(HorizontalAlignment.RIGHT);
		numberStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		numberStyle.setBorderTop(CellStyle.BORDER_THIN);   
		numberStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		numberStyle.setBorderRight(CellStyle.BORDER_THIN);  
//		numberStyle.setDataFormat(XSSFDataFormat);    // .getBuiltinFormat("0.00")
		XSSFDataFormat df = workBook.createDataFormat();
		numberStyle.setDataFormat(df.getFormat("#,##0.00"));
		
		CellStyle dataStyle = workBook.createCellStyle();
		dataStyle.setFont(dataFont);
//		numberStyle.setDataFormat(XSSFDataFormat  .getBuiltinFormat("0.00"));
		dataStyle.setAlignment(HorizontalAlignment.LEFT);
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);   
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);  
		
		for (int i = 0; i < cellTitle.length; i++) {
			XSSFCell cell = titleRow.createCell(i);
			cell.setCellValue(cellTitle[i]);
			cell.setCellStyle(yellowStyle);
			if(ArrayUtils.contains(blues,i) ) {
				cell.setCellStyle(bluetyle);
			}
		}
		
		// 插入需导出的数据
		for (int i = 1; i < dataList.size(); i++) {
			XSSFRow row = sheet.createRow(i);
//			row.setHeight(height);
//			row.setRowStyle(style);
			
			for (int j = 0; j < cellTitle.length; j++) {
//				row.createCell(j).setCellValue(dataList.get(i)[j]);
				XSSFCell cell = row.createCell(j);
				if(j>12 && j < 35 ) {
					cell = row.createCell(j, CellType.NUMERIC);
					cell.setCellStyle(numberStyle);
					cell.setCellValue(dataList.get(i)[j]);
				}else {
					cell.setCellValue(dataList.get(i)[j]);
					cell.setCellStyle(dataStyle);
				}
			}
		}
		
//		for (int i = 0; i < cellTitle.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
		
//		sheet.setColumnWidth(1, 7.88); 
		// 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 16);
		
		File file = new File(exportFileName);
		// 文件输出流
		try {
			FileOutputStream outStream = new FileOutputStream(file);
			workBook.write(outStream);
			workBook.close();
			outStream.flush();
			outStream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
//		System.out.println("导出成功！" /* + basePath */ + exportFileName);
		return exportFileName;
	}

	
	
	

}
