package com.poolm.controller.app;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poolm.controller.AppController;
import com.poolm.entity.AppData;
import com.poolm.entity.Page;
import com.poolm.pojo.Dish;
import com.poolm.service.impl.app.IAppMenuService;
import com.poolm.util.TextHelper;

@Controller
@RequestMapping("/app/menu")
public class AppMenuController extends AppController {

	private static Logger logger = Logger.getLogger(AppMenuController.class);

	@Resource
	private IAppMenuService appMenuService;

	@RequestMapping(value = "/getSystemMenuList", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void getSystemMenuList() {
		AppData data = new AppData();

		try {
			String pageNo = getParameter("pageNo");
			String pageSize = getParameter("pageSize");
			logger.info("  pageNo[" + pageNo + "] pageSize[" + pageSize + "]");

			if (TextHelper.isNullOrEmpty(pageNo)
					|| TextHelper.isNullOrEmpty(pageSize)) {
				data.setStatus(FAIL);
				data.setMsg(PARAM_NULL);
			} else {
				int totalCount = appMenuService.getDish_counts();
				logger.info("totalCount=" + totalCount);
				Page page = new Page();
				page.setPageNo(Integer.parseInt(pageNo));
				page.setPageSize(Integer.parseInt(pageSize));
				page.setTotalCount(new Integer(totalCount));
				Page.countPageSum(page, totalCount);

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("pageNo", page.getPageNo() * page.getPageSize());
				param.put("pageSize", pageSize);
				List<Dish> results = appMenuService.getDishList(param);

				if (results.size() > 0) {
					data.setStatus(SUCCESS);
					data.putInData("pageCount", page.getPageCount());
					data.putInData("Find", results);
					data.setPage(page);
				} else {
					data.setStatus(SUCCESS);
					data.setMsg(RESULT_EMPTY);
				}
			}
		} catch (UnsupportedEncodingException e) {
			data.setStatus(FAIL);
			data.setMsg(DATA_ACCESS_ERROR);
			logger.error("appGetMenuList ERROR:" + e.getMessage());
			e.printStackTrace();
		} finally {
			outJSON(data);
		}
	}

}
