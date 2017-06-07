package com.poolm.dao;

import java.util.List;
import java.util.Map;

import com.poolm.pojo.Dish;

public interface DishMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Dish record);

	int insertSelective(Dish record);

	Dish selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Dish record);

	int updateByPrimaryKey(Dish record);

	int getAllDishsForCount(Map<String, Object> parm);

	List<Map<String, Object>> getAllDishsPage(Map<String, Object> parm);

	int enableOrDisableDish(Map<String, Object> parm);

	int getDishsForCount();

}