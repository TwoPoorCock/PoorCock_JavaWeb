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
import com.poolm.pojo.User;
import com.poolm.service.admin.UserService;
import com.poolm.util.TableHelper;
import com.poolm.util.TextHelper;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	private static Logger logger = Logger.getLogger(AdminController.class);

	@Resource
	private UserService userService;

	@RequestMapping("/main")
	public String toMain() {
		return "index";
	}

	@RequestMapping("/toGetAllUsersPage")
	public String toGetAllUsersPage() {
		return "getAllUserPage";
	}

	@RequestMapping(value = "/getAllUsersPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void getAllUsersPage(@ModelAttribute User user,
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
		List<Map<String, Object>> list = userService
				.getAllUsersPage(user, page);
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

	@RequestMapping(value = "toEditUserPage")
	public String toEditUserPage(Model model,
			@RequestParam(value = "id") int userId) {
		model.addAttribute("userMap", userService.getUserById(userId));
		return "editUserPage";
	}

	@RequestMapping(value = "updateUser", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void updateUser(@RequestParam("userName") String userName,
			@RequestParam("phone") String phone,
			@RequestParam("gender") String gender,
			@RequestParam("status") Integer status,
			@RequestParam("userId") int userId) {
		logger.info("userName[" + userName + "] phone[" + phone + "] gender["
				+ gender + "] status[" + status + "] userId[" + userId + "] ");
		Map<String, Object> map = new HashMap<String, Object>();
		int result = userService.updateUser(userName, phone, gender, status,
				userId);
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

	@RequestMapping(value = "/batchEnableOrDisableUser")
	public void batchEnableOrDisableUser(
			@RequestParam("userIds") String userIds,
			@RequestParam("status") Integer status) {
		logger.info("userIds[" + userIds + "] status[" + status + "]");
		Map<String, Object> map = new HashMap<String, Object>();
		String[] userId = userIds.split(",");
		boolean b = false;
		logger.info("length=" + userId.length);
		for (int i = 0; i < userId.length; i++) {
			int result = userService.enableOrDisableUser(status,
					Integer.parseInt(userId[i]));
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

	@RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
	public void checkPhone(@RequestParam("phone") String phone) {
		logger.info("phone[" + phone + "] ");
		Map<String, Object> map = new HashMap<String, Object>();
		int result = userService.checkPhone(phone);
		if (result > 0) {
			map.put(STATUS, FAIL);
			map.put("msg", "手机号已存在，请重新输入");
		} else {
			map.put(STATUS, SUCCESS);
		}
		logger.info("msg[" + map.get("msg") + "] status[" + map.get("status")
				+ "] ");
		outJSON(map);
	}

	@RequestMapping(value = "/toUserNotice")
	public String toUserNotice(Model model) {
		return "userNotice";
	}

	@RequestMapping(value = "/toUseExplanationOne")
	public String toUseExplanationOne(Model model) {
		return "productNotice";
	}
}
