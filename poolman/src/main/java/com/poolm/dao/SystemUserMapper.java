package com.poolm.dao;

import java.util.List;
import java.util.Map;

import com.poolm.pojo.SystemUser;

public interface SystemUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SystemUser record);

	int insertSelective(SystemUser record);

	SystemUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SystemUser record);

	int updateByPrimaryKey(SystemUser record);

	SystemUser selectByUserName(String userName);

	int getAllSystemUsersForCount(Map<String, Object> parm);

	List<Map<String, Object>> getAllSystemUsersPage(Map<String, Object> parm);

	int enableOrDisableSystemUser(Map<String, Object> parm);

	int getSystemUsersForCount();
}