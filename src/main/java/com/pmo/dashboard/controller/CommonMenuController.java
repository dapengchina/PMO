package com.pmo.dashboard.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.constant.SysConstant;
import com.pmo.dashboard.entity.CommonMenu;
import com.pmo.dashboard.entity.CommonUserMenu;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.vo.CommonMenuVo;
import com.pom.dashboard.service.CommonMenuService;
import com.pom.dashboard.service.CommonUserMenuService;

@Controller
@RequestMapping(value="/commonmenu")
public class CommonMenuController {
	
	
	
	   @Resource
	   private CommonMenuService commonMenuService;
	   
	   @Resource
	   private CommonUserMenuService commonUserMenuService;
	   
	   private ObjectMapper objectMapper = new ObjectMapper();  
	   
	   @RequestMapping("/getMenu")
	   @ResponseBody
	   public String getMenu(final HttpServletRequest request) throws JsonProcessingException{
		   HttpSession session = request.getSession();
		   User user = (User) session.getAttribute("loginUser");
		   CommonMenu cm = new CommonMenu();
		   List<CommonMenuVo> topmenulist = new ArrayList<CommonMenuVo>();
		   
		   List<CommonMenu> commonMenuList = commonMenuService.getTopMenu(cm);
		   for(int i=0;i<commonMenuList.size();i++){
			   //权限校验
			   if(permissionProcessing(user.getUserType(),commonMenuList.get(i).getId())){
				   CommonMenuVo cv = new CommonMenuVo();
				   cv.setId(commonMenuList.get(i).getId());
				   cv.setText(commonMenuList.get(i).getMenuName());
				   cv.setIcon("glyphicon glyphicon-folder-close");
				   cv.setSelectedIcon("glyphicon glyphicon-folder-open");
				   cv.setColor("#2FA4E7");
				   cv.setNodes(handleChildMenu(commonMenuList.get(i).getMenuid(),user.getUserType()));
				   
				   topmenulist.add(cv);
			   }
		   }
		   return objectMapper.writeValueAsString(topmenulist);
	   }
	   
	   
	   private List<CommonMenuVo> handleChildMenu(String parentid,String usertype){
		   CommonMenu cm = new CommonMenu();
		   List<CommonMenu> childList;
		   List<CommonMenuVo> returnChildList = new ArrayList<CommonMenuVo>();;
		   //获取子节点
		   cm.setParentid(parentid);
		   childList = commonMenuService.getChildMenu(cm);
		   for(int i=0;i<childList.size();i++){
			   CommonMenuVo cv = new CommonMenuVo();
			   if(handleChildMenu(childList.get(i).getMenuid(),usertype)!=null && handleChildMenu(childList.get(i).getMenuid(),usertype).size()>0){
				   //权限校验
				   if(permissionProcessing(usertype,childList.get(i).getId())){
					   cv.setId(childList.get(i).getId());
					   cv.setText(childList.get(i).getMenuName());
					   cv.setIcon("glyphicon glyphicon-folder-close");
					   cv.setSelectedIcon("glyphicon glyphicon-folder-open");
					   cv.setColor("#2FA4E7");
					   cv.setNodes(handleChildMenu(childList.get(i).getMenuid(),usertype));
				   }
			   }else{
				   //权限校验
				   if(permissionProcessing(usertype,childList.get(i).getId())){
					   cv.setId(childList.get(i).getId());
					   cv.setText(childList.get(i).getMenuName());
					   cv.setIcon("glyphicon glyphicon-stop");
					   cv.setSelectedIcon("glyphicon glyphicon-stop");
					   cv.setColor("#2FA4E7");
					   cv.setHref(SysConstant.serverurl+"service/performance/"+childList.get(i).getMenuUrl());
					   //cv.setNodes(handleChildMenu(childList.get(i).getMenuid()));
				   }
			   }
			   returnChildList.add(cv);
		   }
		   
		   return returnChildList;
	   }

	   
	   /**
	    * 权限处理
	    */
	   private boolean permissionProcessing(String userType,String menuid){
		   Set<String> set = new HashSet<String>();
		   CommonUserMenu cm = new CommonUserMenu();
		   cm.setUsertype(userType);
		   List<CommonUserMenu> userMenuList = commonUserMenuService.getUserMenu(cm);
		   if(userMenuList!=null && userMenuList.size()>0){
			   for(int i=0;i<userMenuList.size();i++){
				   set.add(userMenuList.get(i).getMenuid());
			   }
		   }
		   return set.contains(menuid);
	   }
}
