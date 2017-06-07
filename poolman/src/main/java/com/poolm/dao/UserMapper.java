package com.poolm.dao;

import java.util.List;
import java.util.Map;

import com.poolm.pojo.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectByUserName(String userName);

	int getAllUsersForCount(Map<String, Object> parm);

	List<Map<String, Object>> getAllUsersPage(Map<String, Object> parm);

	int enableOrDisableUser(Map<String, Object> parm);

	int checkPhone(Map<String, Object> params);

	int getUsersForCount();

}