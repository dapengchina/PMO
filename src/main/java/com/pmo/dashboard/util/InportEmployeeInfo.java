package com.pmo.dashboard.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.HSBCDeptService;

import jxl.Sheet;
import jxl.Workbook;
@Component
public class InportEmployeeInfo
{
    
	
	
    public static void main(String[] args){
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/spring-mybatis.xml");
    	EmployeeService employeeService = (EmployeeService) context.getBean(EmployeeService.class);
    	HSBCDeptService hsbcDeptService = (HSBCDeptService) context.getBean(HSBCDeptService.class);
    	List<Employee> list = new ArrayList<Employee>();
        try
        {
            Workbook rwb = Workbook.getWorkbook(new File("C:/Users/gaonana/Desktop/b.xls"));
            Sheet rs = rwb.getSheet(0);
            int clos = rs.getColumns();
            int rows = rs.getRows();
            HSBCDept hsbcDept = new HSBCDept();
            
            Employee em = new Employee();
            
            int count = 0;
            for(int i=1;i<rows;i++){
            	String zuhe = "";
            	//System.out.println(rs.getCell(28, 1).getContents());
            	//先查询此人是否存在，如果存在则修改，如果不存在则添加
            	List<Employee> emlist = employeeService.selectByEhr(rs.getCell(28, i).getContents());
            	
            		//hsbc staff id
            		em.setHsbcStaffId(rs.getCell(1, i).getContents());
            		//staff name
            		em.setStaffName(rs.getCell(2, i).getContents());
            		//LN
            		em.setLn(rs.getCell(3, i).getContents());
            		//email
            		em.setEmail(rs.getCell(4, i).getContents());
            		//staff region
            		em.setStaffRegion(rs.getCell(5, i).getContents());
            		//staff location
            		em.setStaffLocation(rs.getCell(6, i).getContents());
            		//location type
            		em.setLocationType(rs.getCell(7, i).getContents());
            		//onshore
            		em.setOnshoreOrOffshore(rs.getCell(8, i).getContents());
            		
            		
            		//gbgf
            		hsbcDept.setName(rs.getCell(9, i).getContents());
            		List<HSBCDept> hsbcdeptlist1 = hsbcDeptService.queryById(hsbcDept);
            		if(hsbcdeptlist1!=null && hsbcdeptlist1.size()>0){
            			em.setGbGf(hsbcdeptlist1.get(0).getId());
            		}
            		//hsbcdept
            		hsbcDept.setName(rs.getCell(10, i).getContents());
            		List<HSBCDept> hsbcdeptlist2 = hsbcDeptService.queryById(hsbcDept);
            		if(hsbcdeptlist2!=null && hsbcdeptlist2.size()>0){
            			zuhe = zuhe + hsbcdeptlist2.get(0).getId()+",";
            		}
            		//hsbcsubdept
            		hsbcDept.setName(rs.getCell(11, i).getContents());
            		List<HSBCDept> hsbcdeptlist3 = hsbcDeptService.queryById(hsbcDept);
            		if(hsbcdeptlist3!=null && hsbcdeptlist3.size()>0){
            			zuhe = zuhe + hsbcdeptlist3.get(0).getId();
            		}
            		
            		em.setHsbcSubDept(zuhe);
            		//em.set
            		//hsbcmanager
            		em.setProjectManager(rs.getCell(12, i).getContents());
            		//project name
            		em.setProjectName(rs.getCell(13, i).getContents());
            		//sow#
            		em.setSow(rs.getCell(14, i).getContents());
            		//sow# expired date
            		if(rs.getCell(15, i).getContents()!=null && !"".equals(rs.getCell(15, i).getContents())){
            			em.setSowExpiredDate(rs.getCell(15, i).getContents());
            		}
            		
            		//staff category
            		em.setStaffCategory(rs.getCell(16, i).getContents());
            		//Engagement type
            		em.setEngagementType(rs.getCell(17, i).getContents());
            		//hsbc doj
            		if(rs.getCell(18, i).getContents()!=null && !"".equals(rs.getCell(18, i).getContents())){
            			em.setHsbcDOJ(rs.getCell(18, i).getContents());
            		}
            		
            		//graduation date
            		if(rs.getCell(19, i).getContents()!=null && !"".equals(rs.getCell(19, i).getContents())){
            			em.setGraduationDate(rs.getCell(19, i).getContents());
            		}
            		
            		//msa role
            		em.setRole(rs.getCell(20, i).getContents());
            		//skill
            		em.setSkill(rs.getCell(21, i).getContents());
            		//entry date	
            		if(rs.getCell(22, i).getContents()!=null && !"".equals(rs.getCell(22, i).getContents())){
            			em.setEntryDate(rs.getCell(22, i).getContents());
            		}
            		
            		//billing currency
            		em.setBillingCurrency(rs.getCell(23, i).getContents());
            		//bill rate
            		em.setBillRate(rs.getCell(24, i).getContents());
            		//resource status
            		em.setResourceStatus(rs.getCell(25, i).getContents());
            		//lwd
            		if(rs.getCell(26, i).getContents()!=null && !"".equals(rs.getCell(26, i).getContents())){
            			em.setTerminatedDate(rs.getCell(26, i).getContents());
            		}
            		
            		//terminateddate reason
            		em.setTerminationReason(rs.getCell(27, i).getContents());
            		//ehr
            		em.seteHr(rs.getCell(28, i).getContents());
            		//lob
            		em.setLob(rs.getCell(29, i).getContents());
            		
            		//rm
            		if(rs.getCell(30, i).getContents().equals("王蓓")){
            			em.setRmUserId("d7f19c09a5f1456fb9edf45bd3c2fbbb");
            		}
            		if(rs.getCell(30, i).getContents().equals("高赛")){
            			em.setRmUserId("0699ce9ceb7343a48c2acbe3e6e3aa97");
            		}
            		if(rs.getCell(30, i).getContents().equals("柳夏")){
            			em.setRmUserId("55988cfa8f07418c84f890cf895460d0");
            		}
            		
            		//csdept
            		if(rs.getCell(31, i).getContents().equals("财富管理交付部")){
            			em.setCsSubDept("8");
            		}
                    
            		
            		//it work year
            		em.setItindustryWorkYear(rs.getCell(32, i).getContents());
            		//chinasofti pro number
            		em.setChsoftiProNumber(rs.getCell(33, i).getContents());
            		//chinasofti pro startdate
            		if(rs.getCell(34, i).getContents()!=null && !"".equals(rs.getCell(34, i).getContents())){
            			em.setChsoftiProStartdate(rs.getCell(34, i).getContents());
            		}
            		
            		//chinasofti pro name
            		em.setChsoftiProName(rs.getCell(35, i).getContents());
            		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date curDate = new Date();
                    Timestamp createTime = null;
            		try {
            			createTime = new Timestamp(sdf.parse(sdf.format(curDate)).getTime());
            		} catch (ParseException e) {
            			//logger.error(e.getMessage());
            		}
            		em.setCreateTime(createTime);
            	
            	
            	    //如果此人存在则修改，如果不存在则添加
            		if(emlist!=null && emlist.size()>=1){
            			System.out.println("存在"+rs.getCell(28, i).getContents());
            			em.setEmployeeId(emlist.get(0).getEmployeeId());
            			//存在，修改
            			employeeService.updateEmployee(em);
            		}else{
            			System.out.println(count+"添加");
            			em.setEmployeeId(Utils.getUUID());
            			//不存在，添加
            			employeeService.addEmployee(em);
            			count++;
            		}
            }
            System.out.println("处理完成"+count);    
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

}
