package com.poolm.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.poolm.dao.UserMapper;
import com.poolm.entity.Page;
import com.poolm.pojo.User;
import com.poolm.service.impl.admin.IUserService;

@Service("userService")
public class UserService implements IUserService {

	private static Logger logger = Logger.getLogger(UserService.class);

	@Resource
	private UserMapper userMapper;

	public User getUserById(int userId) {
		return this.userMapper.selectByPrimaryKey(userId);
	}

	public User getUserByName(String userName) {
		return this.userMapper.selectByUserName(userName);
	}

	public List<Map<String, Object>> getAllUsersPage(User user, Page page) {
		Map<String, Object> parm = new HashMap<String, Object>();

		parm.put("user", user);
		int totalCount = userMapper.getAllUsersForCount(parm);
		Page.countPageSum(page, totalCount);
		int beginSize = (page.getPageNo() - 1) * page.getPageSize();
		parm.put("page", page);
		parm.put("beginSize", beginSize);
		return userMapper.getAllUsersPage(parm);
	}

	public int updateUser(String userName, String phone, String gender,
			Integer status, int userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		User user = new User();
		user.setUsername(userName);
		user.setGender(gender);
		user.setPhone(phone);
		user.setStatus(status);
		user.setId(userId);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	public int enableOrDisableUser(Integer status, int userId) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("status", status.byteValue());
		parm.put("userId", userId);
		int result = userMapper.enableOrDisableUser(parm);
		return result;
	}

	public int checkPhone(String phone) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phone", phone);
		return userMapper.checkPhone(params);
	}

}
