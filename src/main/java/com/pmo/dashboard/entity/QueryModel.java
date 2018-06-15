package com.pmo.dashboard.entity;

import java.util.List;

public class QueryModel {
	
	
	   @SuppressWarnings("rawtypes")
	   private List csdeptids;
	   
	   private String status;
	   
	   private String rr;
	   
	   private String jobcode;
	   
	   private String type;
	   
	   private String skill;
	   
	   private String employeeid;
	   
	   
	   
	   
	   
	 

	   public String getEmployeeid() {
		 return employeeid;
	   }

	   public void setEmployeeid(String employeeid) {
		  this.employeeid = employeeid;
	   }

	   public String getSkill() {
		   return skill;
	   }

	   public void setSkill(String skill) {
		  this.skill = skill;
	   }

	   public String getType() {
		  return type;
	   }

	   public void setType(String type) {
		  this.type = type;
	   }

	   public String getRr() {
		  return rr;
	   }

	   public void setRr(String rr) {
		  this.rr = rr;
	   }

	   public String getJobcode() {
		  return jobcode;
	   }

	   public void setJobcode(String jobcode) {
		  this.jobcode = jobcode;
	   }

	   @SuppressWarnings("rawtypes")
	   public List getCsdeptids() {
		   return csdeptids;
	   }

	   public void setCsdeptids(@SuppressWarnings("rawtypes") List csdeptids) {
		   this.csdeptids = csdeptids;
	   }

	   public String getStatus() {
		   return status;
	   }

	   public void setStatus(String status) {
		   this.status = status;
	   }
	 

}
