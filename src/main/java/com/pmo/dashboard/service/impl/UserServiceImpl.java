package com.pmo.dashboard.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.UserMapper;
import com.pmo.dashboard.entity.ApiUser;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.UserService;

/**
 * 用户service接口实现类
 * 
 * @author dilu
 * @version1.0 2017-9-4 22:22:28
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	@Override
	public User checkUser(String userName) {
		// TODO Auto-generated method stub
		return userMapper.checkUser(userName);
	}
	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		return userMapper.login(userName,password);
	}
	@Override
	public int updatePwd(String userId, String newPwd) {
		// TODO Auto-generated method stub
		return userMapper.updatePwd(userId,newPwd);
	}
	@Override
	public List<User> getUserForRM() {
		
		return userMapper.getUserForRM();
	}
	@Override
	public boolean addUser(User user) {
		if(userMapper.addUser(user)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		if(userMapper.updateUser(user)>0){
            return true;
        }
        return false;
	}
	@Override
	public User queryUserById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.queryUserById(userId);
	}
	@Override
	public boolean update(User user) {
		if(userMapper.update(user)>0){
            return true;
        }
        return false;
	}
	
	@Override
	public List<ApiUser> loadAllApiUsers() {
		//xml file path 
		
		String path = this.getClass().getClassLoader().getResource("").getPath()+"conf/syncEmployInfo.xml";
		SAXReader reader = new SAXReader();
		reader.setEncoding("utf-8");
		Document document ;
		List<ApiUser> apiUsers= new ArrayList<>();

		try {
			document = reader.read(new File(path));
			Element root = document.getRootElement();//得到xml跟标签
			List<Element> list = root.elements("user");  
			
			for (Element element : list) {
				ApiUser  apiUser = new ApiUser();
				String userName = element.element("userName").getStringValue();
				String password = element.element("password").getStringValue();
				String systemId = element.element("systemId").getStringValue();

				apiUser.setUserName(userName);
				apiUser.setPassword(password);
				apiUser.setSystemId(systemId);
				apiUsers.add(apiUser);
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return apiUsers;
	}
	
	@Override
	public ApiUser loginApiUser(String userName, String password, String systemId) {
		
		List<ApiUser> apiUsers = new ArrayList<>();
		
		//得到所有的api用户信息
		apiUsers	= loadAllApiUsers();
		
		//判断登录信息
		if(apiUsers.size()>0){
			for (ApiUser apiUser : apiUsers) {
				if(apiUser.getUserName().equals(userName) && apiUser.getPassword().equals(password) && apiUser.getSystemId().equals(systemId)){
					return apiUser;
				}
			}
		}
		
		return null;
	}
	@Override
	public List<User> getHR(String userid) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("type1", "6");
		param.put("type2", "7");
		if(userid != null && !"".equals(userid)){
			param.put("userid", userid);
		}
		List<User> list = userMapper.getUser(param);
		return list;
	}
	@Override
	public List<User> getUser(Map<String, Object> map) {
		List<User> list = userMapper.getUser(map);
		return list;
	}

}
