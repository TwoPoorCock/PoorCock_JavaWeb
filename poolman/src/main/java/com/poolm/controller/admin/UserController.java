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
import com.poolm.pojo.SystemUser;
import com.poolm.service.admin.SystemUserService;
import com.poolm.util.TableHelper;
import com.poolm.util.TextHelper;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping("/toGetAllAdminsPage")
	public String toGetAllAdminsPage() {
		return "getAllAdminsPage";
	}

	@RequestMapping(value = "/getAllAdminsPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void getAllAdminsPage(@ModelAttribute SystemUser systemUser,
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
		logger.info("orderByIndex[" + orderByIndex + "] orderBy[" + orderBy
				+ "] orderName[" + orderName + "+] query[" + query
				+ "] pageNo[" + pageNo + "]");
		page.setPageNo((pageNo));
		page.setPageSize(pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = systemUserService
				.getAllSystemUsersPage(systemUser, page);
		if (list != null && list.size() > 0) {
			map.put("rows", list);
		} else {
			map.put("rows", new ArrayList<Map<String, Object>>());
		}

		if (!TextHelper.isNullOrEmpty(query)) {
			int total = page.getTotalCount();
			int selectCount = page.getSelectCount();
			map.put("draw", draw);
			map.put("recordsTotal", total);
			map.put("recordsFiltered", selectCount);
			logger.info("draw[" + draw + "] recordsTotal[" + total
					+ "] recordsFiltered[" + selectCount + "] row:"
					+ map.get("rows"));
		} else {
			int total = page.getTotalCount();
			map.put("draw", draw);
			map.put("recordsTotal", total);
			map.put("recordsFiltered", total);
			logger.info("draw[" + draw + "] recordsTotal[" + total
					+ "] recordsFiltered[" + total + "] row:" + map.get("rows"));
		}

		outJSON(map);
	}

	@RequestMapping("/batchEnableOrDisableSystemUser")
	public void batchEnableOrDisableSystemUser(
			@RequestParam("systemUserIds") String systemUserIds,
			@RequestParam("status") Integer status) {
		logger.info("businessIds[" + systemUserIds + "] status[" + status + "]");
		Map<String, Object> map = new HashMap<String, Object>();
		String[] systemUserId = systemUserIds.split(",");
		boolean b = false;
		logger.info("length=" + systemUserId.length);
		for (int i = 0; i < systemUserId.length; i++) {
			int result = systemUserService.enableOrDisableBusiness(status,
					Integer.parseInt(systemUserId[i]));
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

	@RequestMapping("/toEditAdminPage")
	public String toEditAdminPage(Model model,
			@RequestParam(value = "id") int adminId) {
		model.addAttribute("userMap", systemUserService.getAdminById(adminId));
		return "editAdminPage";
	}

	@RequestMapping(value = "updateAdmin", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void updateAdmin(@RequestParam("userName") String userName,
			@RequestParam("name") String name,
			@RequestParam("passWord") String passWord,
			@RequestParam("email") String email,
			@RequestParam("status") Integer status,
			@RequestParam("userId") int adminId) {
		logger.info("userName[" + userName + "] name[" + name + "] passWord["
				+ passWord + "] email[" + email + "] name[" + name
				+ "] status[" + status + "] userId[" + adminId + "] ");
		Map<String, Object> map = new HashMap<String, Object>();
		int result = systemUserService.updateUser(name, userName, passWord,
				email, status, adminId);
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
}
