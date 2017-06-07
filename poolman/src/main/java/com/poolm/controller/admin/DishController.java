package com.poolm.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poolm.controller.BaseController;
import com.poolm.entity.Page;
import com.poolm.pojo.Dish;
import com.poolm.service.admin.DishService;
import com.poolm.util.TableHelper;
import com.poolm.util.TextHelper;

@Controller
@RequestMapping("/dish")
public class DishController extends BaseController {

	private static Logger logger = Logger.getLogger(DishController.class);

	@Resource
	private DishService dishService;

	@RequestMapping("/toGetAllDishPage")
	public String toGetAllDishPage() {
		return "getAllDishPage";
	}

	@RequestMapping(value = "/getAllDishPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void getAllDishPage(@ModelAttribute Dish dish,
			@RequestParam("start") Integer pageNo,
			@RequestParam("length") Integer pageSize,
			@RequestParam("draw") Integer draw) throws IOException {
		int orderByIndex = Integer.parseInt(request
				.getParameter("order[0][column]"));
		String orderBy = request.getParameter("order[0][dir]");
		String orderName = request.getParameter("columns[" + orderByIndex
				+ "][name]");
		String query = new String(request.getParameter("search[value]")
				.getBytes("ISO-8859-1"), "UTF-8");

		logger.info("orderByIndex[" + orderByIndex + "] orderBy[" + orderBy
				+ "] orderName[" + orderName + "] query[" + query + "] pageNo["
				+ pageNo + "]");

		Page page = new Page();
		if (!TextHelper.isNullOrEmpty(orderBy)) {
			page.setOrderBy(orderBy);
		} else {
			page.setOrderBy("desc");
		}
		if (!TextHelper.isNullOrEmpty(orderName)) {
			page.setOrderName(TableHelper.convertToGBK(orderName));
		}
		if (!TextHelper.isNullOrEmpty(query)) {
			page.setQuery(query);
		}
		pageNo = (pageNo / pageSize) + 1;
		page.setPageNo((pageNo));
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = dishService.getAllDishPage(dish, page);
		if (list != null && list.size() > 0) {
			map.put("rows", list);
		} else {
			map.put("rows", new ArrayList<Map<String, Object>>());
		}
		int total = page.getTotalCount();
		map.put("draw", draw);
		map.put("recordsTotal", total);
		map.put("recordsFiltered", total);
		logger.info("draw[" + draw + "] recordsTotal[" + total
				+ "] recordsFiltered[" + total + "] row:" + map.get("rows"));
		outJSON(map);
	}

	@RequestMapping("/batchEnableOrDisableDish")
	public void batchEnableOrDisableBusiness(
			@RequestParam("dishIds") String dishIds,
			@RequestParam("status") Integer status) {
		logger.info("businessIds[" + dishIds + "] status[" + status + "]");
		Map<String, Object> map = new HashMap<String, Object>();
		String[] dishId = dishIds.split(",");
		boolean b = false;
		logger.info("length=" + dishId.length);
		for (int i = 0; i < dishId.length; i++) {
			int result = dishService.enableOrDisableDish(status,
					Integer.parseInt(dishId[i]));
			logger.info("result=" + result);
			if (result > 0) {
				b = true;
			} else {
				b = false;
				break;
			}
		}
		if (b == true) {
			if (status == 1) {
				map.put("msg", "启用成功");
			} else {
				map.put("msg", "停用成功");
			}
			map.put(STATUS, SUCCESS);
		} else {
			if (status == 1) {
				map.put("msg", "启用失败");
			} else {
				map.put("msg", "停用失败");
			}
			map.put(STATUS, FAIL);
		}
		logger.info("msg[" + map.get("msg") + "] status[" + map.get("status")
				+ "] ");
		outJSON(map);
	}

	@RequestMapping("/toEditDishPage")
	public String toEditBusinessPage(Model model,
			@RequestParam(value = "id") int dishId) {
		model.addAttribute("dishMap", dishService.getDishById(dishId));
		return "editDishPage";
	}

	@RequestMapping(value = "updateDish", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void updateBusiness(@RequestParam("name") String dishName,
			@RequestParam("flag_r") String flag_r,
			@RequestParam("flag_h") String flag_h,
			@RequestParam("status") Integer status,
			@RequestParam("dishId") int dishId) {
		logger.info("dishName[" + dishName + "] flag_r[" + flag_r + "] flag_h["
				+ flag_h + "] status[" + status + "] dishId[" + dishId + "] ");
		Map<String, Object> map = new HashMap<String, Object>();
		int result = dishService.updateDish(dishName, flag_r, flag_h, status,
				dishId);
		if (result > 0) {
			map.put(STATUS, SUCCESS);
			map.put("msg", "修改成功");
		} else {
			map.put(STATUS, FAIL);
			map.put("msg", "修改失败");
		}
		logger.info("msg[" + map.get("msg") + "] status[" + map.get("status")
				+ "] ");
		outJSON(map);
	}

	@RequestMapping(value = "/fileUploadF", method = RequestMethod.POST)
	public void fileUploadF() {
		logger.info("进入fileUploadF");
		Map<String, Object> map = new HashMap<String, Object>();
		int result = dishService.getAllByExcel("G://book.xls");

		if (result > 0) {
			map.put(STATUS, SUCCESS);
			map.put("msg", "插入成功");
		} else {
			map.put(STATUS, FAIL);
			map.put("msg", "插入失败");
		}
		outJSON(map);
	}
}
