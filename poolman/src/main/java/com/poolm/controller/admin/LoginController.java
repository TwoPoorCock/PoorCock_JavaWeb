package com.poolm.controller.admin;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poolm.pojo.User;
import com.poolm.service.IUserService;

import com.poolm.util.TextHelper;

@Controller  
@RequestMapping("/login")
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource
	private IUserService userService; 
	
	@RequestMapping("/admin_login")
	public String adminLogin(){
		return "admin/login";
	}
	
	@RequestMapping("/submit")
	public String submit(String username,String password,Model model){
		 logger.info("userName="+username+"  pwd="+password);
		 User user = userService.getUserByName(username);
		 logger.info("user="+user);
		 if(!TextHelper.isNullOrEmpty(user)){
			 return "index";
		 }else return "showUser";
	}
	
}
