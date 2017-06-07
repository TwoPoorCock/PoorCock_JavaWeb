package com.poolm.service.impl.app;

import com.poolm.pojo.User;

public interface IAppPersonInfoService {

	public int update_PersonalInfo(User user);

	public User getUserById(User user);
}
