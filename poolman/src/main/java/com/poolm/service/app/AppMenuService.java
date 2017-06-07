package com.poolm.service.app;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poolm.dao.AppMenuMapper;
import com.poolm.pojo.Dish;
import com.poolm.service.impl.app.IAppMenuService;

@Service("appMenuService")
public class AppMenuService implements IAppMenuService {

	@Resource
	private AppMenuMapper appMenuMapper;

	public int getDish_counts() {
		return appMenuMapper.getDish_counts();
	}

	public List<Dish> getDishList(Map<String, Object> param) {

		return appMenuMapper.getDishList(param);
	}

}
