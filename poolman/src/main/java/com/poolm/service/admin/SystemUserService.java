package com.poolm.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poolm.dao.BusinessMapper;
import com.poolm.dao.DishMapper;
import com.poolm.dao.SystemUserMapper;
import com.poolm.dao.UserMapper;
import com.poolm.entity.Page;
import com.poolm.pojo.SystemUser;
import com.poolm.service.impl.admin.ISystemUserService;

@Service
public class SystemUserService implements ISystemUserService {

	@Resource
	private SystemUserMapper systemUserMapper;

	@Resource
	private BusinessMapper businessMapper;

	@Resource
	private DishMapper dishMapper;

	@Resource
	private UserMapper userMapper;

	public SystemUser getUserByName(String userName) {
		return systemUserMapper.selectByUserName(userName);
	}

	public List<Map<String, Object>> getAllSystemUsersPage(
			SystemUser systemUser, Page page) {
		Map<String, Object> parm = new HashMap<String, Object>();

		parm.put("systemUser", systemUser);
		int totalCount = systemUserMapper.getAllSystemUsersForCount(parm);
		Page.countPageSum(page, totalCount);
		int beginSize = (page.getPageNo() - 1) * page.getPageSize();
		parm.put("page", page);
		parm.put("beginSize", beginSize);
		return systemUserMapper.getAllSystemUsersPage(parm);
	}

	public int enableOrDisableBusiness(Integer status, int systemUserId) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("status", status.byteValue());
		parm.put("systemUserId", systemUserId);
		int result = systemUserMapper.enableOrDisableSystemUser(parm);
		return result;
	}

	public SystemUser getAdminById(int adminId) {
		return this.systemUserMapper.selectByPrimaryKey(adminId);
	}

	public int updateUser(String name, String userName, String passWord,
			String email, Integer status, int adminId) {
		Map<String, Object> params = new HashMap<String, Object>();
		SystemUser systemUser = new SystemUser();
		systemUser.setEmail(email);
		systemUser.setName(userName);
		systemUser.setPassword(passWord);
		systemUser.setUsername(userName);
		systemUser.setId(adminId);
		systemUser.setIsdel(status);
		return systemUserMapper.updateByPrimaryKeySelective(systemUser);
	}

	public int dishCount(Page page) {
		return dishMapper.getDishsForCount();
	}

	public int businessCount(Page page) {
		return businessMapper.getBusinessForCount();
	}

	public int userCount(Page page) {
		return userMapper.getUsersForCount();
	}

	public int systemCount(Page page) {
		return systemUserMapper.getSystemUsersForCount();
	}

}
