package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.User;

/**
 * 用户dao接口
 * 
 * @author dilu
 * @version 1.0， 2017-9-4 22:24:51
 */
public interface UserMapper {

	User checkUser(String userName);

	User login(@Param("userName") String userName,@Param("password") String password);

	int updatePwd(@Param("userId")String userId, @Param("newPwd")String newPwd);
	
	List<User> getUserForRM();
	
	int addUser(User user);
	
	// Felix, 180105, Begin
	int updateUser(User user);
		
	User queryUserById(String userId);
	// Felix, 180105, End
	
	int update(User user);
	
	List<User> getUser(Map<String,Object> param);

}
