package com.poolm.service.impl.admin;

import java.util.List;
import java.util.Map;

import com.poolm.entity.Page;
import com.poolm.pojo.Business;

public interface IBusinessService {

	public Business getBusinessById(int businessId);

	public List<Map<String, Object>> getAllBusinessPage(Business business,
			Page page);

	public int enableOrDisableBusiness(Integer status, int businessId);

	public int updateBusiness(String name, String phone, String email,
			String position, Integer status, int businessId);
}
