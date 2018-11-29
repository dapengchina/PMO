package com.pmo.dashboard.constant;

import java.util.HashMap;
import java.util.Map;

public class SysConstant {
	
	
	
	
	   /**
	    * 员工绩效目标设定状态
	    */
	   public static final String PERFOR_DRAFT_STATE = "0";//绩效目标草稿状态
	   public static final String PERFOR_SUBMIT_STATE = "1";//绩效目标提交状态
	   public static final String APPROVAL_PASS = "2";//绩效目标审批通过
	   public static final String APPROVAL_NOPASS = "3";//绩效目标审批未通过
	   
	   public static Map<String,Object> getPerforStateMap(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   map.put(PERFOR_DRAFT_STATE, "草稿");
		   map.put(PERFOR_SUBMIT_STATE, "已提交");
		   map.put(APPROVAL_PASS, "审批通过");
		   map.put(APPROVAL_NOPASS, "审批未通过");
		   
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
	   
	   /**
	    * 员工考评进度(流程)
	    */
	   public static final String PENDING_APPROVAL="1";//员工绩效目标待RM审批
	   public static final String PERFORMANCE_PASS = "2";//绩效目标审批通过
	   public static final String PERFORMANCE_NOPASS = "3";//绩效目标审批未通过
	   public static Map<String,Object> getEvaProgressStateMap(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   
		   map.put(PENDING_APPROVAL, "待审批");
		   map.put(PERFORMANCE_PASS, "审批通过");
		   map.put(PERFORMANCE_NOPASS, "审批未通过");
		   
		   return map;
	   }
	   
	   /**
	    * 员工考评流程类型
	    */
	   public static final String PROCESS_TYPE1="1";//绩效目标审批
	   public static Map<String,Object> getPerforProcessTypeMap(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   
		   map.put(PROCESS_TYPE1, "绩效目标审批");
		   
		   return map;
	   }
	   
	   /**
	    * 绩效结果表状态
	    */
	   public static final String PRESULT_PENDING_RM = "0";//待RM审批
	   public static final String RPESULT_REJECT_RM = "1";//RM审批不通过
	   public static final String PRESULT_PENDING_DU = "2";//待交付部审批
	   public static final String PRESULT_REJECT_DU = "3";//交付部经理审批不通过
	   public static final String PRESULT_PENDING_BU = "4";//待事业部审批
	   public static final String PRESULT_REJECT_BU = "5";//事业部经理审批不通过
	   public static final String PRESULT_PENDING_LOB = "6";//待lob总审批
	   public static final String PRESULT_REJECT_LOB = "7";//LOB总审批不通过
	   public static final String PRESULT_PASS_LOB = "8";//LOB总审批通过
	   
	   public static Map<String,Object> getPresultStateMap(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   
		   map.put(PRESULT_PENDING_RM, "待RM审批");
		   map.put(RPESULT_REJECT_RM, "RM审批不通过");
		   map.put(PRESULT_PENDING_DU, "待交付部审批");
		   map.put(PRESULT_REJECT_DU, "交付部经理审批不通过");
		   map.put(PRESULT_PENDING_BU, "待事业部审批");
		   map.put(PRESULT_REJECT_BU, "事业部经理审批不通过");
		   map.put(PRESULT_PENDING_LOB, "待lob总审批");
		   map.put(PRESULT_REJECT_LOB, "LOB总审批不通过");
		   map.put(PRESULT_PASS_LOB, "LOB总审批通过");
		   
		   return map;
	   }
	   
	   /**
	    *  是否为team leader
	    */
	   public static final String TLdefault = null;
	   public static final String TLdefault1 = "";
	   public static final String IsTeamLeader = "1";
	   public static final String NotTeamLeader = "2";
	   
	   public static Map<String,Object> getTeamLeader(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   
		   map.put(TLdefault, "");
		   map.put(TLdefault1, "");
		   map.put(IsTeamLeader, "yes");
		   map.put(NotTeamLeader, "no");;
		   
		   return map;
	   }
	   
	   /**
	    *  team leader Type
	    */
	   public static final String noTeamLeaderType = null;
	   public static final String TeamLeaderType = "";
	   public static final String TeamLeaderType1 = "1";
	   public static final String TeamLeaderType2 = "2";
	   public static final String TeamLeaderType3 = "3";
	   public static final String TeamLeaderType4 = "4";
	   
	   public static Map<String,Object> getTeamLeaderType(){
		   Map<String,Object> map = new HashMap<String,Object>();
		   
		   map.put(TeamLeaderType, "");
		   map.put(noTeamLeaderType, "");
		   map.put(TeamLeaderType1, "Qualified and is Team Leader");
		   map.put(TeamLeaderType2, "Qualified but not Team Leader");
		   map.put(TeamLeaderType3, "He/She is a culture");
		   map.put(TeamLeaderType4, "Not a culture");
		   
		   return map;
	   }
	   
	   public static final String ISFINAL = "True";
	   public static final String ISNOTFINAL = "False";
	   //本地服务器地址
	   public static final String serverurl="http://localhost:8082/Pmo/";

}
