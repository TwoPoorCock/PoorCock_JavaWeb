package com.poolm.service.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poolm.dao.AppUserMapper;
import com.poolm.pojo.User;
import com.poolm.service.impl.app.IAppPersonInfoService;

@Service("appPersonInfoService")
public class AppPersonInfoService implements IAppPersonInfoService {

	@Resource
	private AppUserMapper appUserMapper;

	public int update_PersonalInfo(User user) {
		int result = appUserMapper.updateByPrimaryKey(user);
		return result;
	}

	public User getUserById(User user) {
		return appUserMapper.getUserById(user.getId());
	}

}
