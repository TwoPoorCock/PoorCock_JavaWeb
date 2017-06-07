package com.poolm.service.impl.admin;

import java.util.List;
import java.util.Map;

import com.poolm.entity.Page;
import com.poolm.pojo.SystemUser;

public interface ISystemUserService {

	public SystemUser getAdminById(int adminId);

	public SystemUser getUserByName(String userName);

	public List<Map<String, Object>> getAllSystemUsersPage(
			SystemUser systemUser, Page page);

	public int enableOrDisableBusiness(Integer status, int parseInt);
}
