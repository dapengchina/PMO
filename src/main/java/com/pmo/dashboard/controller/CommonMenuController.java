package com.pmo.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.CommonMenu;
import com.pmo.dashboard.entity.vo.CommonMenuVo;
import com.pom.dashboard.service.CommonMenuService;

@Controller
@RequestMapping(value="/commonmenu")
public class CommonMenuController {
	
	
	
	   @Resource
	   private CommonMenuService commonMenuService;
	   
	   private ObjectMapper objectMapper = new ObjectMapper();  
	   
	   @RequestMapping("/getMenu")
	   @ResponseBody
	   public String getMenu() throws JsonProcessingException{
		   CommonMenu cm = new CommonMenu();
		   List<CommonMenuVo> topmenulist = new ArrayList<CommonMenuVo>();
		   
		   List<CommonMenu> commonMenuList = commonMenuService.getTopMenu(cm);
		   for(int i=0;i<commonMenuList.size();i++){
			   CommonMenuVo cv = new CommonMenuVo();
			   cv.setId(commonMenuList.get(i).getId());
			   cv.setText(commonMenuList.get(i).getMenuName());
			   cv.setIcon("glyphicon glyphicon-folder-close");
			   cv.setSelectedIcon("glyphicon glyphicon-folder-open");
			   cv.setColor("#2FA4E7");
			   cv.setNodes(handleChildMenu(commonMenuList.get(i).getMenuid()));
			   
			   topmenulist.add(cv);
			   
		   }
		   return objectMapper.writeValueAsString(topmenulist);
	   }
	   
	   
	   private List<CommonMenuVo> handleChildMenu(String parentid){
		   CommonMenu cm = new CommonMenu();
		   List<CommonMenu> childList;
		   List<CommonMenuVo> returnChildList = new ArrayList<CommonMenuVo>();;
		   //获取子节点
		   cm.setParentid(parentid);
		   childList = commonMenuService.getChildMenu(cm);
		   for(int i=0;i<childList.size();i++){
			   CommonMenuVo cv = new CommonMenuVo();
			   if(handleChildMenu(childList.get(i).getMenuid())!=null && handleChildMenu(childList.get(i).getMenuid()).size()>0){
				   cv.setId(childList.get(i).getId());
				   cv.setText(childList.get(i).getMenuName());
				   cv.setIcon("glyphicon glyphicon-folder-close");
				   cv.setSelectedIcon("glyphicon glyphicon-folder-open");
				   cv.setColor("#2FA4E7");
				   cv.setNodes(handleChildMenu(childList.get(i).getMenuid()));
			   }else{
				   cv.setId(childList.get(i).getId());
				   cv.setText(childList.get(i).getMenuName());
				   cv.setIcon("glyphicon glyphicon-stop");
				   cv.setSelectedIcon("glyphicon glyphicon-stop");
				   cv.setColor("#2FA4E7");
				   cv.setHref(childList.get(i).getMenuUrl());
				   //cv.setNodes(handleChildMenu(childList.get(i).getMenuid()));
			   }
			   returnChildList.add(cv);
		   }
		   
		   return returnChildList;
	   }

}
