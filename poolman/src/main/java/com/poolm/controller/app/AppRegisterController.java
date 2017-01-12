package com.poolm.controller.app;

import com.poolm.controller.AppController;
import com.poolm.entity.AppData;
import com.poolm.service.impl.IAppUserService;
import com.poolm.util.TextHelper;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
@Scope("prototype")
public class AppRegisterController extends AppController{
	
	private static Logger logger = Logger.getLogger(AppRegisterController.class);
	
	@Resource
	private IAppUserService appUserService;
  
	/**
	 * APP注册
	 */
	@RequestMapping(value={"/register"}, method={RequestMethod.POST, RequestMethod.GET})
	public void appRegister(){
		AppData data = new AppData();
		try{
			String userName = getParameter("userName");
			String password = getParameter("passWord");
			logger.info("userName=" + userName + " passWord=" + password);
			if (TextHelper.isNullOrEmpty(userName)){
				data.setStatus(FAIL);
				data.setMsg(PARAM_ERROR);
			}else{
				Map<String, Object> map = appUserService.getUserByName(userName);
				if (!TextHelper.isNullOrEmpty(map)){
					data.setStatus(FAIL);
					data.setMsg(USER_EXIST);
				}else{
					int result = appUserService.addUser(userName, password);
					if (result > 0) {
						data.setStatus(SUCCESS);
						data.setMsg(MSG_SUCCESS);
					}
				}
			}
		}catch (UnsupportedEncodingException e){
			data.setStatus(FAIL);
			data.setMsg(DATA_ACCESS_ERROR);
			logger.error("appLogin error:::" + e.getMessage());
			e.printStackTrace();
		}finally{
			outJSON(data);
		}
	}
  
	/***
	 * APP登陆
	 */
	@RequestMapping(value={"/login"}, method={RequestMethod.POST, RequestMethod.GET})
	public void appLogin(){
		AppData data = new AppData();
		try{
			String userName = getParameter("userName");
			String passWord = getParameter("passWord");
			logger.info("userName=" + userName + " passWord=" + passWord);
			if (TextHelper.isNullOrEmpty(userName)||TextHelper.isNullOrEmpty(passWord)){
				data.setStatus(FAIL);
				data.setMsg(PARAM_ERROR);
			}else{
				Map<String, Object> map = appUserService.getUserByName(userName);
				String pwd = map.get("passWord").toString();
				if (!pwd.equals(passWord)){
					data.setStatus(FAIL);
					data.setMsg(ACCOUNT_OR_PASSWORD_ERROR);
				}else{
					data.putInData("UserNotice", map);
					data.setStatus(SUCCESS);
					data.setMsg(MSG_SUCCESS);
				}
			}
		}catch (Exception e){
			data.setStatus(FAIL);
			data.setMsg(DATA_ACCESS_ERROR);
			e.printStackTrace();
		}finally{
			outJSON(data);
		}
	}
}
