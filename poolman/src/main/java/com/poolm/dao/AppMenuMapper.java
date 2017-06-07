package com.poolm.dao;

import java.util.List;
import java.util.Map;

import com.poolm.pojo.Dish;

public interface AppMenuMapper {

	public int getDish_counts();

	List<Dish> getDishList(Map<String, Object> param);
}
