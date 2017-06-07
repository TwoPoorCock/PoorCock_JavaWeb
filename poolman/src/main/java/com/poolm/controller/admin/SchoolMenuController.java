package com.poolm.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poolm.controller.BaseController;
import com.poolm.pojo.Dish;

@Controller
@RequestMapping("/school")
public class SchoolMenuController extends BaseController {

	private static Logger logger = Logger.getLogger(SchoolMenuController.class);

	@RequestMapping("/toGetAllSystemMenuPage")
	public String toGetAllSystemMenuPage() {
		return "getAllSystemMenu";
	}

}
