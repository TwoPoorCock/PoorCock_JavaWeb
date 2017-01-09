package com.poolm.service.impl;

import com.poolm.pojo.User;

public interface IUserService {
	
	public User getUserById(int userId);
	
	public User getUserByName(String userName);
}
