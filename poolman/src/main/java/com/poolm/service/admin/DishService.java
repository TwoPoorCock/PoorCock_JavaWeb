package com.poolm.service.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.poolm.dao.DishMapper;
import com.poolm.entity.Page;
import com.poolm.pojo.Dish;
import com.poolm.service.impl.admin.IDishService;

@Service
public class DishService implements IDishService {

	private static Logger logger = Logger.getLogger(DishService.class);

	@Resource
	private DishMapper dishMapper;

	public List<Map<String, Object>> getAllDishPage(Dish dish, Page page) {
		Map<String, Object> parm = new HashMap<String, Object>();

		parm.put("dish", dish);
		int totalCount = dishMapper.getAllDishsForCount(parm);
		Page.countPageSum(page, totalCount);
		int beginSize = (page.getPageNo() - 1) * page.getPageSize();
		parm.put("page", page);
		parm.put("beginSize", beginSize);
		return dishMapper.getAllDishsPage(parm);
	}

	public int enableOrDisableDish(Integer status, int dishId) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("status", status);
		parm.put("dishId", dishId);
		int result = dishMapper.enableOrDisableDish(parm);
		return result;
	}

	public Dish getDishById(int dishId) {
		return dishMapper.selectByPrimaryKey(dishId);
	}

	public int updateDish(String dishName, String flag_r, String flag_h,
			Integer status, int dishId) {
		Dish dish = new Dish();
		dish.setDishname(dishName);
		dish.setFlagH(flag_h);
		dish.setFlagR(flag_r);
		dish.setId(dishId);
		dish.setType(status);
		return dishMapper.updateByPrimaryKeySelective(dish);
	}

	public int getAllByExcel(String file) {

		logger.info("进入dishService");
		int result = 0;
		try {
			
			Workbook rwb = Workbook.getWorkbook(new File(file));
			System.out.println("aaaaaaaaaa");
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = rs.getRows();// 得到所有的行

			System.out.println("clos=" + clos + " rows:" + rows);
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					// 第一个是列数，第二个是行数
					String name = rs.getCell(j++, i).getContents();
					String flag_r = rs.getCell(j++, i).getContents();
					String flag_h = rs.getCell(j++, i).getContents();
					String type = rs.getCell(j++, i).getContents();
					System.out.println("name:" + name + " flag_r:" + flag_r
							+ " flag_h:" + flag_h + "  type:" + type);

					Dish dish = new Dish();
					dish.setDishname(name);
					dish.setFlagH(flag_h);
					dish.setFlagR(flag_r);
					dish.setType(0);

					result = dishMapper.insertSelective(dish);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
