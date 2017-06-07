package com.poolm.dao;

import java.util.List;
import java.util.Map;

import com.poolm.pojo.Business;

public interface BusinessMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Business record);

	int insertSelective(Business record);

	Business selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Business record);

	int updateByPrimaryKey(Business record);

	int getAllBusinessForCount(Map<String, Object> parm);

	List<Map<String, Object>> getAllBusinessPage(Map<String, Object> parm);

	int enableOrDisableBusiness(Map<String, Object> parm);

	int getBusinessForCount();

}