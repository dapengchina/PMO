package com.pmo.dashboard.constant;

import java.util.HashMap;
import java.util.Map;

public class SysConstant {
	
	
	
	
	   /**
	    * 员工绩效目标设定状态
	    */
	   public static final String PERFOR_DRAFT_STATE = "0";//绩效目标草稿状态
	   public static final String PERFOR_SUBMIT_STATE = "1";//绩效目标提交状态
	   
	   public static Map<String,Object> getPerforStateMap(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   map.put(PERFOR_DRAFT_STATE, "草稿");
		   map.put(PERFOR_SUBMIT_STATE, "已提交");
		   
		   return map;
	   }
	   
	   /**
	    * 员工绩效目标设定类型
	    */
	   public static final String PRIORITY_WORK = "0";//重点工作
	   public static final String KEY_EVENTS = "1";//关键事件
	   
	   public static Map<String,Object> getPerforTypeMap(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   map.put(PRIORITY_WORK, "重点工作");
		   map.put(KEY_EVENTS, "关键事件");
		   
		   return map;
	   }
	   
	   //本地服务器地址
	   public static final String serverurl="http://localhost:8082/Pmo/";

}
