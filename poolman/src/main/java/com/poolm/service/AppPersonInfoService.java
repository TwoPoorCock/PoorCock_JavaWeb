package com.poolm.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poolm.dao.AppUserMapper;
import com.poolm.pojo.User;
import com.poolm.service.impl.IAppPersonInfoService;

@Service("appPersonInfoService")
public class AppPersonInfoService implements IAppPersonInfoService {
	
	@Resource
	private AppUserMapper appUserMapper;
	
	@Override
	public int update_PersonalInfo(User user) {
		int result = appUserMapper.updateByPrimaryKey(user);
		return result;
	}

	@Override
	public User getUserById(User user) {
		return appUserMapper.getUserById(user.getId());
	}

}
