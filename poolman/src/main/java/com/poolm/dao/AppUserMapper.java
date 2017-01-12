package com.poolm.dao;

import java.util.Map;

import com.poolm.pojo.User;

public interface AppUserMapper {

	public Map<String, Object> appLogin(String userName, String passWord);
	  
	public Map<String, Object> selectByUserName(String userName);
	  
	public int addUser(User user);
	
	public User getUserById(Integer userId);
	
	public int updateByPrimaryKey(User user);
	
}
