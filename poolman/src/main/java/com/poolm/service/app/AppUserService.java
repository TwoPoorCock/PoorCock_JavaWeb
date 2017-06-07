package com.poolm.service.app;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poolm.dao.AppUserMapper;
import com.poolm.pojo.User;
import com.poolm.service.impl.app.IAppUserService;

@Service("appUserService")
public class AppUserService implements IAppUserService {

	@Resource
	private AppUserMapper appUserMapper;

	public Map<String, Object> getUserByName(String userName) {

		return this.appUserMapper.selectByUserName(userName);
	}

	public Map<String, Object> appLogin(String userName, String passWord) {

		return this.appUserMapper.appLogin(userName, passWord);
	}

	public int addUser(String userName, String passWord) {
		int result = 0;
		User user = new User();
		user.setUsername(userName);
		user.setPassword(passWord);
		result = appUserMapper.addUser(user);
		return result;
	}

}
