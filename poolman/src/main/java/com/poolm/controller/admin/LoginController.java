package com.poolm.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poolm.controller.BaseController;
import com.poolm.entity.Page;
import com.poolm.pojo.SystemUser;
import com.poolm.service.admin.SystemUserService;
import com.poolm.util.TextHelper;
import com.poolm.util.ValidateCode;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	// @Resource
	// private UserService userService;

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping("/admin_login")
	public String adminLogin() {
		return "login";
	}

	@RequestMapping("/code")
	public void code() {
		ValidateCode vcode = new ValidateCode();
		try {
			vcode.service(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/submit")
	public void submit(@RequestParam("userName") String userName,
			@RequestParam("passWord") String passWord,
			@RequestParam("codeStr") String codeStr) {
		logger.info("userName=" + userName + "  pwd=" + passWord + " codeStr="
				+ codeStr);
		Map<String, Object> map = new HashMap<String, Object>();
		String code = (String) session.getAttribute("code");
		if (TextHelper.isNullOrEmpty(userName)
				|| TextHelper.isNullOrEmpty(passWord)
				|| TextHelper.isNullOrEmpty(codeStr)) {
			map.put(STATUS, FAIL);
			map.put(MSG, "参数错误");
		}
		if (code.equalsIgnoreCase(codeStr)) {
			SystemUser user = systemUserService.getUserByName(userName);
			if (!TextHelper.isNullOrEmpty(user)) {
				if (user.getPassword().equals(passWord)) {
					map.put(STATUS, SUCCESS);
					map.put("user", user);
				} else {
					map.put(STATUS, FAIL);
					map.put(MSG, "用户密码有误");
				}
			} else {
				map.put(STATUS, FAIL);
				map.put(MSG, "用户名不存在！");
			}
		} else {
			map.put(STATUS, ERROR);
			map.put(MSG, "验证码输入错误");
		}
		outJSON(map);
	}

	@RequestMapping(value = "/toIndex", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String toIndex(@RequestParam("userName") String userName, Model model) {
		SystemUser user = systemUserService.getUserByName(userName);
		model.addAttribute("userInfo", user);
		return "/index";
	}

	@RequestMapping(value = "/tipCount", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void dishCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page();
		int dishCount = systemUserService.dishCount(page);
		int userCount = systemUserService.userCount(page);
		int systemCount = systemUserService.systemCount(page);
		int businessCount = systemUserService.businessCount(page);
		map.put("dishCount", dishCount);
		map.put("userCount", userCount);
		map.put("systemCount", systemCount);
		map.put("businessCount", businessCount);
		outJSON(map);
	}
}
