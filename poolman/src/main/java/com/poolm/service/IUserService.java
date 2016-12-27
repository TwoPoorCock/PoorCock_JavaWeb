package com.poolm.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.poolm.dao.UserMapper;
import com.poolm.pojo.User;
import com.poolm.service.impl.UserService;

@Service("userService")
public class IUserService implements UserService {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(IUserService.class);
	
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
