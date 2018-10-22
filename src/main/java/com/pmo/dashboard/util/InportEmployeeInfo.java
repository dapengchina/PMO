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
            //String temp = rs.getCell(30, 1).getContents();
            //System.out.println(temp);
            Employee em = new Employee();
            
            int count = 0;
            for(int i=1;i<rows;i++){
            	String zuhe = "";
            	//System.out.println(rs.getCell(30, 1).getContents());
            	//先查询此人是否存在，如果存在则修改，如果不存在则添加
            	List<Employee> emlist = employeeService.selectByEhr(rs.getCell(30, i).getContents());
            	
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
            		if(rs.getCell(20, i).getContents()!=null && !"".equals(rs.getCell(20, i).getContents())){
            			em.setGraduationDate(rs.getCell(20, i).getContents());
            		}
            		
            		//msa role
            		em.setRole(rs.getCell(22, i).getContents());
            		//skill
            		em.setSkill(rs.getCell(23, i).getContents());
            		//entry date	
            		if(rs.getCell(24, i).getContents()!=null && !"".equals(rs.getCell(24, i).getContents())){
            			em.setEntryDate(rs.getCell(24, i).getContents());
            		}
            		
            		//billing currency
            		em.setBillingCurrency(rs.getCell(25, i).getContents());
            		//bill rate
            		em.setBillRate(rs.getCell(26, i).getContents());
            		//resource status
            		em.setResourceStatus(rs.getCell(27, i).getContents());
            		//lwd
            		if(rs.getCell(28, i).getContents()!=null && !"".equals(rs.getCell(28, i).getContents())){
            			em.setTerminatedDate(rs.getCell(28, i).getContents());
            		}
            		
            		//terminateddate reason
            		em.setTerminationReason(rs.getCell(29, i).getContents());
            		//ehr
            		em.seteHr(rs.getCell(30, i).getContents());
            		//lob
            		em.setLob(rs.getCell(31, i).getContents());
            		
            		//rm
            		if(rs.getCell(32, i).getContents().equals("张晨")){
            			em.setRmUserId("d87ca6a3b5534553b7967bc34b8eb59c");
            		}
            		
            		//csdept
            		if(rs.getCell(33, i).getContents().equals("财富管理交付部")){
            			em.setCsSubDept("8");
            		}
                    
            		
            		//it work year
            		em.setItindustryWorkYear(rs.getCell(34, i).getContents());
            		//chinasofti pro number
            		em.setChsoftiProNumber(rs.getCell(35, i).getContents());
            		//chinasofti pro startdate
            		if(rs.getCell(36, i).getContents()!=null && !"".equals(rs.getCell(36, i).getContents())){
            			em.setChsoftiProStartdate(rs.getCell(36, i).getContents());
            		}
            		
            		//chinasofti pro name
            		em.setChsoftiProName(rs.getCell(37, i).getContents());
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
            		if(emlist!=null && emlist.size()==1){
            			System.out.println(rs.getCell(30, i).getContents());
            			em.setEmployeeId(emlist.get(0).getEmployeeId());
            			//存在，修改
            			employeeService.updateEmployee(em);
            		}else{
            			em.setEmployeeId(Utils.getUUID());
            			//不存在，添加
            			employeeService.addEmployee(em);
            			count++;
            		}
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
                //处理GBGF
            	/**String ehr = rs.getCell(3, i).getContents();
            	String gbgf = rs.getCell(0, i).getContents();
            	System.out.println(ehr);
            	//根据Name去获取ID
            	hsbcDept.setName(gbgf);
            	List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);


            	if(hsbcDeptList!=null && hsbcDeptList.size()>0){
            		em.seteHr(ehr);
                	em.setGbGf(hsbcDeptList.get(0).getId());
                	employeeService.importEmployeeProject(em);
            	}*/
            	//处理HSBCDept和HSBCSubDept
            	/**String ehr = rs.getCell(3, i).getContents();
            	String hsbcDeptt = rs.getCell(1, i).getContents();
            	String hsbcSubDeptt = rs.getCell(2, i).getContents();
            	
            	System.out.println(ehr);
            	//根据Name去获取ID
            	HSBCDept hsbcDept2 = new HSBCDept();
            	HSBCDept hsbcDept3 = new HSBCDept();
            	hsbcDept2.setName(hsbcDeptt);
            	hsbcDept3.setName(hsbcSubDeptt);
            	List<HSBCDept> hsbcDeptList1 = hsbcDeptService.queryById(hsbcDept2);
            	List<HSBCDept> hsbcDeptList2 = hsbcDeptService.queryById(hsbcDept3);

            	String zuhe="";
            	if(hsbcDeptList1!=null && hsbcDeptList1.size()>0){
            		zuhe = zuhe+hsbcDeptList1.get(0).getId()+",";
            		if(hsbcDeptList2!=null && hsbcDeptList2.size()>0){
            			zuhe = zuhe + hsbcDeptList2.get(0).getId();
            		}
            	}
            	em.seteHr(ehr);
                em.setHsbcSubDept(zuhe);
                employeeService.importEmployeeProject(em);*/
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
