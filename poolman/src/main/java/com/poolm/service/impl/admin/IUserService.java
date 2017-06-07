package com.poolm.service.impl.admin;

import java.util.List;
import java.util.Map;

import com.poolm.entity.Page;
import com.poolm.pojo.User;

public interface IUserService {

	public User getUserById(int userId);

	public User getUserByName(String userName);

	public List<Map<String, Object>> getAllUsersPage(User user, Page page);

	public int enableOrDisableUser(Integer status, int userId);

	public int updateUser(String userName, String phone, String gender,
			Integer status, int userId);

	public int checkPhone(String phone);
}
