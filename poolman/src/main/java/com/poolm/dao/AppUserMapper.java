package com.poolm.dao;

import java.util.Map;

import com.poolm.pojo.User;

public interface AppUserMapper {

	Map<String, Object> appLogin(String userName, String passWord);
	  
	Map<String, Object> selectByUserName(String userName);
	  
	int addUser(User user);
}
