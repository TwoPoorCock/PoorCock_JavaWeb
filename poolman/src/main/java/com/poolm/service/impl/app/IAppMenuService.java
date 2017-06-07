package com.poolm.service.impl.app;

import java.util.List;
import java.util.Map;

import com.poolm.pojo.Dish;

public interface IAppMenuService {

	public int getDish_counts();

	public List<Dish> getDishList(Map<String, Object> param);
}
