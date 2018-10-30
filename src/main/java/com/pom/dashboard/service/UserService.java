package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.ApiUser;
import com.pmo.dashboard.entity.User;

/**
 * 用户的Service接口
 * 
 * @author dilu
 * @version 1.0，2017-9-4 22:13:37
 */
public interface UserService {

	 User checkUser(String userName);

	User login(String userName, String password);

	int updatePwd(String userId, String newPwd);
	
	List<User> getUserForRM();
	
	boolean addUser(User user);
	
	// Felix, 180105, Begin
	boolean updateUser(User user);
		
	User queryUserById(String userId);
	// Felix, 180105, End
	
	boolean update(User user);
	
	List<ApiUser> loadAllApiUsers();
	
	ApiUser loginApiUser(String userName, String password, String systemId);
	
	List<User> getHR(String userid);
	
	List<User> getUser(Map<String,Object> map);
}
