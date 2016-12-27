package com.poolm.service.impl;

import com.poolm.pojo.User;

public interface UserService {
	
	public User getUserById(int userId);
	
	public User getUserByName(String userName);
}
