package com.poolm.controller.app;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poolm.controller.AppController;
import com.poolm.entity.AppData;
import com.poolm.pojo.User;
import com.poolm.service.impl.app.IAppPersonInfoService;
import com.poolm.util.TextHelper;

@Controller
@RequestMapping("/app/information")
@Scope("prototype")
public class AppPersonInfoController extends AppController {

	private static Logger logger = Logger
			.getLogger(AppPersonInfoController.class);

	@Resource
	private IAppPersonInfoService appPersonInfoService;

	@RequestMapping(value = "/editPersonalInfo", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void editPersonalInfo() {
		AppData data = new AppData();
		try {
			String userId = getParameter("userId");
			String userName = getParameter("userName");
			String gender = getParameter("gender");
			String phone = getParameter("phone");
			String height = getParameter("height");
			String weight = getParameter("weight");
			String weiteng = getParameter("weiteng");
			String kouqiangky = getParameter("kouqiangky");
			String yayingcx = getParameter("yayincx");
			String jianfei = getParameter("jianfei");
			logger.info("userId=" + userId + " userName=" + userName
					+ " gender=" + gender + " phone=" + phone + " height="
					+ height + " weight=" + weight + " weiteng=" + weiteng
					+ " kouqiangky=" + kouqiangky + " yayingcx=" + yayingcx
					+ " jianfei=" + jianfei);

			if (TextHelper.isNullOrEmpty(userId)) {
				data.setStatus(FAIL);
				data.setMsg(PARAM_NULL);
			} else {
				User user = new User();
				user.setId(Integer.parseInt(userId));
				user.setUsername(userName);
				user.setGender(gender);
				user.setPhone(phone);
				user.setHeight(Double.parseDouble(height));
				user.setWeight(Double.parseDouble(weight));
				user.setWeiteng(Integer.parseInt(weiteng));
				user.setKouqiangky(Integer.parseInt(kouqiangky));
				user.setYayingcx(Integer.parseInt(yayingcx));
				user.setJianfei(Integer.parseInt(jianfei));
				if (TextHelper.isNullOrEmpty(gender)) {
					user.setGender("男");
				}
				if (TextHelper.isNullOrEmpty(phone)) {
					user.setPhone("000");
				}
				if (TextHelper.isNullOrEmpty(height)) {
					user.setHeight(0.0);
				}
				if (TextHelper.isNullOrEmpty(weight)) {
					user.setWeight(0.0);
				}
				if (TextHelper.isNullOrEmpty(weiteng)) {
					user.setWeiteng(0);
				}
				if (TextHelper.isNullOrEmpty(kouqiangky)) {
					user.setKouqiangky(0);
				}
				if (TextHelper.isNullOrEmpty(yayingcx)) {
					user.setYayingcx(0);
				}
				if (TextHelper.isNullOrEmpty(jianfei)) {
					user.setJianfei(0);
				}

				// 更新数据库
				int temp = appPersonInfoService.update_PersonalInfo(user);
				if (temp > 0) {
					User user2 = appPersonInfoService.getUserById(user);
					data.setStatus(SUCCESS);
					data.setMsg(MSG_SUCCESS);
					Map<String, Object> result = new HashMap<String, Object>();
					result.put("userId", user2.getId());
					result.put("userName", user2.getUsername());
					result.put("gender", user2.getGender());
					result.put("phone", user2.getPhone());
					result.put("height", user2.getHeight());
					result.put("weight", user2.getWeight());
					result.put("weiteng", user2.getWeiteng());
					result.put("kouqiangky", user2.getKouqiangky());
					result.put("yayingcx", user2.getYayingcx());
					result.put("jianfei", user2.getJianfei());
					data.putInData("Infomation", result);
					logger.info("userId=" + user2.getId() + " userName="
							+ user2.getUsername() + " gender="
							+ user2.getGender() + " phone=" + user2.getPhone()
							+ " height=" + user2.getHeight() + " weight="
							+ user2.getWeight() + " kqky="
							+ user2.getKouqiangky() + " yycx="
							+ user2.getYayingcx() + " wt=" + user2.getWeiteng()
							+ " jf=" + user2.getJianfei());
				} else {
					data.setStatus(FAIL);
					data.setMsg(DATA_ACCESS_ERROR);
				}
			}
		} catch (Exception e) {
			data.setStatus(FAIL);
			data.setMsg(DATA_ACCESS_ERROR);
			logger.info("appPersonInfo:::" + e.getMessage());
			e.printStackTrace();
		} finally {
			outJSON(data);
		}
	}
}
