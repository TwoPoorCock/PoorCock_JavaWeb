package com.poolm.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.poolm.dao.UserMapper;
import com.poolm.pojo.User;
import com.poolm.service.impl.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(UserService.class);
	
	@Resource
    private UserMapper userDao;
	
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		return this.userDao.selectByUserName(userName);
	}

}
