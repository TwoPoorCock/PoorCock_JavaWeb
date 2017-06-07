package com.poolm.service.impl.app;

import java.util.Map;

public interface IAppUserService {
	
	Map<String, Object> getUserByName(String userName);
	  
	Map<String, Object> appLogin(String userName, String passWord);
	  
	int addUser(String userName, String passWord);

}
