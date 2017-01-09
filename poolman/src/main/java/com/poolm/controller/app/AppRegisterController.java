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
  
	/***
	 * APP×¢²á
	 */
	@RequestMapping(value={"/register"}, method={RequestMethod.POST, RequestMethod.GET})
	public void appRegister(){
		AppData data = new AppData();
		try{
			String userName = getParameter("userName");
			String password = getParameter("passWord");
			logger.info("userName=" + userName + " passWord=" + password);
			if (TextHelper.isNullOrEmpty(userName)){
				data.setStatus(0);
				data.setMsg(PARAM_ERROR);
			}else{
				Map<String, Object> map = appUserService.getUserByName(userName);
				logger.info("Map==="+map);
				if (!TextHelper.isNullOrEmpty(map)){
					data.setStatus(0);
					data.setMsg(USER_EXIST);
				}else{
					logger.info("pass");
					int result = appUserService.addUser(userName, password);
					if (result > 0) {
						data.setStatus(1);
					}
				}
			}
		}catch (UnsupportedEncodingException e){
			data.setStatus(0);
			data.setMsg(DATA_ACCESS_ERROR);
			logger.error("appLogin error:::" + e.getMessage());
			e.printStackTrace();
		}finally{
			outJSON(data);
		}
	}
  
	/***
	 * APPµÇÂ½
	 */
	@RequestMapping(value={"/login"}, method={RequestMethod.POST, RequestMethod.GET})
	public void appLogin(){
		AppData data = new AppData();
		try{
			String userName = getParameter("userName");
			String passWord = getParameter("passWord");
			logger.info("userName=" + userName + " passWord=" + passWord);
			if (TextHelper.isNullOrEmpty(userName)||TextHelper.isNullOrEmpty(passWord)){
				data.setStatus(0);
				data.setMsg(PARAM_ERROR);
			}else{
				Map<String, Object> map = appUserService.getUserByName(userName);
				String pwd = map.get("passWord").toString();
				if (!pwd.equals(passWord)){
					data.setStatus(0);
					data.setMsg(ACCOUNT_OR_PASSWORD_ERROR);
				}else{
					data.putInData("UserNotice", map);
					data.setStatus(1);
				}
			}
		}catch (Exception e){
			data.setStatus(0);
			data.setMsg(DATA_ACCESS_ERROR);
			e.printStackTrace();
		}finally{
			outJSON(data);
		}
	}
}
