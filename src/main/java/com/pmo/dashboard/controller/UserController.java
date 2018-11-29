package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.UserService;


/**
 * 用户的Controller
 * 
 * @author dilu
 * @version 1.0，2017-9-4 21:50:23
 */
@Controller
@RequestMapping(value="user")
public class UserController {

	@Resource
	private UserService userService;
//	private String user;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 验证用户名存在的方法法
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public String checkUser(String userName){
		boolean result = true;
		User user = userService.checkUser(userName);
		if(user==null){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
	}
	
	/**
	 * 验证用户登录的方法
	 * 
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(String userName,String password,HttpSession session,Model model){
		User user = userService.login(userName,password);
		if(user!=null){
			try{
				if(!("0".equals(user.getLoginStatus()))){
					return "2";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//登录成功 ，将user放入session，跳转到首页
			session.setAttribute("loginUser", user);
			return "0";
		}else{
			//登录失败，返回登录页面，设置错误信息
			//model.addAttribute("error", "用户名或密码有误  请重新输入");
			return "1";
		}
		
	}
	
	/**
	 * 跳转到首页的方法
	 * 
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome(){
		
		return "welcome";
	}
	
	
	/**
	 * 修改密码的方法
	 * 
	 * @param newPassword
	 * @return
	 */
	@RequestMapping("/updatePassword")
	public String updatePassword(String newPwd,HttpSession session){
		//获取用户id
		User exitUser = (User) session.getAttribute("loginUser");
		String userId = exitUser.getUserId();
		//调用service层
		int num = userService.updatePwd(userId,newPwd);
		String flg = "";
		if(num>0){
			flg = "1";
		}
		return flg;
		
	}
	
	/**
	 * 获得userType为3 即是 RM的用户信息（全部）
	 * @author gkf
	 * @date 2017-12-5
	 */
	@RequestMapping("/getUserForRM")
	@ResponseBody
	public List<User> getUserForRM() {
		List<User> userList  = userService.getUserForRM();
		
		return userList;
		
	}
	
	// Felix, 180103, Begin
		@RequestMapping("/userInfo")
	    public String employeeInfo(final HttpServletRequest request,
	            final HttpServletResponse response){
	        return "user/userInfo";
	    }
		
		@RequestMapping("/updateUserInfo")
	    public String updateEmployeeInfo(final HttpServletRequest request,
	            final HttpServletResponse response)
	    {
	        String userId = request.getParameter("userId");
	        request.setAttribute("userId", userId);
	        return "user/updateUserInfo";
	    }
		
		@RequestMapping("/updateUser")
	    @ResponseBody
	    public boolean updateUser(final HttpServletRequest request,
	            final HttpServletResponse response)
	    {
			String userId = request.getParameter("userId");
	        String eHr = request.getParameter("eHr");
	        String userName = request.getParameter("userName");
	        String userType = request.getParameter("userType");
	        String email = request.getParameter("email");
	        String bu = request.getParameter("bu");
	        String csDeptId = request.getParameter("du");
	        
	        User user = new User(userId, eHr, userName, userType, email, bu, csDeptId);
	        
	     
	        
	        boolean resultFlag = userService.updateUser(user);
	        
	        return resultFlag;
	    }
		
		@RequestMapping("/queryUserById")
	    @ResponseBody
	    public Object queryUserById(final HttpServletRequest request,
	            final HttpServletResponse response)
	    {
	        String userId = request.getParameter("userId");
	        
	        User user = userService.queryUserById(userId);
	        
	        return user;
	    }
		// Felix, 180103, End
		/**
		 * 用户禁用/启用
		 * @param request
		 * @throws JsonProcessingException 
		 */
		@RequestMapping("/userRights/{userid}/{state}")
	    @ResponseBody
		public String userRights(@PathVariable("userid") String userid,@PathVariable("state") String state,final HttpServletRequest request) throws JsonProcessingException{
			Map<String,Object> map = new HashMap<String,Object>();
			try{
				User u = new User();
				u.setUserId(userid);
				u.setLoginStatus(state);
				userService.update(u);
				
				map.put("msg", "操作成功");
				map.put("code", "1");
			}catch(Exception e){
				map.put("msg", "操作失败");
				map.put("code", "0");
			}
			return objectMapper.writeValueAsString(map);
		}
}


