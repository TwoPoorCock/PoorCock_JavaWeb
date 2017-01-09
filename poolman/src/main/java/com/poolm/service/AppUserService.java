package com.poolm.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poolm.dao.AppUserMapper;
import com.poolm.pojo.User;
import com.poolm.service.impl.IAppUserService;

@Service("appUserService")
public class AppUserService implements IAppUserService {

	@Resource
	private AppUserMapper appUserMapper;

	@Override
	public Map<String, Object> getUserByName(String userName) {

		return this.appUserMapper.selectByUserName(userName);
	}

	public Map<String, Object> appLogin(String userName, String passWord){
	
		return this.appUserMapper.appLogin(userName, passWord);
	}
	  
	public int addUser(String userName, String passWord){
		int result = 0;
		User user = new User();
		user.setUsername(userName);
		user.setPassWord(passWord);
		result = appUserMapper.addUser(user);
	    return result;
	}

}
