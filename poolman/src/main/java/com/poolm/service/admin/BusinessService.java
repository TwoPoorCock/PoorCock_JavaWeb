package com.poolm.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poolm.dao.BusinessMapper;
import com.poolm.entity.Page;
import com.poolm.pojo.Business;
import com.poolm.service.impl.admin.IBusinessService;

@Service("businessService")
public class BusinessService implements IBusinessService {

	@Resource
	private BusinessMapper businessMapper;

	public Business getBusinessById(int businessId) {
		return this.businessMapper.selectByPrimaryKey(businessId);
	}

	public List<Map<String, Object>> getAllBusinessPage(Business business,
			Page page) {
		Map<String, Object> parm = new HashMap<String, Object>();

		parm.put("business", business);
		int totalCount = businessMapper.getAllBusinessForCount(parm);
		Page.countPageSum(page, totalCount);
		int beginSize = (page.getPageNo() - 1) * page.getPageSize();
		parm.put("page", page);
		parm.put("beginSize", beginSize);
		return businessMapper.getAllBusinessPage(parm);

	}

	public int enableOrDisableBusiness(Integer status, int businessId) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("status", status.byteValue());
		parm.put("businessId", businessId);
		int result = businessMapper.enableOrDisableBusiness(parm);
		return result;
	}

	public int updateBusiness(String name, String phone, String email,
			String position, Integer status, int businessId) {
		Business business = new Business();
		business.setName(name);
		business.setPosition(position);
		business.setPhone(phone);
		business.setEmail(email);
		business.setStatus(status);
		business.setId(businessId);
		return businessMapper.updateByPrimaryKeySelective(business);
	}

}
